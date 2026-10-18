package fr.exemple.signalements.signalement.internal.application;

import fr.exemple.signalements.signalement.internal.domain.Signalement;
import fr.exemple.signalements.signalement.internal.domain.SignalementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional( readOnly = true)
public class SignalementService {
    private final SignalementRepository repository;
    public SignalementService(SignalementRepository repository) {
        this.repository = repository;
    }
    public List<Signalement> lister(){
        return repository.findAll();
    }
}
