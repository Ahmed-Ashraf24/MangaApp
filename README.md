# MangaApp

MangaApp is an Android application designed to provide users with a seamless experience for browsing and reading manga. The app is built using the Model-View-ViewModel (MVVM) architecture and Clean Architecture principles, ensuring a robust and maintainable codebase.

## Features

- **User Authentication**: Secure user authentication using Firebase Authentication.
- **Manga Browsing**: Browse a vast collection of manga titles with detailed information.
- **Manga Reading**: Enjoy reading manga with a user-friendly interface.
- **Favorites Management**: Save your favorite manga to your profile using Firestore.
- **User History Tracking**: Automatically record and maintain a history list of manga you’ve opened, stored in Firestore.

## Architecture

MangaApp utilizes the following technologies and architectural components:

- **MVVM Architecture**: Separates the app's data layer from the UI layer, promoting a clear separation of concerns.
- **Clean Architecture**: Ensures a scalable and testable codebase by organizing the project into distinct layers.
- **Room Database**: Used to store simple user data for local login and registration.
- **Firebase Authentication**: Manages user authentication securely.
- **Firestore**: Serves as the primary online data source for manga information.
- **Retrofit**: Handles network requests to external APIs for additional manga data.

## Getting Started

To get a local copy of MangaApp up and running, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Ahmed-Ashraf24/MangaApp.git
