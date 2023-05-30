package nursery.src;
import nursery.src.controller.Controller;
import nursery.src.model.FileOperation;
import nursery.src.model.Repository;
import nursery.src.view.View;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperation("animals.csv");
        Repository repository = new Repository(fileOperation);
        Controller controller = new Controller(repository);
        View view = new View(controller);
        view.run();
    }
}