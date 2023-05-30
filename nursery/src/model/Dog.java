package nursery.src.model;

import java.util.List;

public class Dog extends Pet {
    public Dog(String name, List<String> commands) {
        super(name, commands);
    }

    @Override
    public String toString() {
        String commandsStr = Mapper.mapListToStr(this.getCommands());
        return "Pet. Dog. Name: " + this.getName() + ". Commands: " + commandsStr;
    }
}
