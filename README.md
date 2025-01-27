# Sum-It Backend

## Description

Ce projet constitue la partie backend de l'application Sum-It, qui permet de localiser et de gérer des sommets en lien avec des API externes. Il est développé avec Spring Boot et utilise une base de données MongoDB pour stocker les informations sur les sommets.

## Fonctionnalités

- API RESTful pour la gestion des sommets (consultation, création, mise à jour, suppression).
  - Exemple : Une requête GET sur l'endpoint `/rest/peak/all` retourne la liste de tous les sommets en JSON, avec leurs informations telles que le nom, l'altitude et la localisation géographique.
- Connexion à une base MongoDB pour le stockage des données.
- Logique métier centralisée dans un service Spring.

## Prérequis

- **Java 17** ou version supérieure
- **Maven** pour la gestion des dépendances
- Une instance de **MongoDB** en cours d'exécution

## Installation et exécution

1. Clonez le dépôt :

   ```bash
   git clone <URL-du-repo>
   cd sum-it-backend
2. Configurez l'accès à MongoDB dans le fichier `application.properties` :

   ```properties
   spring.data.mongodb.uri=mongodb://<username>:<password>@<host>:<port>/<database>
   ```

3. Compilez et lancez l'application :

   ```bash
   mvn spring-boot:run
   ```

4. Accédez à l'API via `http://localhost:8080`.

## Structure du projet

- **Contrôleurs** : Gestion des endpoints API dans `controllers/`.
- **Documents** : Modèles de données pour MongoDB dans `document/`.
- **Dépôts** : Interfaces pour les opérations avec MongoDB dans `repositories/`.
- **Services** : Contiennent la logique métier dans `services/`.

## Tests

Pour exécuter les tests unitaires :

```bash
mvn test
```

## Fonctionnalités futures

- Ajouter des validations supplémentaires sur les données d'entrée.
- Implémentation de la pagination pour les requêtes REST.
- Intégration avec d'autres API pour enrichir les données.
