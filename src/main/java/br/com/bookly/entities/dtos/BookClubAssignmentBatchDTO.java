package br.com.bookly.entities.dtos;

import java.util.List;
import java.util.UUID;

public record BookClubAssignmentBatchDTO(
        UUID clubId,
        List<UUID> bookIds,
        String frequency // semanal, quinzenal, mensal
) {}