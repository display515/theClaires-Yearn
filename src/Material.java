public abstract class Material {
    private final int id;
    private final String name;
    private final String color;
    private int grams;

    public Material(int id, String name, String color, int grams){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Color cannt be empty.");
        }
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

    public void add(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount can't be less than or equal to 0");
        }

        grams += amount;
    }

    public void use(int amount) throws InsufficientMaterialException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount can't be less than or equal to 0.");
        }
        if (amount > grams) {
            throw new InsufficientMaterialException(
                "Not enough " + color + " " + name + ": need " + amount + "g but only " + grams + "g left.");
        }
        grams -= amount;
    }

    public int getId()         {return id;}
    public String getName()    {return name;}
    public String getColor()   {return color;}
    public int getGrams()      {return grams;}

    public abstract String getType();

    @Override
    public String toString(){
        return String.format("%-18s %-8s %4d g", name, color, grams);
    }

    
}
