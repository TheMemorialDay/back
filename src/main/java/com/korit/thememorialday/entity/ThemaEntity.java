package com.korit.thememorialday.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "thema")
public class ThemaEntity {
    @Id
    private String thema;

    @ManyToOne
    @JoinColumn(name = "product_number", nullable = false)
    private ProductEntity product;

    public ThemaEntity(String thema, ProductEntity product) {
        this.thema = thema;
        this.product = product;
    }
}
