import java.util.Map;

public class PizzaFiftyToFifty extends Pizza {
    private Pizza firstHalf;
    private Pizza secondHalf;

    public PizzaFiftyToFifty(String name, PizzaBase base, String size, Pizza firstHalf, Pizza secondHalf) {
        super(name, base, size);
        this.firstHalf = firstHalf;
        this.secondHalf = secondHalf;
    }

    @Override
    public double calculatePrice() {
        double total = base.getPrice();

        double firstHalfIngPrice = 0;
        if (firstHalf.side != null)
            firstHalfIngPrice = firstHalf.side.calculatePrice();

        for (Map.Entry<Ingredient, Integer> entry : firstHalf.getIngredients().entrySet()) {
            firstHalfIngPrice += entry.getKey().getPrice() * entry.getValue();
        }

        double secondHalfIngPrice = 0;
        if (secondHalf.side != null)
            secondHalfIngPrice = secondHalf.side.calculatePrice();

        for (Map.Entry<Ingredient, Integer> entry : secondHalf.getIngredients().entrySet()) {
            secondHalfIngPrice += entry.getKey().getPrice() * entry.getValue();
        }

        return total + (firstHalfIngPrice / 2.0) + (secondHalfIngPrice / 2.0);
    }
}