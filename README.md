# Yassir Movies - Elie-José Maalouf

## About
In a nutshell, the application makes a few network calls to retrieve movie lists and movie details.

## Features
- Clicking on a movie from the list displays the movie details in a separate screen
- The list includes basic movie data
- The movie details screen includes the same data, plus an overview of the movie and some rating details.
![Capture](https://user-images.githubusercontent.com/49279528/167297820-0c9f04e4-f7c8-45f2-87ad-6181e3320a33.PNG)

## External Frameworks and Libraries
### Splash Screen (for Android 12)
- Android 12 requires that we no longer use our own Splash Activities to load the app. So this is used to dynamically load a splash screen and remove it once data is ready to be used by the application.
### Retrofit
- For network calls to retrieve movie data.
### Hilt
- For dependency injection
### Glide
- For loading images. Chosen because of straightforward and reliable use.

## Potential Improvements
- UI is not the prettiest and could do with an overhaul in theme.
- The movie details data is lacking and could use with some extra information.
- Transitions using sharedElements between the movie list and the movie details screens (for image, title, and release date), would make navigating between the two fragments more pretty.
- The architecture/packaging is done semi by layer, semi by feature. A bigger application of this kind could do with the use of the Clean Architecture, but due to the very small and simple scale, I have decided to simply go for a regular MVVM pattern with Repos and Models.
