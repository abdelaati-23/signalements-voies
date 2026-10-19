package fr.exemple.signalements.signalement.internal.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "signalement")
public class Signalement {
    @Id
    private UUID id;
    private String ligne;
    private BigDecimal pointKm;
    private String description;
    private boolean urgent;
    @Enumerated(EnumType.STRING)
    private StatutSignalement statut;
    private Instant saisiLe;
    private Instant recuLe;
    protected Signalement() {
    }
    public static Signalement creer(UUID id, String ligne, BigDecimal pointKm, String description,
                                    boolean urgent, Instant saisiLe, Instant recuLe){
        Signalement s=new Signalement();
        s.id=id;
        s.ligne=ligne;
        s.pointKm=pointKm;
        s.description=description;
        s.urgent=urgent;
        s.statut=StatutSignalement.NOUVEAU;
        s.saisiLe=saisiLe;
        s.recuLe=recuLe;
        return s;
    }

    public UUID getId() { return id; }
    public String getLigne() { return ligne; }
    public BigDecimal getPointKm() { return pointKm; }
    public String getDescription() { return description; }
    public boolean isUrgent() { return urgent; }
    public StatutSignalement getStatut() { return statut; }
    public Instant getSaisiLe() { return saisiLe; }
    public Instant getRecuLe() { return recuLe; }
}
