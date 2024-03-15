import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {
    private static ArrayList<Recipe> recipes = new ArrayList<>();

    public static void main(String[] args) {
        // Read recipes from the file provided by the user
        readRecipes();

        // Implement a loop to interact with the user
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("File to read: ");
            String file = scanner.nextLine();
            if (file.equals("stop")) {
                break;
            }

            System.out.println("\nCommands:");
            System.out.println("list - lists the recipes");
            System.out.println("stop - stops the program");
            System.out.println("find name - searches recipes by name");
            System.out.println("find cooking time - searches recipes by cooking time");
            System.out.println("find ingredient - searches recipes by ingredient\n");

            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "list":
                    listRecipes();
                    break;
                case "find name":
                    findByName(scanner);
                    break;
                case "find cooking time":
                    findByCookingTime(scanner);
                    break;
                case "find ingredient":
                    findByIngredient(scanner);
                    break;
                case "stop":
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    // Method to read recipes from the file
    private static void readRecipes() {
        try (Scanner scanner = new Scanner(Paths.get("recipe.txt"))) {
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                int cookingTime = Integer.parseInt(scanner.nextLine());
                List<String> ingredients = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String ingredient = scanner.nextLine();
                    if (ingredient.isEmpty()) {
                        break;
                    }
                    ingredients.add(ingredient);
                }
                Recipe recipe = new Recipe(name, cookingTime, ingredients);
                recipes.add(recipe);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading recipes: " + e.getMessage());
        }
    }

    // Method to list all recipes
    private static void listRecipes() {
        System.out.println("\nRecipes:");
        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
        System.out.println();
    }

    // Method to find recipes by name
    private static void findByName(Scanner scanner) {
        System.out.print("Enter the name to search: ");
        String keyword = scanner.nextLine();
        System.out.println("\nRecipes with name containing '" + keyword + "':");
        boolean found = false;
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(recipe);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No recipes found with name containing '" + keyword + "'.");
        }
        System.out.println();
    }

    // Method to find recipes by cooking time
    private static void findByCookingTime(Scanner scanner) {
        System.out.print("Enter the maximum cooking time: ");
        int maxTime = Integer.parseInt(scanner.nextLine());
        System.out.println("\nRecipes with cooking time less than or equal to " + maxTime + " minutes:");
        boolean found = false;
        for (Recipe recipe : recipes) {
            if (recipe.getCookingTime() <= maxTime) {
                System.out.println(recipe);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No recipes found with cooking time less than or equal to " + maxTime + " minutes.");
        }
        System.out.println();
    }

    // Method to find recipes by ingredient
    private static void findByIngredient(Scanner scanner) {
        System.out.print("Enter the ingredient to search: ");
        String ingredient = scanner.nextLine();
        System.out.println("\nRecipes containing '" + ingredient + "':");
        boolean found = false;
        for (Recipe recipe : recipes) {
            for (String ingr : recipe.getIngredients()) {
                if (ingr.toLowerCase().contains(ingredient.toLowerCase())) {
                    System.out.println(recipe);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No recipes found containing '" + ingredient + "'.");
        }
        System.out.println();
    }
}
