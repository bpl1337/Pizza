import java.util.ArrayList;

public class Pizza implements Priceable{
    protected String name;
    protected String size;
    protected PizzaBase base;
    protected PizzaSide side;
    protected ArrayList<Ingredient> ingredients;

    public Pizza(String name, PizzaBase base, String size) {
        this.name = name;
        this.base = base;
        this.size = size;
        this.ingredients = new ArrayList<>();
        this.side = null;
    }

    public Pizza(String name, PizzaBase base, String size, PizzaSide side) {
        this(name, base, size);
        this.side = side;
    }

    public String getSize() {return size;}

    public void setSize(String size) {this.size = size;}

    public String check() {
        if (this.side == null) return "Бортика нет";
        return side.getName();
    }

    public String getName() {return name;}

    public PizzaBase getBase() {return base;}

    public PizzaSide getSide() {return side;}

    public void setSide(PizzaSide side) {this.side = side;}

    public void setBase(PizzaBase base) {this.base = base;}

    public ArrayList<Ingredient> getIngredients() {return ingredients;}

    public void setName(String name) {this.name = name;}

    public void addIngredient(Ingredient ing) {ingredients.add(ing);}

    public void removeIngredient(Ingredient ing) {ingredients.remove(ing);}

    @Override
    public double calculatePrice() {
        double total = base.getPrice();
        for (Ingredient ing : ingredients) {
            total += ing.getPrice();
        }
        return total;
    }
}