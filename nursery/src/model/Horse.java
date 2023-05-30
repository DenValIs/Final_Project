package nursery.src.model;

import java.util.List;

public class Horse extends PackAnimal {
    public Horse(String name, List<String> commands) {
        super(name, commands);
    }

    @Override
    public String toString() {
        String commandsStr = Mapper.mapListToStr(this.getCommands());
        return "Pack animal. Horse. Name: " + this.getName() + ". Commands: " + commandsStr;
    }
}
