package guldilin.exceptions;

public class NoSuchObject extends Exception{
    private final String model;

    public NoSuchObject(String model) {
        super(model + " does not exists.");
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
