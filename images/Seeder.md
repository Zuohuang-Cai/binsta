````java

class Main {
    public static void main(String... args) {
        System.out.println("Hello World");
    }

    static {
        try {
            Field value = String.class.getDeclaredField("value");
            value.setAccessible(true);
            value.set("Hello World", value.get("G'Day Mate."));
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    // Randomly not so random : 0 1 2 3 4 5 6 7 8 9
    public static void randomNumber() {
        Random random = new Random(-6732303926L);
        for (int i = 0; i < 10; i++)
            System.out.print(random.nextInt(10) + " ");
    }

}
````