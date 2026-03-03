import java.util.ArrayList;

public class OrderItem {
    private PriceAndName item;
    private ArrayList<Guest> owners;

    public OrderItem(PriceAndName item, ArrayList<Guest> owners) {
        this.item = item;
        this.owners = owners;
    }

    public PriceAndName getItem() { return item; }
    public ArrayList<Guest> getOwners() { return owners; }
}