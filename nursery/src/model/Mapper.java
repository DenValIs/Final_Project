package nursery.src.model;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public String map(Animal animal) {

        String commandsStr = mapListToStr(animal.getCommands());

        if (animal instanceof Dog) {
            return String.format("%s;%s;%d", animal.getName(), commandsStr, 1);
        } else if (animal instanceof Cat) {
            return String.format("%s;%s;%d", animal.getName(), commandsStr, 2);
        } else if (animal instanceof Hamster) {
            return String.format("%s;%s;%d", animal.getName(), commandsStr, 3);
        } else if (animal instanceof Horse) {
            return String.format("%s;%s;%d", animal.getName(), commandsStr, 4);
        } else if (animal instanceof Camel) {
            return String.format("%s;%s;%d", animal.getName(), commandsStr, 5);
        } else if (animal instanceof Donkey) {
            return String.format("%s;%s;%d", animal.getName(), commandsStr, 6);
        } else {
            throw new IllegalArgumentException("Internal error! Object Animal have unknown type");
        }

    }

    public static String mapListToStr(List<String> strings) {
        StringBuilder result = new StringBuilder();
        for (String string : strings) {
            if (!string.isEmpty()) {
                result.append(string);
                result.append(", ");
            }
        }
        if (result.length() != 0) {
            result.setLength(result.length() - 2);
        }
        return String.valueOf(result);
    }

    public Animal map(String line) {
        String[] lines = line.split(";");
        if (lines.length < 3) {
            throw new IllegalArgumentException("Invalid format of data in animals.scv file!");
        }
        String[] lineCommands = lines[1].split(", ");
        List<String> listCommands = new ArrayList<>();
        for (String command : lineCommands) {
            listCommands.add(command);
        }
        try {
            switch(lines[2]) {
                case ("1"):
                    return new Dog(lines[0], listCommands);
                case ("2"):
                    return new Cat(lines[0], listCommands);
                case ("3"):
                    return new Hamster(lines[0], listCommands);
                case ("4"):
                    return new Horse(lines[0], listCommands);
                case ("5"):
                    return new Camel(lines[0], listCommands);
                case ("6"):
                    return new Donkey(lines[0], listCommands);
                default:
                    throw new IllegalArgumentException("Invalid format of data in animals.scv file!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of data in animals.scv file!");
        }
        return null;
    }
}
