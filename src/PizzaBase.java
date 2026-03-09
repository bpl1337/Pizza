public class PizzaBase {
    private String name;
    private double price;
    private static double standardPrice;

    public PizzaBase(String name, double price, boolean isClassic) {
        this.name = name;
        if (isClassic) {
            standardPrice = price;
            this.price = price;
        }
        else this.price = Math.min(price, standardPrice * 1.2);
    }

    public String getName() { return name;}

    @Override
    public String toString() {
        return "name: " + name + '\'' + ", price: " + price;
    }

    public static double getStandardPrice() {return standardPrice;}

    public double getPrice() {return price;}

    public void setName(String name) {this.name = name;}

    public void setPrice(double price) {
        if (this.name.equalsIgnoreCase("Классическая") && price > 0) {
            this.price = price;
            standardPrice = price;
        }
        if (price > 0) {
            this.price = Math.min(price, standardPrice * 1.2);
        } else {
            System.out.println("Ошибка: стоимость не может быть отрицательной " +
                    "или отличаться от классической более чем на 20%!");
        }
    }
}
