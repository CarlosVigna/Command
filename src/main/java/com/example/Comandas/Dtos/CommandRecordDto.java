package com.example.Comandas.Dtos;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
import java.util.List;

public record CommandRecordDto(@NotNull String detailsCommand,
                               @NotNull Double totalValue,
                               @NotNull String situation,
                               @NotNull Date startDate,
                               @NotNull List<CommandItemRecordDto> items) {
}
