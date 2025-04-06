package com.ecommerce.product_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sub_specifications")
public class SubSpecification {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specification_id")
    @JsonIgnore
    private Specification specification;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "order_index", nullable = false)
    private byte orderIndex;
}

