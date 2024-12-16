package com.example.Comandas.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Units")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("serial")
public class Units {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idUnit;
    private String detailsUnit;
}
