public class Bead extends Material{
    public Bead(int id, String name, String color, int grams){
        super(id, name, color, grams);
    }

    @Override
    public String getType() { return "Bead"; }
}
