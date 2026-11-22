public abstract class Material {
    private final int id;
    private final String name;
    private final String color;
    private int grams;

    public Material(int id, String name, String color, int grams){
        this.id = id;
        this.name = name;
        this.color = color;
        setGrams(grams);
    }

    public void setGrams(int grams){
        if (grams < 0 ){
            throw new IllegalArgumentException("Grams cannot be negative");
        }
        this.grams = grams;
    }

    public abstract String getType();

    @Override
    public String toString(){
        return String.format("%-18s %-8s %4d g", name, color, grams);
    }

    
}
