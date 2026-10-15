# Suivi des signalements de maintenance voies

Application de suivi des signalements et interventions de maintenance sur un réseau ferroviaire (projet d'entraînement aux pratiques d'entreprise).

## Documentation
- Cadrage : `docs/01-cadrage/`
- Spécification (backlog) : `docs/02-specification/`
- Architecture et ADR : `docs/03-architecture/`, `docs/adr/`
- Règles de contribution : `CONTRIBUTING.md`

## Démarrer en local
Prérequis : Docker, Java 25 (LTS), Node 22.

```bash
cp .env.example .env
docker compose up -d          # PostgreSQL + stockage objet
cd backend && ./mvnw spring-boot:run
cd frontend && npm ci && npm start
```

## Structure
```
backend/    API Java / Spring Boot (monolithe modulaire)
frontend/   Application Angular (web et mobile via Capacitor)
docs/       Cadrage, spécification, architecture, ADR
.github/    CI, modèles de PR et d'issues
```

## Statut
En développement. Voir le tableau du projet GitHub pour l'avancement.
