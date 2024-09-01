package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.task.Deadline;
import morgana.task.Task;

/**
 * Represents a command to add a {@link Deadline} to the task list.
 */
public class DeadlineCommand extends AddCommand {
    /**
     * Constructs a {@code DeadlineCommand} with the specified arguments.
     *
     * @param args The string containing the task description and deadline.
     */
    public DeadlineCommand(String args) {
        super(args);
    }

    @Override
    Task createTask(String args) throws MorganaException {
        String[] parts = args.split(" /by ");
        if (parts.length != 2) {
            throw new MorganaException("""
                    Invalid deadline format.
                    Usage: deadline <description> /by <yyyy-MM-dd HHmm>
                    Example: deadline return book /by 2019-10-15 1800""");
        }
        return new Deadline(parts[0], Parser.parseDateTime(parts[1]));
    }
}
