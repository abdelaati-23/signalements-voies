package fr.exemple.signalements.signalement.internal.web;

import fr.exemple.signalements.signalement.internal.application.SignalementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/signalements")
public class SignalementController {
    private final SignalementService signalementService;

    @GetMapping
    public List<SignalementResponse> lister(){
        return signalementService.lister().stream().map(SignalementResponse::from).toList();
    }
}
