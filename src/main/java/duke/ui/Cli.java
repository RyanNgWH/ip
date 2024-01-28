package duke.ui;

import java.util.Scanner;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.TaskList;

/**
 * The UI CLI class handles the displaying of UI elements in
 * the application
 *
 * @author Ryan NgWH
 */
public class Cli extends Ui {
    /**
     * Shared scanner for user input
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Displays the CLI UI of the application
     */
    @Override
    public void startUI(TaskList taskList) {
        // Print greeting
        printGreeting();

        boolean isExit = false;

        while (!isExit) {
            try {
                // Get user input
                String input = readCommand();
                printDividerLine();

                // Parse user input
                Command command = Parser.parse(input);

                // Execute command
                command.execute(taskList, this);

                // Exit (if applicable)
                isExit = command.isExit();
            } catch (DukeException e) {
                printError(e.getMessage());
            } finally {
                printDividerLine();
            }
        }

        sc.close();

        // case "unmark": // Unmark item
        // if (splitInput.length <= 1) {
        // throw new MissingArgumentException("Missing argument - Index of task
        // required");
        // }

        // // Print success message
        // System.out.println("OK, I've marked this task as not done yet:");
        // Task unmarkedTask = taskList.unmarkTask(Integer.parseInt(splitInput[1]) - 1);
        // System.out.println(String.format(" %s", unmarkedTask.toString()));
        // break;

        // case "delete": // Delete item
        // if (splitInput.length <= 1) {
        // throw new MissingArgumentException("Missing argument - Index of task
        // required");
        // }

        // // Print success message
        // System.out.println("Noted. I've removed this task:");
        // Task deletedTask = taskList.deleteTask(Integer.parseInt(splitInput[1]) - 1);
        // System.out.println(String.format(" %s", deletedTask.toString()));
        // System.out.println(String.format("Now you have %d tasks in the list.",
        // taskList.size()));

        // break;

        // default: // Store and echo items
        // // Store item
        // taskList.addTask(splitInput[0], Arrays.copyOfRange(splitInput, 1,
        // splitInput.length));
        // break;
        // }
        // } catch (Exception exception) {
        // System.out.println(String.format("ERROR: %s", exception.getMessage()));
        // }

        // System.out.println("------------------------------------------------------------");
        // } while (!input.equals("bye"));
    }

    /**
     * Print warning on standard output when application fails to load tasks from
     * file
     */
    public static void printLoadFromFileWarning() {
        System.out.println("WARNING: Failed to load from file, starting with empty list");
    }

    /**
     * Get user input and return a complete command for the application
     */
    private String readCommand() {
        return sc.nextLine();
    }

    /**
     * Print greeting on standard output
     */
    private void printGreeting() {
        // UI Greeting
        String greeting = "Hello! I'm Ciara\n"
                + "What can I do for you?";

        // Display greeting
        printDividerLine();
        System.out.println(greeting);
        printDividerLine();
    }

    /**
     * Print divider line on standard output
     */
    private void printDividerLine() {
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Print error on standard output
     */
    private void printError(String error) {
        System.out.println(String.format("ERROR: %s", error));
    }
}
