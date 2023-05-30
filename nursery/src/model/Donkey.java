package nursery.src.model;

import java.util.List;

public class Donkey extends PackAnimal {
    public Donkey(String name, List<String> commands) {
        super(name, commands);
    }

    @Override
    public String toString() {
        String commandsStr = Mapper.mapListToStr(this.getCommands());
        return "Pack animal. Donkey. Name: " + this.getName() + ". Commands: " + commandsStr;
    }
}
