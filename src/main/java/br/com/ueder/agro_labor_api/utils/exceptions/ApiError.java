package br.com.ueder.agro_labor_api.utils.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ApiError(
        String message,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dataHora) {
}
