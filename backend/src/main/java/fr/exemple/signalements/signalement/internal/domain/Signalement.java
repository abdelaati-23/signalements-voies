package fr.exemple.signalements.signalement.internal.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "signalement")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Signalement {
    @Id
    private UUID id;
    private String ligne;
    private BigDecimal pointKm;
    private String description;
    private boolean urgent;
    private String statut;

}
