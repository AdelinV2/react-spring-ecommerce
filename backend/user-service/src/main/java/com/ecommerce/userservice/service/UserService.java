package com.ecommerce.userservice.service;

import com.ecommerce.userservice.entity.User;

public interface UserService {

    /**
     * Create a new user
     * @param user
     * @return created user or null if email/phone already exists
     */
    User createUser(User user);

    /**
     * Update an existing user
     * @param user
     * @return updated user
     */
    User updateUser(User user);

    /**
     * Delete an existing user
     * @param user
     */
    void deleteUser(User user);

    /**
     * Get a user by id
     * @param id
     * @return user or null if not found
     */
    User getUserById(int id);
}
