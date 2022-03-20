# Palette App

![GooglePlayStore](https://user-images.githubusercontent.com/13314984/159171617-57360258-4a0c-493e-b75f-a01dd24ef165.png)

The app that let you capture the colors of your surrounding.

## App Design
You can check the app design [here](https://www.figma.com/file/OBptiQdn5sc75R4K0ChFB6/Palette-Material-Theme).

## Libraries Used
* [CameraX](https://developer.android.com/training/camerax) capturing colors.
* [SQLDelight](https://cashapp.github.io/sqldelight/android_sqlite/) Database.
* [Jetpack compose](https://developer.android.com/jetpack/androidx/releases/compose) for UI.
* [Compose Destination](https://github.com/raamcosta/compose-destinations) for navigation.
* [Hilt](https://dagger.dev/hilt/) Dependency Injection.
* [Mockk](https://github.com/mockk/mockk) Testing.
* [Ktlint](/documentation/StaticAnalysis.md) for formatting.
* [Gradle Versions Plugin](/documentation/VersionsPlugin.md) for checking all dependencies for new versions.
* [GitHub Actions](/documentation/GitHubActions.md) for running continuous integration and ensuring code quality with every PR. 



## Dependency Setup

You may notice that dependencies are set up in a very specific way. Each of the tools has its own Gradle file in the [buildscripts folder](/buildscripts). This is by design so that if you chose to have a multi module project, these dependencies can easily be shared between them. This is already configured inside our root `build.gradle` file, by applying to each sub project:

```groovy
subprojects {
    apply from: "../buildscripts/ktlint.gradle"
    apply from: "../buildscripts/versionsplugin.gradle"
}
```

In addition, there is a [versions.gradle](/buildscripts/versions.gradle) file which includes the version numbers of all dependencies used inside the app module. The benefit of moving them here, is that if any dependencies are shared between two modules, we only have to update the version number in one spot. As an added bonus, each dependency version also has a comment linking to the release page, so you can quickly reference to see what's changed. 

