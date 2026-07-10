package lv.bootcamp.shelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lv.bootcamp.shelter.model.AnimalStatus;
import lv.bootcamp.shelter.model.AnimalType;

/**
 * Response body for a single animal returned by the API.
 * {@code adoptionNote} is only populated for ADMIN callers (see AnimalService#toResponse) —
 * everyone else just sees the plain {@code status}.
 */
public record AnimalResponse(

        @Schema(description = "Animal ID", example = "1")
        Long id,

        @Schema(description = "Animal name", example = "Barsik")
        String name,

        @Schema(description = "Animal type", example = "CAT")
        AnimalType type,

        @Schema(description = "Breed", example = "Maine Coon")
        String breed,

        @Schema(description = "Age in years", example = "7")
        Integer age,

        @Schema(description = "Description", example = "Eats mice & drinks cow milk")
        String description,

        @Schema(description = "Adoption status", example = "AVAILABLE")
        AnimalStatus status,

        @Schema(description = "Image filename or URL", example = "cat.jpg")
        String imageUrl,

        @Schema(description = "Adoption note (if adopted)", example = "Adopted by John Doe on 2026-07-09")
        String adoptionNote
) {}
