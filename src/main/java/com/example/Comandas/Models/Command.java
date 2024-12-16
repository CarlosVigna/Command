package com.example.Comandas.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Command")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommand;
    private String detailsCommand;
    private Double totalValue;
    private String situation;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @OneToMany(mappedBy = "command", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommandItems> items;

    // Calcula o valor total da comanda com base nos itens
    @PrePersist
    @PreUpdate
    private void calculateTotalValue() {
        this.totalValue = items.stream()
                .mapToDouble(CommandItems::getProductTotal)
                .sum();
    }
}
