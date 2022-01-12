# Git Hooks

This project has some Git hooks included inside the [git-hooks](/git-hooks) folder. These hooks can be installed automatically via the Gradle commands `./gradlew copyGitHooks` and `./gradlew installGitHooks`. You can find these commands in [this Gradle file](/buildscripts/githooks.gradle), but it's also good to know that the hooks are installed automatically just by running a `clean` task. Thanks to [Sebastiano's blog post](https://blog.sebastiano.dev/ooga-chaka-git-hooks-to-enforce-code-quality/) for that inspiration.

## Pre-Commit

There is a [pre-commit](/git-hooks/pre-commit.sh) hook that will automatically run Ktlint formatting over any modified Kotlin files. This way you can just commit your code and trust that formatting happens behind the scenes, without having to consciously worry about it.