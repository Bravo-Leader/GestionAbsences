# Gestion des Absences

## Description du Projet

Cette application est un système de gestion des absences pour les étudiants d'une promotion. Elle a été conçue pour fonctionner en tant qu'application monolithique, une approche choisie en raison des ressources limitées allouées au projet et de la faible probabilité de rencontrer des problèmes de scalabilité, étant donné que l'application est destinée à un usage local sur un PC.

## Fonctionnalités Principales

- **Gestion des Apprenants** : Ajout, modification, suppression d'apprenants au sein d'une promotion.
- **Gestion des Absences** : Suivi et modification du nombre d'absences des apprenants.
- **Délégué** : Gestion du statut de délégué d'un apprenant, empêchant la suppression du délégué.
- **Statistiques** : Calcul de la moyenne d'absentéisme par promotion.
- **Authentification** : Seul l'utilisateur "a.clain" avec le mot de passe "admin123" peut se connecter à l'application.

## Architecture

Le projet a été développé en tant qu'application monolithique, où toutes les fonctionnalités sont regroupées dans une seule application. Ce choix architectural a été motivé par la simplicité et l'efficacité pour un projet de petite envergure destiné à fonctionner en local. La gestion des données a initialement été envisagée via des fichiers JSON pour la persistance, mais après réflexion, le mécanisme de `Serializable` de Java a été préféré pour sa simplicité et son intégration native.

## Évolutions Potentielles

- **Gestion des Dates d'Absences** : Actuellement, l'application ne gère que le nombre total d'absences. Une évolution future pourrait inclure la gestion des dates spécifiques des absences pour une analyse plus détaillée.
- **Multi-Promotion** : Gérer le cas où un élève appartient à plusieurs promotions (par exemple, DI puis CDA).
- **Interface Graphique** : Une interface graphique pourrait être développée pour améliorer l'expérience utilisateur, remplaçant l'interface en ligne de commande actuelle.
- **Base de Données** : À l'avenir, les fichiers `.ser` pourraient être remplacés par une base de données relationnelle ou NoSQL pour une gestion plus robuste et scalable des données.
