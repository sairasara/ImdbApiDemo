This is a demo Android application that allows users to browse and explore a curated a list of movies using the IMDb API and provides detailed information for each selected movie. The app is built using modern Android development tools and best practices.

<img width="317" alt="Screenshot 2025-05-11 at 11 08 54 AM" src="https://github.com/user-attachments/assets/5e860eca-f2d9-4165-8e49-50d249d49e58" />

<img width="317" alt="Screenshot 2025-05-11 at 11 09 11 AM" src="https://github.com/user-attachments/assets/9b515a73-5028-44fe-a267-cca2baef4196" />

<img width="299" alt="Screenshot 2025-05-11 at 11 07 18 AM" src="https://github.com/user-attachments/assets/7f89bd41-82bd-482c-8f03-27915b93da94" />

<img width="317" alt="Screenshot 2025-05-11 at 11 08 24 AM" src="https://github.com/user-attachments/assets/c180b9c7-a63d-49e1-9b63-28fc67d5de4c" />


**Features**

- List popular or trending movies
- View detailed information about a selected movie (e.g., title, description, rating, release date, etc.)
- Handles no internet scenarios with appropriate messages
- Includes unit and UI tests

**Tech Stack**

- Language: Kotlin
- Architecture: MVVM
- UI: Jetpack Compose
- Dependency Injection: Hilt
- Networking: Retrofit + OkHttp
- Asynchronous: Kotlin Coroutines + StateFlow
- Navigation: Jetpack Navigation Compose
- Image Loading: Coil

**Screens**

- Movie List Screen: Displays a scrollable list of movies with thumbnails and titles.
- Movie Detail Screen: Shows full details for the selected movie including poster, overview, release date, and ratings.
