import java.util.HashMap;
import java.util.Map;

public class Pizza implements PriceAndName {
    protected String name;
    protected String size;
    protected PizzaBase base;
    protected PizzaSide side;
    protected Map<Ingredient, Integer> ingredients;

    public Pizza(String name, PizzaBase base, String size) {
        this.name = name;
        this.base = base;
        this.size = size;
        this.ingredients = new HashMap<>();
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

    @Override
    public String getName() {return name;}

    public PizzaBase getBase() {return base;}

    public PizzaSide getSide() {return side;}

    public void setSide(PizzaSide side) {this.side = side;}

    public void setBase(PizzaBase base) {this.base = base;}

    public Map<Ingredient, Integer> getIngredients() {return ingredients;}

    public void setName(String name) {
        this.name = name;
    }

    public void addIngredient(Ingredient ing) {
        if (ingredients.containsKey(ing)) {
            int currentCount = ingredients.get(ing);
            ingredients.put(ing, currentCount + 1);
            System.out.println("Ингредиент успешно увеличен на 1 порцию!\n");
        } else {
            ingredients.put(ing, 1);
            System.out.println("Ингредиент успешно добавлен!\n");
        }
    }

    public void removeIngredient(Ingredient ing) {
        int currentCount = ingredients.get(ing);
        if (currentCount > 1) {
            ingredients.put(ing, currentCount - 1);
            System.out.println("Ингредиент '" + ing.getName() + "' успешно уменьшен на одну порцию!\n");
        } else {
            ingredients.remove(ing);
            System.out.println("Ингредиент '" + ing.getName() + "' успешно удален из пиццы!\n");
        }
    }

    @Override
    public double calculatePrice() {
        double total = base.getPrice();
        if (side != null) total += side.calculatePrice();
        for (Map.Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
            Ingredient ing = entry.getKey();
            Integer count = entry.getValue();
            total += ing.getPrice() * count;
        }
        return total;
    }

}