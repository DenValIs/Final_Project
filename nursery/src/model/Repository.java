package nursery.src.model;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Mapper mapper = new Mapper();
    private FileOperation fileOperation;

    public Repository(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    public List<Animal> getAllAnimals() {
        List<String> lines = fileOperation.readAllLines();
        List<Animal> animals = new ArrayList<>();
        for (String line : lines) {
            animals.add(mapper.map(line));
        }
        return animals;
    }

    private void saveAnimals(List<Animal> animals) {
        List<String> lines = new ArrayList<>();
        for (Animal animal : animals) {
            lines.add(mapper.map(animal));
        }
        fileOperation.saveAllLines(lines);
    }

    private void saveAnimal(Animal animal, List<Animal> animals) {
        animals.add(animal);
        saveAnimals(animals);
    }

    public void createAnimal(Animal animal) {
        List<Animal> animals = getAllAnimals();
        saveAnimal(animal, animals);
    }

    private Animal findAnimal(String name, List<Animal> animals) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        throw new IllegalStateException("Animal not found!");
    }

    public void deleteAnimal(String name) {
        List<Animal> animals = getAllAnimals();
        animals.remove(findAnimal(name, animals));
        saveAnimals(animals);
    }

    public void updateAnimal(Animal animal) {
        deleteAnimal(animal.getName());
        List<Animal> animals = getAllAnimals();
        saveAnimal(animal, animals);
    }
}
