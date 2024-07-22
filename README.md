# binsta

## Usage

- **Default Avatar**:
    - In the `HomeController.index`, `defaultAvatar.png` is used as the default avatar. Ensure that you have this image
      placed in the `resources/static/images` folder of your project.

## Seeder

- **Seeder Configuration**:
    - The seeder is enabled by default when the application starts. If you want to disable the seeder, you can change
      the `spring.profiles.active` property in your `application.properties` file to `prod`.

  ```properties
  spring.profiles.active=prod
    ```

- The BlogSeeder has a property named IMAGES, which contains the names of images stored in the resources/static/images
  directory. This list is used for seeding blog entries. Make sure that the images exist with the correct names, similar
  to UserSeeder.

 ```java

Class BlogSeeder {
    private static final List<String> IMAGES = Arrays.asList(
        "mountain.jpeg", "space.jpeg", "pinkBol.png", "sunflower.png", "rat.png", "panda.png", "tiger.png"
    );
}

```

```java

Class UserSeeder {
    
    private static final List<String> Avatars = Arrays.asList(
        "AvatarKids.png", "AvatarMan.png", "AvatarWoman.png"
    );
}
```
