package com.dimas.moexdataservice.model.dto.other;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error model")
public record ExceptionMessage(
        @Schema(description = "Error message", example = "Wrong date format") String message
) {
}
