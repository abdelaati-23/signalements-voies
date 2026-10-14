# Règles de contribution

## Branches (GitHub Flow)
- `main` est toujours déployable et protégée : on n'y pousse jamais directement.
- Une branche par story : `feat/US-04-creer-signalement`, `fix/US-10-affectation-doublon`, `docs/adr-007`.
- Branches courtes : une story se découpe pour tenir en quelques jours.

## Commits (Conventional Commits)
Format : `type(portée): message à l'impératif`

| Type | Usage |
|---|---|
| feat | nouvelle fonctionnalité |
| fix | correction de bug |
| docs | documentation seule |
| test | ajout ou changement de tests |
| refactor | changement de structure sans effet visible |
| chore | outillage, dépendances, configuration |
| ci | pipeline |

Exemple : `feat(signalement): valider ligne et PK à la création`

## Pull requests
1. Ouvrir la PR tôt, en brouillon si besoin.
2. Une PR = une story (ou une partie claire). Petite, donc relisable en 15 minutes.
3. La CI doit être verte avant fusion.
4. Fusion en *squash* : un commit propre par story sur `main`.

## Définition de « terminé » (Definition of Done)
- Les critères d'acceptation de la story sont tous vérifiés par des tests.
- La CI est verte (build, tests, architecture).
- Le code est relu (PR) et les remarques sont traitées.
- La documentation est à jour, notamment l'ADR concerné.
- Aucune donnée réelle ni aucun secret dans le dépôt.

## Décisions techniques
Toute décision structurante fait l'objet d'un ADR dans `docs/adr/` (modèle : `0000-template.md`).
