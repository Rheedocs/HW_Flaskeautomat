package core;

// Repræsenterer en flaske med et unikt id og en type (øl eller vand)
public class Bottle {

    private final int id;
    private final String type;

    public Bottle(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " #" + id;
    }
}