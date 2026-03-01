import java.util.ArrayList;

public class PizzaSide {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Pizza> pizzas;

    public PizzaSide(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
        this.pizzas = new ArrayList<>();
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public ArrayList<Ingredient> getIngredients() {return ingredients;}

    public void addIngredient(Ingredient ing) {ingredients.add(ing);}

    public void removeIngredient(Ingredient ing) {ingredients.remove(ing);}

    public ArrayList<Pizza> getPizzas() {return pizzas;}

    public void addPizza(Pizza pizza) {pizzas.add(pizza);}

    public  void removePizza(Pizza pizza) {pizzas.remove(pizza);}

    public double calculatePrice() {
        double total = 0;
        for (Ingredient ing : ingredients) {
            total += ing.getPrice();
        }
        return total;
    }
}
