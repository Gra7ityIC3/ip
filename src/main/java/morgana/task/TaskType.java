package morgana.task;

/**
 * Represents the types of tasks that can be created.
 */
public enum TaskType {
    TODO, DEADLINE, EVENT;

    @Override
    public String toString() {
        return name().substring(0, 1);
    }
}
