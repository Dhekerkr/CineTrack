CineTrack — Application Android

CineTrack est une application Android moderne permettant de rechercher des films, consulter leurs informations, attribuer une note personnalisée et gérer une liste de favoris.
Elle est développée en Kotlin avec Jetpack Compose, Ktor Client pour les requêtes réseau, et Room pour la persistance locale.

Fonctionnalités principales
Recherche de films

L’application permet d’effectuer une recherche de films via l’API de TheMovieDB.
Les résultats sont affichés instantanément avec l’affiche, le titre et un résumé.

Détails d’un film

Chaque fiche film contient :

une image en haute définition

le titre

la description

la note moyenne donnée par l’utilisateur

le casting

un bouton pour ajouter ou retirer le film des favoris

un système de notation de 1 à 5 (stocké localement)

Gestion des favoris

L’utilisateur peut enregistrer des films dans une liste de favoris.
Cette liste est disponible dans un écran dédié, avec possibilité de retirer un film à tout moment.

Système de notation locale

L’utilisateur peut attribuer une note à chaque film.
Ces notes sont enregistrées en base de données Room et la moyenne est recalculée automatiquement lors de l’affichage des détails du film.

Page d’accueil

L’accueil propose :

des suggestions de films populaires

une barre de recherche animée

une interface moderne et responsive entièrement construite avec Jetpack Compose

Architecture du projet — MVVM

L’application suit une architecture MVVM (Model – View – ViewModel), recommandée par Google pour les applications Android modernes.
Cette structure permet une séparation claire des responsabilités, une meilleure maintenabilité et un code plus testable.

1) Model (M) — Gestion des données

Cette couche regroupe tout ce qui concerne la récupération, le stockage et la transformation des données.

API TMDB via Ktor Client
Gère les appels réseau et la récupération des films, détails et castings.

Base de données Room
Stocke localement les favoris et les notes attribuées par l’utilisateur.

Repositories
(MovieRepository, RatingRepository)
Servent d'intermédiaires entre les ViewModels et les sources de données (API + Room).

2) ViewModel (VM) — Logique métier

Les ViewModels assurent la logique de l’application et exposent les données sous forme de StateFlow pour l’interface :

MovieViewModel — Films, recherches et détails

FavoriteViewModel — Gestion des favoris

RatingViewModel — Gestion et calcul des notes

Ils ne dépendent pas de la View et survivent aux changements de configuration.

3) View (V) — Interface utilisateur avec Jetpack Compose

Les écrans (HomeScreen, SearchScreen, DetailsScreen, FavoritesScreen) observent les flux d’état issus des ViewModels.
Ils affichent les données, déclenchent des actions utilisateur, mais ne contiennent aucune logique métier.

Résultat : une application propre, scalable et facile à maintenir

Les Views sont simples et réactives.

Les ViewModels centralisent la logique.

Les Repositories gèrent toutes les sources de données.

L’application reste modulaire et extensible.
Technologies utilisées

Kotlin

Jetpack Compose

Ktor Client

Room Database

Navigation Compose

Coil (chargement d’images)

StateFlow & Coroutines

Installation

Cloner le dépôt GitHub.

Ajouter une clé TMDB valide dans le fichier APIServiceKtor ou dans la configuration réseau.

Lancer l’application sur un émulateur Android (SDK 26 ou supérieur).

Compiler et exécuter depuis Android Studio.

Auteur

Projet réalisé par Dheker Kraiem dans le cadre du cours "Atelier Android"
