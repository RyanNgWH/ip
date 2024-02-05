package duke;

import java.io.File;

import duke.storage.TaskList;
import duke.ui.Gui;
import duke.ui.Ui;

/**
 * The Duke class provides the Duke chatbot application
 *
 * @author Ryan NgWH
 */
public class Duke {
    /**
     * File to save storage to
     */
    public static final File SAVE_FILE = new File("data/tasks.json");

    /**
     * Task list for the Duke instance
     */
    private TaskList taskList;

    /**
     * UI of the Duke instance
     */
    private Ui ui;

    /**
     * Create a Duke instance
     *
     * @param file File to save and load tasks
     */
    public Duke(File file) {
        this.ui = new Gui();

        // Create data directory (if required)
        file.getParentFile().mkdirs();

        this.taskList = new TaskList(file);
    }

    /**
     * Run the Duke instance
     *
     * @param args Arguments to pass to the application
     */
    public void run(String[] args) {
        this.ui.startUI(this.taskList, args);
    }

    public static void main(String[] args) {
        new Duke(SAVE_FILE).run(args);
    }
}
