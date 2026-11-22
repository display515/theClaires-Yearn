public class Yarn extends Material {
    public Yarn(int id, String name, String color, int grams){
        super(id, name, color, grams);
    }

    @Override
    public String getType() { return "Yarn"; }
}
