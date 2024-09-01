package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;
import morgana.ui.Ui;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final String args;

    /**
     * Constructs a {@code UnmarkCommand} with the specified arguments.
     *
     * @param args The string containing the task index to be marked as not done.
     */
    public UnmarkCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MorganaException {
        int index = Parser.parseTaskIndex(args, tasks);
        Task task = tasks.get(index);
        task.markAsDone(false);
        ui.showToUser(
                "OK, I've marked this task as not done yet:",
                "%d. %s".formatted(index + 1, task));
        storage.save(tasks);
    }
}
