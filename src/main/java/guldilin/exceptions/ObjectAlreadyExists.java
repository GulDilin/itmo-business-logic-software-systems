package guldilin.exceptions;

public class ObjectAlreadyExists extends Exception {
    private final String model;

    public ObjectAlreadyExists(String model) {
        super(model + " already exists.");
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
