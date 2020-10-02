public class FoodItem {

    private String name, category;
    private float price, discount;
    private int quantity;
    private int id;
    private static int IdCounter = 1;

    FoodItem(String name, float price, int quantity,String category, float discount) {
        this.name = name;
        this.category = category;
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
        id = IdCounter;
        IdCounter++;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public float getDiscount() {
        return discount;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public float getFinalPrice() {
        return price * (1 - discount / 100);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void updateQuantity(int quantity) {
        this.quantity -= quantity;
    }
}
