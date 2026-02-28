import java.util.ArrayList;

public class Pizza {
    private String name;
    private PizzaBase base;
    private ArrayList<Ingredient> ingredients;

    public Pizza(String name, PizzaBase base) {
        this.name = name;
        this.base = base;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {return name;}

    public PizzaBase getBase() {return base;}

    public void setBase(PizzaBase base) {this.base = base;}

    public ArrayList<Ingredient> getIngredients() {return ingredients;}

    public void setName(String name) {this.name = name;}

    public void addIngredient(Ingredient ing) {ingredients.add(ing);}

    public void removeIngredient(Ingredient ing) {ingredients.remove(ing);}

    public double calculatePrice() {
        double total = base.getPrice();
        for (Ingredient ing : ingredients) {
            total += ing.getPrice();
        }
        return total;
    }
}