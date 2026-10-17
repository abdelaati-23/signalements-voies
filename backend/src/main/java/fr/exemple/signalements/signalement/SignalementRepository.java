package fr.exemple.signalements.signalement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SignalementRepository extends JpaRepository<signalement, UUID> {
}
