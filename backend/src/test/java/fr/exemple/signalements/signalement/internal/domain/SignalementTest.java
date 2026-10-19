package fr.exemple.signalements.signalement.internal.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SignalementTest {
    private static final Instant SAISI_LE=Instant.parse("2026-10-19T07:55:00Z");
    private static final Instant RECU_LE=Instant.parse("2026-10-19T08:00:00Z");
    @Test
    void un_signalement_cree_est_au_statut_nouveau_avec_ses_deux_dates(){
        UUID id=UUID.randomUUID();
        Signalement signalement=Signalement.creer(
            id, "Ligne 001000", new BigDecimal("12.350"), "Rail fissuré", false, SAISI_LE, RECU_LE
        );
        assertThat(signalement.getId()).isEqualTo(id);
        assertThat(signalement.getStatut()).isEqualTo(StatutSignalement.NOUVEAU);
        assertThat(signalement.getSaisiLe()).isEqualTo(SAISI_LE);
        assertThat(signalement.getRecuLe()).isEqualTo(RECU_LE);
    }

}
