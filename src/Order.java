import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    private String id;
    private ArrayList<OrderItem> items;
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

    public void addItem(OrderItem item) {items.add(item);}

    public void printReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        System.out.println("\n=========================================");
        System.out.println("ЧЕК ЗАКАЗА #" + id);
        System.out.println("Создан: " + orderTime.format(formatter));
        if (deferredTime != null) {System.out.println("ОТЛОЖЕННАЯ ДОСТАВКА НА: " + deferredTime.format(formatter));}
        System.out.println("\n=========================================");

        Map<Guest, Double> guestTotals = new HashMap<>();
        double grandTotal = 0;

        for (OrderItem orderItem : items) {
            PriceAndName item = orderItem.getItem();
            List<Guest> owners = orderItem.getOwners();
            double price = item.calculatePrice();
            grandTotal += price;

            System.out.printf("- %s: %.2f руб.\n", item.getName(), price);

            if (!owners.isEmpty()) {
                int totalCents = (int) Math.round(price * 100);
                int splitCents = totalCents / owners.size();
                int remainderCents = totalCents % owners.size();

                for (int i = 0; i < owners.size(); i++) {
                    Guest g = owners.get(i);
                    double amountToPay = splitCents / 100.0;

                    if (i < remainderCents) {
                        amountToPay += 0.01;
                    }

                    guestTotals.put(g, guestTotals.getOrDefault(g, 0.0) + amountToPay);
                }
            }
        }

        System.out.println("\n=========================================");
        System.out.printf("ИТОГО: %.2f руб.\n", grandTotal);

        if (!guestTotals.isEmpty()) {
            System.out.println("\n--- К ОПЛАТЕ ПО ГОСТЯМ ---");
            for (Map.Entry<Guest, Double> entry : guestTotals.entrySet()) {
                System.out.printf("%s платит: %.2f руб.\n", entry.getKey().getName(), entry.getValue());
            }
        }
        System.out.println("=========================================\n");
    }

    public String getId() { return id; }
}