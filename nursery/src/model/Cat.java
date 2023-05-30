package nursery.src.model;

import java.util.List;

public class Cat extends Pet {
    public Cat(String name, List<String> commands) {
        super(name, commands);
    }

    @Override
    public String toString() {
        String commandsStr = Mapper.mapListToStr(this.getCommands());
        return "Pet. Cat. Name: " + this.getName() + ". Commands: " + commandsStr;
    }
}
