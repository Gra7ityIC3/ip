package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;

/**
 * Represents an abstract command to add a task to the task list.
 * Concrete subclasses should implement the {@link #createTask(String)} method
 * to define how the task is created based on the user's input.
 */
public abstract class AddCommand extends Command {
    private final String args;

    /**
     * Constructs an {@code AddCommand} with the specified arguments.
     *
     * @param args The string containing the task details to be added.
     */
    public AddCommand(String args) {
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws MorganaException {
        Task task = createTask(args);
        tasks.add(task);
        storage.save(tasks);
        return """
                Got it. I've added this task:
                %s
                Now you have %d task%s in the list.
                """.formatted(task, tasks.size(), tasks.size() > 1 ? "s" : "");
    }

    /**
     * Creates a task based on the provided arguments.
     *
     * @param args The string containing the task details.
     * @return The created {@code Task}.
     * @throws MorganaException If an error occurs while creating the task.
     */
    abstract Task createTask(String args) throws MorganaException;

    @Override
    public String getStyleClass() {
        return "add-label";
    }
}
