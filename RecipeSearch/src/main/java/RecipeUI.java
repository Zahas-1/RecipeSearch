import java.util.Scanner;

public class RecipeUI {
    public static void main(String[] args) {
        // Read recipes from the file provided by the user
        FileReader.readRecipes();

        // Implement a loop to interact with the user
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Prompt the user to enter the file name or 'stop' to exit
            System.out.println("File to read: ");
            String file = scanner.nextLine();
            if (file.equals("stop")) {
                break;
            }

            // Display available commands
            System.out.println("\nCommands:");
            System.out.println("list - lists the recipes");
            System.out.println("stop - stops the program");
            System.out.println("find name - searches recipes by name");
            System.out.println("find cooking time - searches recipes by cooking time");
            System.out.println("find ingredient - searches recipes by ingredient\n");

            // Prompt the user to enter a command
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            // Execute the command based on user input
            switch (command) {
                case "list":
                    FileReader.listRecipes(); // List all recipes
                    break;
                case "find name":
                    FileReader.findByName(scanner); // Find recipes by name
                    break;
                case "find cooking time":
                    FileReader.findByCookingTime(scanner); // Find recipes by cooking time
                    break;
                case "find ingredient":
                    FileReader.findByIngredient(scanner); // Find recipes by ingredient
                    break;
                case "stop":
                    break;
                default:
                    System.out.println("Invalid command. Please try again."); // Display error for invalid command
                    break;
            }
        }
        scanner.close(); // Close the scanner
    }
}
