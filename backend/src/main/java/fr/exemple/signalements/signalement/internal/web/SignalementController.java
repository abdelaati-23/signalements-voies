package fr.exemple.signalements.signalement;

import fr.exemple.signalements.signalement.internal.domain.SignalementResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/signalements")
public class SignalementController {
    private final SignalementRepository repository;

    public SignalementController(SignalementRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SignalementResponse> lister(){
        return repository.findAll().stream()
            .map(s -> new SignalementResponse(s.getId(), s.getLigne(), s.getPointKm(), s.getDescription(), s.isUrgent(), s.getStatut()))
            .toList();
    }
}
