package nursery.src.view;

import nursery.src.controller.Controller;
import nursery.src.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private final Controller controller;
    private final Counter counter = new Counter();

    public View(Controller controller) {
        this.controller = controller;
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    public void run() {

        while (true) {
            System.out.println("1. View all animals in nursery");
            System.out.println("2. Add new animal");
            System.out.println("3. View animal commands");
            System.out.println("4. Teach the animal new commands");
            System.out.println("5. Exit");
            String input = prompt("Select an action: ");

            String name;
            String answer;
            List<String> commands = new ArrayList<>();

            switch (input) {
                case ("5"):
                    return;
                case("1"):
                    System.out.println();
                    controller.readAnimals().forEach(System.out::println);
                    System.out.println();
                    break;
                case ("2"):
                    System.out.println("1. Add pet");
                    System.out.println("2. Add pack animal");
                    String animalKind = prompt("Select an action: ");
                    int animalIndex;
                    while (!(animalKind.equals("1") || animalKind.equals("2"))) {
                        System.out.println("Bad command!");
                        System.out.println("1. Add pet");
                        System.out.println("2. Add pack animal");
                        animalKind = prompt("Select an action: ");
                    }
                    if (animalKind.equals("1")) {
                        System.out.println("1. Add dog");
                        System.out.println("2. Add cat");
                        System.out.println("3. Add hamster");
                        String animalType = prompt("Select an action: ");
                        while (!(animalType.equals("1") || animalType.equals("2") || animalType.equals("3"))) {
                            System.out.println("Bad command!");
                            System.out.println("1. Add dog");
                            System.out.println("2. Add cat");
                            System.out.println("3. Add hamster");
                            animalType = prompt("Select an action: ");
                        }
                        if (animalType.equals("1"))         animalIndex = 1;
                        else if (animalType.equals("2"))    animalIndex = 2;
                        else                                animalIndex = 3;
                    } else {
                        System.out.println("1. Add horse");
                        System.out.println("2. Add camel");
                        System.out.println("3. Add donkey");
                        String animalType = prompt("Select an action: ");
                        while (!(animalType.equals("1") || animalType.equals("2") || animalType.equals("3"))) {
                            System.out.println("Bad command!");
                            System.out.println("1. Add horse");
                            System.out.println("2. Add camel");
                            System.out.println("3. Add donkey");
                            animalType = prompt("Select an action: ");
                        }
                        if (animalType.equals("1"))         animalIndex = 4;
                        else if (animalType.equals("2"))    animalIndex = 5;
                        else                                animalIndex = 6;
                    }
                    name = prompt("Enter animal name: ");
                    while (name.isEmpty()) {
                        System.out.println("Error! Field is empty!");
                        name = prompt("Enter animal name: ");
                    }

                    do {
                        String command = prompt("Enter animal command: ");
                        commands.add(command);
                        answer = prompt("Add another animal command? (y/n): ");
                    } while (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes"));
                    try (Counter count = counter.start()) {
                        switch (animalIndex) {
                            case (1):
                                controller.saveAnimal(new Dog(name, commands));
                                break;
                            case(2):
                                controller.saveAnimal(new Cat(name, commands));
                                break;
                            case(3):
                                controller.saveAnimal(new Hamster(name, commands));
                                break;
                            case(4):
                                controller.saveAnimal(new Horse(name, commands));
                                break;
                            case(5):
                                controller.saveAnimal(new Camel(name, commands));
                                break;
                            case(6):
                                controller.saveAnimal(new Donkey(name, commands));
                                break;
                            default:
                                System.out.println("Internal error!");
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    counter.add();
                    break;
                case ("3"):
                    name = prompt("Enter animal name: ");
                    if (!controller.checkAnimal(name)) {
                        System.out.println("Animal not found!");
                        break;
                    }
                    try {
                        System.out.print("Commands: ");
                        System.out.println(Mapper.mapListToStr(controller.readAnimal(name).getCommands()));
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case ("4"):
                    name = prompt("Enter animal name: ");
                    if (!controller.checkAnimal(name)) {
                        System.out.println("Animal not found!");
                        break;
                    }
                    do {
                        String command = prompt("Enter animal command: ");
                        commands.add(command);
                        answer = prompt("Add another animal command? (y/n): ");
                    } while (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes"));
                    controller.teachNewCommand(name, commands);
                    break;
                default:
                    System.out.println("Unknown command!");
                    break;
            }
        }
    }

    private static class Counter implements AutoCloseable {
        private int count = 0;

        public Counter start() {
            count ++;
            return this;
        }
        public void add() {
            count ++;
        }
        @Override
        public void close() throws Exception {
            if (this.count == 1000) {
                throw new Exception("Counter should be used in try-with-resources block!");
            }
        }
    }
}
