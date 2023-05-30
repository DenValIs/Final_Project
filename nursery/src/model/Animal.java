package nursery.src.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    private final String name;
    private List<String> commands = new ArrayList<>();

    public Animal(String name) {
        this.name = name;
    }

    public Animal(String name, List<String> commands) {
        this.name = name;
        this.commands = commands;
    }

    public void addCommands(List<String> commands) {
        this.commands.addAll(commands);
    }

    public String getName() {
        return name;
    }

    public List<String> getCommands() {
        return commands;
    }
}
