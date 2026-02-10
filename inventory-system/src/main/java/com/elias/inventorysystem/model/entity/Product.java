package com.elias.inventorysystem.model.entity;

import com.elias.inventorysystem.exception.InsufficientStockException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "current_stock", nullable = false)
    private Integer currentStock = 0;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<StockMovement> movements = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "product_suppliers",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    private Set<Supplier> suppliers = new HashSet<>();


    public void updateStock(MovementType type, Integer quantity) {
        switch (type) {
            case INPUT -> this.currentStock += quantity;

            case OUTPUT -> {
                if (this.currentStock < quantity) {
                    throw new InsufficientStockException("Insufficient stock for the product: " + this.name);
                }
                this.currentStock -= quantity;
            }

            case ADJUSTMENT -> this.currentStock = quantity;
        }
    }

}
