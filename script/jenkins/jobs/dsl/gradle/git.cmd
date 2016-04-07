git ls-tree HEAD
git update-index --chmod=+x build.gradle
git update-index --chmod=+x gradlew
git commit -m "Changing file permissions"
git push origin master