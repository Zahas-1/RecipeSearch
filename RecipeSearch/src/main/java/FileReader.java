import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    // Define an ArrayList to store Recipe objects
    private static ArrayList<Recipe> recipes = new ArrayList<>();
    Scanner scan = new Scanner(System.in);


    // Method to read recipes from the file
    public static void readRecipes() {

        try (Scanner scanner = new Scanner(Paths.get("recipe.txt"))) { // Open the file for reading
            while (scanner.hasNextLine()) { // Continue reading until the end of file is reached
                String name = scanner.nextLine(); // Read recipe name
                int cookingTime = Integer.parseInt(scanner.nextLine()); // Read cooking time
                List<String> ingredients = new ArrayList<>(); // Create a list to store ingredients
                while (scanner.hasNextLine()) { // Continue reading until end of recipe section
                    String ingredient = scanner.nextLine(); // Read each ingredient
                    if (ingredient.isEmpty()) { // Check if empty line is encountered indicating end of recipe
                        break; // Exit the loop
                    }
                    ingredients.add(ingredient); // Add ingredient to the list
                }
                Recipe recipe = new Recipe(name, cookingTime, ingredients); // Create a Recipe object
                recipes.add(recipe); // Add the recipe to the recipes list
            }
        } catch (Exception e) { // Handle any exceptions
            System.out.println("An error occurred while reading recipes: " + e.getMessage()); // Display error message
        }
    }

    // Method to list all recipes
    public static void listRecipes() {
        System.out.println("\nRecipes:"); // Print header
        for (Recipe recipe : recipes) { // Iterate over each recipe in the recipes list
            System.out.println(recipe); // Print recipe details
        }
        System.out.println(); // Print empty line for better formatting
    }

    // Method to find recipes by name
    public static void findByName(Scanner scanner) {
        System.out.print("Enter the name to search: "); // Prompt user to enter search keyword
        String keyword = scanner.nextLine(); // Read user input
        System.out.println("\nRecipes with name containing '" + keyword + "':"); // Print search header
        boolean found = false; // Initialize flag to track if any recipe is found
        for (Recipe recipe : recipes) { // Iterate over each recipe in the recipes list
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) { // Check if recipe name contains the keyword
                System.out.println(recipe); // Print the recipe details
                found = true; // Set flag to true indicating recipe found
            }
        }
        if (!found) { // If no recipe found
            System.out.println("No recipes found with name containing '" + keyword + "'."); // Print message
        }
        System.out.println(); // Print empty line for better formatting
    }

    // Method to find recipes by cooking time
    public static void findByCookingTime(Scanner scanner) {
        System.out.print("Enter the maximum cooking time: "); // Prompt user to enter maximum cooking time
        int maxTime = Integer.parseInt(scanner.nextLine()); // Read user input
        System.out.println("\nRecipes with cooking time less than or equal to " + maxTime + " minutes:"); // Print search header
        boolean found = false; // Initialize flag to track if any recipe is found
        for (Recipe recipe : recipes) { // Iterate over each recipe in the recipes list
            if (recipe.getCookingTime() <= maxTime) { // Check if recipe cooking time is less than or equal to maximum time
                System.out.println(recipe); // Print the recipe details
                found = true; // Set flag to true indicating recipe found
            }
        }
        if (!found) { // If no recipe found
            System.out.println("No recipes found with cooking time less than or equal to " + maxTime + " minutes."); // Print message
        }
        System.out.println(); // Print empty line for better formatting
    }

    // Method to find recipes by ingredient
    public static void findByIngredient(Scanner scanner) {
        System.out.print("Enter the ingredient to search: "); // Prompt user to enter ingredient to search
        String ingredient = scanner.nextLine(); // Read user input
        System.out.println("\nRecipes containing '" + ingredient + "':"); // Print search header
        boolean found = false; // Initialize flag to track if any recipe is found
        for (Recipe recipe : recipes) { // Iterate over each recipe in the recipes list
            for (String ingr : recipe.getIngredients()) { // Iterate over each ingredient in the recipe
                if (ingr.toLowerCase().contains(ingredient.toLowerCase())) { // Check if ingredient contains the search term
                    System.out.println(recipe); // Print the recipe details
                    found = true; // Set flag to true indicating recipe found
                    break; // Exit inner loop
                }
            }
        }
        if (!found) { // If no recipe found
            System.out.println("No recipes found containing '" + ingredient + "'."); // Print message
        }
        System.out.println(); // Print empty line for better formatting
    }
}
