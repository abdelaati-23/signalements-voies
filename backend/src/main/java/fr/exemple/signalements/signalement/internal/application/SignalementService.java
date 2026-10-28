package fr.exemple.signalements.signalement.internal.application;

import fr.exemple.signalements.signalement.internal.domain.Signalement;
import fr.exemple.signalements.signalement.internal.domain.SignalementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional( readOnly = true)
public class SignalementService {
    private final SignalementRepository repository;
    public List<Signalement> lister(){
        return repository.findAll();
    }
}
