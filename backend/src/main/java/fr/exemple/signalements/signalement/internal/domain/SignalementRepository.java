package fr.exemple.signalements.signalement;

import fr.exemple.signalements.signalement.internal.domain.Signalement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SignalementRepository extends JpaRepository<Signalement, UUID> {
}
