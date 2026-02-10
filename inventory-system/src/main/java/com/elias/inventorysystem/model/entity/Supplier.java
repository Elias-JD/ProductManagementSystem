package com.elias.inventorysystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "contact_email", unique = true)
    private String contactEmail;

    @Column(nullable = false)
    private String phone;

    @ManyToMany(mappedBy = "suppliers")
    private Set<Product> products = new HashSet<>();
}
