package fr.exemple.signalements.signalement;

import java.math.BigDecimal;
import java.util.UUID;

public record SignalementResponse(
    UUID id,
    String ligne,
    BigDecimal pointKm,
    String description,
    boolean urgent,
    String statut) {
}
