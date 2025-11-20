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

Architecture du projet

L’application suit une architecture modulaire claire, organisée en trois couches :

1. Couche Data

Elle contient l’accès aux données :

appels à l’API TMDB avec Ktor Client

base de données Room (favoris et notes)

repositories qui centralisent la logique des sources de données

2. Couche ViewModel

Elle regroupe la logique métier de l’application.
Les ViewModels exposent les données sous forme de flux (StateFlow) à l’interface :

MovieViewModel

FavoriteViewModel

RatingViewModel

3. Couche UI (Jetpack Compose)

Elle comprend tous les écrans et la navigation :

HomeScreen

SearchScreen

DetailsScreen

FavoritesScreen

Navigation Compose est utilisé pour les transitions entre écrans.

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
