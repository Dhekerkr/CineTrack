📌 README.md — CineTrack
🎬 CineTrack

Application Android moderne permettant de rechercher des films, consulter leurs détails, ajouter des favoris et attribuer une note personnelle.
Développée avec Kotlin, Jetpack Compose, Ktor Client, et Room.

✨ Fonctionnalités principales
🔍 Recherche de films

L’application permet de rechercher n’importe quel film via l’API TheMovieDB.
Les résultats apparaissent immédiatement avec leur affiche et leur résumé.

🎥 Détails d’un film

Pour chaque film, l’écran détail présente :

L’affiche HD

Le titre

Le résumé

La moyenne des notes données par l’utilisateur

Le casting

La possibilité d’ajouter/supprimer le film des favoris

La possibilité de donner une note (stockée localement)

❤️ Gestion des favoris

Le film peut être ajouté ou retiré des favoris.
Tous les favoris sont visibles dans une page dédiée.

⭐ Système de notation (local avec Room)

L’utilisateur peut attribuer une note de 1 à 5.
La moyenne affichée est recalculée automatiquement.

🏠 Page d’accueil moderne

Suggestions de films populaires

Barre de recherche animée

Design moderne et responsive

🧱 Architecture du projet

Le projet suit une architecture claire en trois couches :

1) Data Layer

Contient tout ce qui touche aux données :

API TMDB via Ktor Client

Base locale Room : favoris + notes

Repositories servant d’intermédiaires

2) ViewModel Layer

MovieViewModel

FavoriteViewModel

RatingViewModel

Ils gèrent la logique métier et exposent des flux d’état (StateFlow) à l’UI.

3) UI Layer (Jetpack Compose)

4 écrans principaux :

HomeScreen

SearchScreen

DetailsScreen

FavoritesScreen

L’application utilise Navigation Compose pour passer d’un écran à l’autre.

🔧 Technologies utilisées
Technologie	Rôle
Kotlin	Langage principal
Jetpack Compose	Interface moderne
Ktor Client	Requêtes API
Room	Base de données locale
Navigation Compose	Transitions entre écrans
Coil	Chargement d’images
StateFlow / Coroutines	Gestion asynchrone
📦 Installation

Cloner le projet

Ajouter votre clé API TMDB dans MovieRepository

Lancer sur un émulateur Android (SDK 26+)

Build & Run

👨‍💻 Développeurs

Projet réalisé par Dheker Kraiem dans le cadre du cours "Atelier Android"
