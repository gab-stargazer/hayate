<div align="center">
  <img src="/core/common/src/main/res/drawable/icon.png" alt="Octocat" />
</div>

# Hayate
Hayate is a mobile application developed in Kotlin for Android, serving as a quick anime guide. The app leverages the [Jikan API](https://jikan.moe/), a community-driven API dedicated to anime-related data.

## Features
Hayate currently consist of the following features:

### Explore Page
The Explore page serves as your gateway to the vibrant world of anime, offering a comprehensive view of what's happening in the anime community today:
1. **Today's Airing Anime**: Stay in the loop with the latest episodes airing today.
2. **Popular Anime**: Discover the hottest and most beloved anime series.
3. **This Season Airing**: Explore the exciting anime currently airing in the current season.
4. **Upcoming Season**: Get a sneak peek into what's on the horizon for the next season.
5. **Search Functionality**: Effortlessly find your favorite anime with the intuitive search feature.

### Collection Screen
The Collection screen provides a personalized space for managing your anime activities:
1. **Recently Viewed**: Quickly revisit the anime you've recently explored.
2. **Watchlist**: Keep track of the anime you're planning to watch in the future.

### Detail Screen
1. **Detailed Anime Information**: Explore comprehensive details about the selected anime.
2. **Add to Watchlist**: Personalize your anime journey by adding selected series to your watchlist.

## Tech Stack
Here's an overview of the technologies and libraries that power Hayate:
1. **Jetpack Compose**: Hayate uses Jetpack Compose for efficient and immersive UI development, aligning seamlessly with its user-friendly interface goals.
2. **Firebase**: Hayate uses Firebase for scalable backend services. Firebase Analytics provides insights, Remote Config adjusts configurations dynamically, and Crashlytics offers real-time crash reporting for enhanced stability.
3. **Dagger Hilt**: Hayate utilizes Dagger Hilt for clean and modular Android dependency injection. Hilt simplifies integration, enhancing code readability and scalability while facilitating future expansion.
4. **Retrofit**: Hayate relies on Retrofit for streamlined and type-safe HTTP requests, simplifying API communication and ensuring reliable data exchange for efficient anime-related information retrieval.
5. **Room**: Hayate relies on Room for seamless SQLite database integration in Android. Room simplifies data persistence, providing an efficient and structured approach for managing anime-related information locally.
6. **ViewModel**: Hayate utilizes ViewModel for efficient data management and a streamlined, user-friendly anime guide experience.
7. **Coroutine**: Hayate utilizes Kotlin Coroutines for streamlined asynchronous programming, enhancing code readability and performance in delivering an efficient anime guide experience.
8. **Moshi**: Hayate employs Moshi for efficient JSON parsing in Kotlin, simplifying data serialization and deserialization for managing anime-related information.
9. **DataStore Preferences**: Hayate improves data storage efficiency with DataStore Preferences on Android. It checks feature availability in real-time through remote configuration, simplifying the management of app settings for a smoother user experience.

##  Installation Guide
1. Ensure you have Android Studio installed.
2. Clone the Hayate repository to your local machine.
3. Run `git clone https://github.com/gab-stargazer/hayate.git`
4. Configure Firebase
    1. Obtain your google-services.json file from the **Firebase Console**.
    2. Place the google-services.json file in the app directory of the Hayate project.
5. Open Android Studio.
6. Click on "Open an Existing Android Studio Project."
7. Navigate to the cloned Hayate repository and select the build.gradle file in the root directory.
8. Click "OK" to open the project.
9. Click on the green triangle button (Run 'app') in the toolbar.
10. Select your preferred emulator or connect a physical device.
11. Click "OK" to build and run the app.  
