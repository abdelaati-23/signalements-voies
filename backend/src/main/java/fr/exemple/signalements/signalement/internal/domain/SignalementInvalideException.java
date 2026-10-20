package fr.exemple.signalements.signalement.internal.domain;

import java.util.List;

public class SignalementInvalideException extends RuntimeException{
    public record Violation(String champ, String message){}
    private final List<Violation> violations;
    public SignalementInvalideException(List<Violation> violations) {
        super("Le signalement est invalide"+violations);
        this.violations = List.copyOf(violations);
    }
    public List<Violation> getViolations() {
        return violations;
    }
}
