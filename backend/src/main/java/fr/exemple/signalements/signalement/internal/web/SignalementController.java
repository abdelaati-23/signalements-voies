package fr.exemple.signalements.signalement.internal.web;

import fr.exemple.signalements.signalement.internal.application.SignalementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/signalements")
public class SignalementController {
    private final SignalementService signalementService;

    public SignalementController(SignalementService signalementService) {
        this.signalementService = signalementService;
    }

    @GetMapping
    public List<SignalementResponse> lister(){
        return signalementService.lister().stream().map(SignalementResponse::from).toList();
    }
}
