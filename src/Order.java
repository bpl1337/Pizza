import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class Order {
    private String id;
    private ArrayList<Priceable> items;
    private LocalDateTime orderTime;
    private LocalDateTime deferredTime;
    private String comment;

    public Order(String comment) {
        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
        this.comment = comment;
        this.deferredTime = null;
    }

    public Order(String comment, LocalDateTime deferredTime) {
        this(comment);
        this.deferredTime = deferredTime;
    }

    public void addItem(Priceable item) {items.add(item);}

    public void removeItem(Priceable item) {items.remove(item);}

    public double calculateTotal() {
        double total = 0;
        for (Priceable item : items) {
            total += item.calculatePrice();
        }
        return total;
    }

    public void printReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        System.out.println("\n=========================================");
        System.out.println("ЧЕК ЗАКАЗА #" + id);
        System.out.println("Создан: " + orderTime.format(formatter));

        if (deferredTime != null) {
            System.out.println("ОТЛОЖЕННАЯ ДОСТАВКА НА: " + deferredTime.format(formatter));
        }

        System.out.println("Комментарий: " + (comment.isEmpty() ? "Нет" : comment));
        System.out.println("-----------------------------------------");

        if (items.isEmpty()) {
            System.out.println("Заказ пуст.");
        } else {
            int i = 1;
            for (Priceable item : items) {
                System.out.printf("%d. Позиция: %.2f руб.\n", i, item.calculatePrice());
                i++;
            }
        }

        System.out.println("-----------------------------------------");
        System.out.printf("ИТОГО К ОПЛАТЕ: %.2f руб.\n", calculateTotal());
        System.out.println("=========================================\n");
    }

    public String getId() { return id; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public LocalDateTime getDeferredTime() { return deferredTime; }
    public ArrayList<Priceable> getItems() { return items; }
}