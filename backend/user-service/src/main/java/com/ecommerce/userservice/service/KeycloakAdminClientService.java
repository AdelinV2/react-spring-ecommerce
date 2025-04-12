package com.ecommerce.userservice.service;

import com.ecommerce.userservice.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Map;

@Service
public class KeycloakAdminClientService {

    private final WebClient webClient;
    private final String authServerUrl;
    private final String realm;
    private final String clientId;
    private final String clientSecret;
    private final String userServiceClientId;
    private final BCryptPasswordEncoder passwordEncoder; // new field

    public KeycloakAdminClientService(WebClient.Builder webClientBuilder,
                                      @Value("${AUTH_SERVER_URL:http://localhost:8080}") String authServerUrl,
                                      @Value("${KEYCLOAK_REALM:ecommerce}") String realm,
                                      @Value("${keycloak-admin.resource:user-service-admin}") String clientId,
                                      @Value("${USER_SERVICE_ADMIN_KEYCLOAK_SECRET}") String clientSecret,
                                      @Value("${USER_SERVICE_KEYCLOAK_CLIENT_ID:user-service}") String userServiceClientId,
                                      BCryptPasswordEncoder passwordEncoder) {
        this.webClient = webClientBuilder.baseUrl(authServerUrl).build();
        this.authServerUrl = authServerUrl;
        this.realm = realm;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.userServiceClientId = userServiceClientId;
        this.passwordEncoder = passwordEncoder; // assign encoder
    }

    private String getTokenEndpoint() {
        return "/realms/" + realm + "/protocol/openid-connect/token";
    }

    public Mono<String> getAdminAccessToken() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);

        return webClient.post()
                .uri(getTokenEndpoint())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData(body))
                .retrieve()
                .onStatus(status -> status.isError(), response ->
                        response.bodyToMono(String.class)
                                .flatMap(error -> {
                                    System.out.println("Token error: " + error);
                                    return Mono.error(new RuntimeException("Error retrieving token: " + error));
                                }))
                .bodyToMono(Map.class)
                .map(response -> (String) response.get("access_token"));
    }

    public Mono<String> createUser(String username, String email, String password) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);

        Map<String, Object> userRepresentation = Map.of(
                "username", username,
                "email", email,
                "enabled", true,
                "credentials", List.of(Map.of("type", "password", "value", password, "temporary", false))
        );

        return getAdminAccessToken().flatMap(accessToken ->
            webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/{realm}/users")
                        .build(realm))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(userRepresentation))
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        String location = response.headers().asHttpHeaders()
                                .getFirst(HttpHeaders.LOCATION);
                        if (location != null) {
                            String userId = location.substring(location.lastIndexOf("/") + 1);
                            return Mono.just(userId);
                        } else {
                            return Mono.error(new RuntimeException("Missing Location header in response"));
                        }
                    } else {
                        return response.bodyToMono(String.class)
                                .flatMap(error -> {
                                    System.out.println("User creation error: " + error);
                                    return Mono.error(new RuntimeException("Error creating user: " + error));
                                });
                    }
                })
        );
    }

    public Mono<String> getUserIdByUsername(String username) {
        return getAdminAccessToken()
                .flatMap(accessToken -> webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/admin/realms/{realm}/users")
                                .queryParam("username", username)
                                .build(realm))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                        .retrieve()
                        .onStatus(status -> status.isError(), response ->
                                response.bodyToMono(String.class)
                                        .flatMap(error -> {
                                            System.out.println("Get user ID error: " + error);
                                            return Mono.error(new RuntimeException("Error retrieving user ID: " + error));
                                        }))
                        .bodyToFlux(Map.class)
                        .next()
                        .map(user -> (String) user.get("id")));
    }

    public Mono<Void> assignRoleToUser(String userId, Role role) {
        return getAdminAccessToken()
                .flatMap(accessToken ->
                        webClient.get()
                                .uri(uriBuilder -> uriBuilder
                                        .path("/admin/realms/{realm}/roles/{roleName}")
                                        .build(realm, role.getRole()))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                                .retrieve()
                                .onStatus(status -> status.isError(), response ->
                                        response.bodyToMono(String.class)
                                                .flatMap(error -> {
                                                    System.out.println("Get realm role error: " + error);
                                                    return Mono.error(new RuntimeException("Error retrieving realm role: " + error));
                                                }))
                                .bodyToMono(Map.class)
                                .flatMap(theRole ->
                                        webClient.post()
                                                .uri(uriBuilder -> uriBuilder
                                                        .path("/admin/realms/{realm}/users/{userId}/role-mappings/realm")
                                                        .build(realm, userId))
                                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                                .body(BodyInserters.fromValue(List.of(theRole)))
                                                .retrieve()
                                                .onStatus(status -> status.isError(), response ->
                                                        response.bodyToMono(String.class)
                                                                .flatMap(error -> {
                                                                    System.out.println("Assign realm role error: " + error);
                                                                    return Mono.error(new RuntimeException("Error assigning realm role: " + error));
                                                                }))
                                                .toBodilessEntity()
                                                .then()
                                )
                );
    }

    public Mono<String> registerUserInKeycloak(String username, String email, String password, Role role) {
        String encodedPassword = passwordEncoder.encode(password);
        return createUser(username, email, encodedPassword)
                .flatMap(userId -> assignRoleToUser(userId, role).thenReturn(userId));
    }
}
