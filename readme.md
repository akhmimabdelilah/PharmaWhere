# PharmaWhere

Le but de ce projet est de développer une interface web qui permet à l'utilisateur de trouver facilement les pharmacies de garde ouvertes dans une ville donnée en indiquant s'il cherche une pharmacie de jour ou de nuit. Pour cela, l'application utilise le framework Spring Boot côté serveur pour la gestion de la logique métier et de la base de données, ainsi que Spring Data pour faciliter l'accès et la manipulation des données. En ce qui concerne la partie client, l'interface utilise la bibliothèque jQuery et la technologie Ajax pour envoyer des requêtes HTTP au serveur et afficher les résultats de manière dynamique à l'utilisateur sans avoir à recharger la page.

## Table de matiere

- [PharmaWhere](#pharmawhere)
  - [Table de matiere](#table-de-matiere)
  - [Fonctionnalités](#fonctionnalités)
  - [Mise en place du projet](#mise-en-place-du-projet)
  - [Déploiement](#déploiement)
  - [Auteur](#auteur)

## Fonctionnalités

- Rechercher une pharmacie de garde en indiquant la ville et le type de garde (jour ou nuit).
- Afficher la liste des pharmacies de garde dans la ville sélectionnée, avec leurs coordonnées.
- Afficher les pharmacies de garde sur une carte interactive en utilisant une API de cartographie.
- Permettre à l'utilisateur de trouver la pharmacie de garde la plus proche de sa position en utilisant la géolocalisation.

## Mise en place du projet

Pour mettre en place ce projet, vous aurez besoin des éléments suivants :

- JVM
- Un SGBD (comme PostgreSQL)
- Un éditeur de code (comme IntellijIdea)
- Maven
- Spring Boot

## Déploiement

Pour déployer ce projet sur votre serveur d'application, suivez les étapes suivantes :

- Téléchargez le projet sur votre ordinateur
- Ouvrez-le dans votre éditeur de code
- Configurez la connexion à votre SGBD dans le fichier application.properties
- Exportez le projet en tant qu'archive JAR
- Déployez l'archive sur votre serveur d'application en suivant les instructions de votre serveur
- Ouvrez votre navigateur et accédez à l'application en entrant l'URL suivante : http://localhost:8090

## Auteur

Ce projet est realisé par SLILA Yazid, BAOUCHOUCH Saad et AIT EL MAMOUN Hamza
