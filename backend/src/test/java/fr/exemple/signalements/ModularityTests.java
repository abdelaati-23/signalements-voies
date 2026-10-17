package fr.exemple.signalements;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTests {
    @Test
    void verifiesModularStructure() {
        ApplicationModules.of(SignalementsApplication.class).verify();
    }
}
