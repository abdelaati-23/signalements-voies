# Phase 4 : industrialisation, guide pas à pas

Objectif : avoir un socle où **chaque modification est relue, testée et vérifiée automatiquement** avant d'entrer dans `main`. On ne code aucune fonctionnalité métier avant que ce socle soit vert.

## 0. Ordre de travail

1. Créer le dépôt et y copier ce kit
2. Régler les protections GitHub
3. Monter le tableau du projet et les issues
4. Générer le squelette backend et frontend
5. Faire passer la CI au vert (le « walking skeleton »)
6. Brancher la qualité (SonarCloud, Dependabot)
7. Vérifier la liste de fin de phase

## 1. Le dépôt

- Nom : `signalements-voies` (public si tu veux SonarCloud gratuit).
- Copie le contenu du kit à la racine, puis place tes livrables : cadrage dans `docs/01-cadrage/`, backlog dans `docs/02-specification/`, architecture dans `docs/03-architecture/`.
- Premier commit directement sur `main` (le seul autorisé), puis tout passe par des branches.

## 2. Protéger `main` (Settings → Branches → Add rule, ou Rulesets)

Cocher :
- **Require a pull request before merging** (approbations requises : 0 si tu travailles seul, GitHub ne permet pas de t'approuver toi-même ; la PR reste obligatoire).
- **Require status checks to pass** : ajouter `Backend (Java 21 / Maven)` et `Frontend (Angular)` (ils n'apparaissent qu'après une première exécution de la CI).
- **Require conversation resolution before merging**.
- **Block force pushes** et **Restrict deletions**.
- Dans Settings → General : autoriser seulement « Squash merging » et cocher « Automatically delete head branches ».

Activer aussi (Settings → Code security) : **Dependabot alerts**, **Secret scanning** et **Push protection**. C'est gratuit pour un dépôt public et ça bloque l'envoi accidentel d'un mot de passe.

## 3. Organiser le travail

**Tableau** (onglet Projects → New project → Board). Colonnes : `Backlog`, `Prêt`, `En cours`, `En revue`, `Terminé`.

**Étiquettes** (Issues → Labels) : `must`, `should`, `could`, `story`, `bug`, `epic:acces`, `epic:signalement`, `epic:affectation`, `epic:intervention`, `epic:pilotage`, `epic:reprise`, `risque`.

**Jalons** (Milestones) : `Lot 1 socle`, `Lot 2 valeur direction`, `Lot 3 terrain`.

**Issues** : une par user story du backlog, avec le modèle fourni. Copie les critères d'acceptation tels quels. Une story est « Prête » quand ses questions client sont levées.

## 4. Générer les squelettes

### Backend
Va sur start.spring.io :
- Project : Maven · Language : Java · Java : 21 · Spring Boot : dernière version stable
- Dépendances : Spring Web, Validation, Spring Data JPA, PostgreSQL Driver, Flyway Migration, OAuth2 Resource Server, Spring Boot Actuator, Testcontainers, Spring Modulith
- Dézippe dans `backend/`.

Crée un sous-paquet par module sous le paquet racine : `signalement`, `affectation`, `intervention`, `pilotage`, `identite`, `notification`. Spring Modulith traite chaque sous-paquet direct comme un module.

Test d'architecture (à ajouter dès maintenant) :

```java
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTests {
    @Test
    void verifiesModularStructure() {
        ApplicationModules.of(Application.class).verify();
    }
}
```

Ce test échoue si un module va chercher dans les classes internes d'un autre. C'est la parade à « discipline d'équipe » de l'ADR-002.

### Frontend
```bash
npm install -g @angular/cli
ng new frontend --routing --style=scss
```
Capacitor viendra à l'étape mobile (`npx cap init`), pas avant que le web tourne.

## 5. Le « walking skeleton »

Le plus petit morceau qui traverse **toute la chaîne** :

| Couche | Contenu minimal |
|---|---|
| Base | Migration Flyway `V1__creer_table_signalement.sql` avec quelques colonnes |
| Backend | `GET /api/signalements` qui renvoie la liste (vide) depuis PostgreSQL |
| Test | Un test d'intégration avec Testcontainers qui appelle l'endpoint |
| Frontend | Une page qui appelle l'API et affiche « 0 signalement » |
| CI | Les deux jobs verts sur une PR |

Tant que ce squelette n'est pas vert de bout en bout, aucune story métier. Tu y découvres les vraies difficultés (versions, configuration, CORS, Docker) quand elles coûtent le moins cher.

Fais-le **en passant par une vraie PR** : branche `chore/walking-skeleton`, commits conventionnels, PR avec le modèle, CI verte, squash.

## 6. SonarCloud (optionnel, recommandé)

1. Connecte-toi sur sonarcloud.io avec GitHub et importe le dépôt.
2. Génère un jeton, puis ajoute-le dans GitHub : Settings → Secrets and variables → Actions → `SONAR_TOKEN`.
3. Dans `pom.xml`, ajoute les propriétés `sonar.organization` et `sonar.projectKey`.
4. Décommente le job `sonar` de `ci.yml`.
5. Règle le seuil de qualité : pas de nouvelle vulnérabilité, couverture du code nouveau supérieure à 70 %.

## 7. Liste de fin de phase

- [ ] `main` est protégée, la CI est exigée.
- [ ] `docker compose up -d` démarre la base et le stockage en une commande.
- [ ] Les 21 stories existent en issues, avec étiquettes et jalons.
- [ ] Le walking skeleton est fusionné par une PR et la CI est verte.
- [ ] Le test d'architecture Modulith est présent et passe.
- [ ] Le secret scanning et Dependabot sont actifs.
- [ ] `README.md` permet à un nouvel arrivant de démarrer en 10 minutes.

## Ce que je regarderai en relecture

- L'historique Git : commits lisibles, aucune poussée directe sur `main` après le premier.
- La CI : temps d'exécution, cache actif, pas de secret en clair.
- Le test d'architecture : il doit **échouer** si tu importes une classe interne d'un autre module (essaie-le exprès).
- Le `README` : peut-on tout lancer en suivant uniquement ce qu'il dit ?
