package fr.exemple.signalements.signalement;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "signalement")
public class signalement {
    @Id
    private UUID id;
    private String ligne;
    private BigDecimal pointKm;
    private String description;
    private boolean urgent;
    private String statut;
    protected signalement() {

    }
    public UUID getId() { return id; }
    public String getLigne() { return ligne; }
    public BigDecimal getPointKm() { return pointKm; }
    public String getDescription() { return description; }
    public boolean isUrgent() { return urgent; }
    public String getStatut() { return statut; }
}
