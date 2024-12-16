package com.example.Comandas.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("serial")

public class Categories {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCategory;
    private String detailsCategory;


}
