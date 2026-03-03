import java.util.HashMap;
import java.util.Map;

public class PizzaSlice extends Pizza {
    private int sliceCount;
    private Map<Ingredient, Integer>[] sliceIngredients;
    private PizzaSide[] sliceSides;
    private boolean[] isSliceFilled;

    public PizzaSlice(String name, PizzaBase base, String size) {
        super(name, base, size);

        if (size.equalsIgnoreCase("Маленький")) this.sliceCount = 6;
        else if (size.equalsIgnoreCase("Средний")) this.sliceCount = 8;
        else this.sliceCount = 12;

        this.sliceIngredients = new HashMap[sliceCount];
        this.sliceSides = new PizzaSide[sliceCount];
        this.isSliceFilled = new boolean[sliceCount];

        for (int i = 0; i < sliceCount; i++) {
            this.sliceIngredients[i] = new HashMap<>();
        }
    }

    public int getSliceCount() { return sliceCount; }
    public boolean[] getFilledStatus() { return isSliceFilled; }

    public boolean isFullyFilled() {
        for (boolean filled : isSliceFilled) {
            if (!filled) return false;
        }
        return true;
    }

    public void fillSlices(int start, int end, Map<Ingredient, Integer> ings, PizzaSide side) {
        for (int i = start - 1; i < end; i++) {
            sliceIngredients[i].putAll(ings);
            sliceSides[i] = side;
            isSliceFilled[i] = true;
        }
    }

    @Override
    public double calculatePrice() {
        double total = base.getPrice();
        double fraction = 1.0 / sliceCount;

        for (int i = 0; i < sliceCount; i++) {
            if (sliceSides[i] != null) {
                total += sliceSides[i].calculatePrice() * fraction;
            }
            for (Map.Entry<Ingredient, Integer> entry : sliceIngredients[i].entrySet()) {
                total += entry.getKey().getPrice() * entry.getValue() * fraction;
            }
        }
        return total;
    }
}