package fr.exemple.signalements;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchitectureTests {

    private static JavaClasses app;

    @BeforeAll
    static void importerLesClasses() {
        app = new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("fr.exemple.signalements");
    }

    @Test
    void les_controleurs_vivent_dans_la_couche_web() {
        classes().that().areAnnotatedWith(RestController.class)
            .should().resideInAPackage("..internal.web..")
            .check(app);
    }

    @Test
    void la_couche_web_n_utilise_pas_directement_un_repository() {
        noClasses().that().resideInAPackage("..internal.web..")
            .should().dependOnClassesThat().areAssignableTo(Repository.class)
            .check(app);
    }

    @Test
    void le_domaine_ne_depend_ni_de_web_ni_de_application() {
        noClasses().that().resideInAPackage("..internal.domain..")
            .should().dependOnClassesThat().resideInAnyPackage("..internal.web..", "..internal.application..")
            .check(app);
    }

    @Test
    void application_ne_depend_pas_de_web() {
        noClasses().that().resideInAPackage("..internal.application..")
            .should().dependOnClassesThat().resideInAPackage("..internal.web..")
            .check(app);
    }
}
