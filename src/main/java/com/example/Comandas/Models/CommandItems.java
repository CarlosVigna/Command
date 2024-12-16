package com.example.Comandas.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CommandItems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommandItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCommand", nullable = false)
    private Command command;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProd", nullable = false)
    private Products product;

    private Double amount;
    private Double unitValue;
    private Double productTotal;

    // Método para atualizar valores ao salvar/atualizar
    @PrePersist
    @PreUpdate
    private void updateValues() {
        if (unitValue == null && product != null) {
            this.unitValue = product.getPrice();
        }
        this.productTotal = (amount != null && unitValue != null) ? amount * unitValue : 0.0;
    }

    @Transient
    public Double getProductTotal() {
        return amount * unitValue;
    }



}