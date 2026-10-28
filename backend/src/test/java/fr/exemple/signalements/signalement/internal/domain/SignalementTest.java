package fr.exemple.signalements.signalement.internal.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    @Test
    void refuse_une_ligne_vide() {
        assertThatThrownBy(() -> creer("  ", new BigDecimal("1.5"), "Rail fissuré"))
            .isInstanceOfSatisfying(SignalementInvalideException.class,
                e -> assertThat(champs(e)).containsExactly("ligne"));
    }

    @Test
    void refuse_un_point_kilometrique_negatif() {
        assertThatThrownBy(() -> creer("Ligne 001000", new BigDecimal("-0.1"), "Rail fissuré"))
            .isInstanceOfSatisfying(SignalementInvalideException.class,
                e -> assertThat(champs(e)).containsExactly("pointKm"));
    }

    @Test
    void accepte_un_point_kilometrique_a_zero() {
        assertThatCode(() -> creer("Ligne 001000", BigDecimal.ZERO, "Début de ligne"))
            .doesNotThrowAnyException();
    }
    @Test
    void refuse_une_description_vide_ou_trop_longue() {
        assertThatThrownBy(() -> creer("Ligne 001000", BigDecimal.ONE, ""))
            .isInstanceOfSatisfying(SignalementInvalideException.class,
                e -> assertThat(champs(e)).containsExactly("description"));

        assertThatThrownBy(() -> creer("Ligne 001000", BigDecimal.ONE, "x".repeat(2001)))
            .isInstanceOfSatisfying(SignalementInvalideException.class,
                e -> assertThat(champs(e)).containsExactly("description"));
    }

    @Test
    void refuse_un_identifiant_ou_une_date_de_saisie_absents() {
        assertThatThrownBy(() -> Signalement.creer(
            null, "Ligne 001000", BigDecimal.ONE, "Rail fissuré", false, null, RECU_LE))
            .isInstanceOfSatisfying(SignalementInvalideException.class,
                e -> assertThat(champs(e)).containsExactlyInAnyOrder("id", "saisiLe"));
    }

    @Test
    void signale_tous_les_champs_invalides_en_une_seule_fois() {
        assertThatThrownBy(() -> creer(" ", new BigDecimal("-1"), ""))
            .isInstanceOfSatisfying(SignalementInvalideException.class,
                e -> assertThat(champs(e)).containsExactlyInAnyOrder("ligne", "pointKm", "description"));
    }

    private static Signalement creer(String ligne, BigDecimal pointKm, String description) {
        return Signalement.creer(UUID.randomUUID(), ligne, pointKm, description, false, SAISI_LE, RECU_LE);
    }
    private static List<String> champs(SignalementInvalideException e) {
        return e.getViolations().stream().map(SignalementInvalideException.Violation::champ).toList();
    }

}
