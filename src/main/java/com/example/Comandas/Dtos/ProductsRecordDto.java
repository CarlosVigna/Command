package com.example.Comandas.Dtos;

import com.example.Comandas.Models.Categories;
import com.example.Comandas.Models.Units;
import org.antlr.v4.runtime.misc.NotNull;

public record ProductsRecordDto(@NotNull String details,
                                @NotNull Double price,
                                Double stock,
                                Categories category,
                                Units unit
){}
