package fr.exemple.signalements.signalement.internal.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SignalementRepository extends JpaRepository<Signalement, UUID> {
}
