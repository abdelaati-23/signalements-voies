package fr.exemple.signalements.signalement.internal.web;

import fr.exemple.signalements.signalement.internal.domain.Signalement;

import java.math.BigDecimal;
import java.util.UUID;

public record SignalementResponse(
    UUID id,
    String ligne,
    BigDecimal pointKm,
    String description,
    boolean urgent,
    String statut) {

    public static SignalementResponse from(Signalement s) {
        return new SignalementResponse(
            s.getId(),
            s.getLigne(),
            s.getPointKm(),
            s.getDescription(),
            s.isUrgent(),
            s.getStatut()
        );
    }
}
