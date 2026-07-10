package lv.bootcamp.shelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lv.bootcamp.shelter.model.AnimalType;

/**
 * JSON request body for creating a new animal via the REST API.
 * Status is not included; all new animals start as AVAILABLE.
 */
public record AnimalCreateRequest(

        @Schema(description = "Animal name", example = "Barsik")
        @NotBlank(message = "Name is required")
        String name,

        @Schema(description = "Animal type", example = "CAT")
        @NotNull(message = "Type is required")
        AnimalType type,

        @Schema(description = "Breed", example = "Maine Coon")
        String breed,

        @Schema(description = "Age in years", example = "7")
        @Min(value = 0, message = "Age cannot be negative")
        Integer age,

        @Schema(description = "Description", example = "Eats mice & drinks cow milk")
        String description,

        @Schema(description = "Image filename or URL", example = "cat.jpg")
        String imageUrl
) {}