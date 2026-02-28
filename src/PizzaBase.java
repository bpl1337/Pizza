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

    public double getPrice() {return price;}

    public void setName(String name) {this.name = name;}

    public void setPrice(double price) {
        if (price >= 0 && price <= standardPrice * 1.2) {
            this.price = price;
        } else {
            System.out.println("Ошибка: стоимость не может быть отрицательной " +
                    "или отличаться от классической более чем на 20%!");
        }
    }
}
