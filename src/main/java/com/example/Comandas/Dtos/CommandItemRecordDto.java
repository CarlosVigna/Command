package com.example.Comandas.Dtos;

import com.example.Comandas.Models.Command;
import com.example.Comandas.Models.Products;
import org.antlr.v4.runtime.misc.NotNull;

public record CommandItemRecordDto(@NotNull Command command,
                                   @NotNull Products product,
                                   @NotNull Double amount,
                                   @NotNull Double unitValue,
                                   @NotNull Double productTotal) {
}
