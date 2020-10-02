import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer implements Reward, Login{

    protected final String name, location;
    protected String category;
    protected final int id;
    protected static int counter = 0;


    protected int rewards, DELIVERY_CHARGE;
    protected List<FoodItem> cart;
    protected List<FoodItem> orderHistory;
    protected float balance;

    Restaurant r;
    boolean restaurantSelected = false;

    protected static Scanner in = new Scanner(System.in);

    Customer(String name, String l) {
        this.name = name;
        this.id = ++counter;
        location = l;

        DELIVERY_CHARGE = 40;
        balance = 1000;
        rewards = 0;

        category = "";

        cart = new ArrayList<FoodItem>();
        orderHistory = new ArrayList<FoodItem>();
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public float getBalance() {
        return balance;
    }

    public String getLocation() {
        return location;
    }

    public List<FoodItem> getCart() {
        return cart;
    }

    public int getDELIVERY_CHARGE() {
        return DELIVERY_CHARGE;
    }

    public List<FoodItem> getOrderHistory() {
        return orderHistory;
    }

    public float getCustomerDiscount(float bill) {
        return bill;
    }

    public void checkout(Restaurant r) {
        float bill = r.calcBill(this);
        storeHistory();
        bill -= rewards;
        rewards = 0;
        balance -= bill;
        r.placeOrder(this);
        cart.clear();
    }

    private void storeHistory() {
        for (FoodItem f : cart) {
            orderHistory.add(f);
        }
    }

    public void addItem(FoodItem f) {
        cart.add(f);
    }

    @Override
    public int getRewards() {
        return rewards;
    }

    @Override
    public void addRewards(int newRewards) {
        rewards += newRewards;
    }

    public void query1(Restaurant r) {
        this.r = r;
        restaurantSelected = true;
    }

    @Override
    public void query1() {
        System.out.println("Choose item by id - ");
        for (FoodItem f : r.getFoodItems()) {
            System.out.println(
                f.getId() + " " + r.getName() + " " + f.getName() + " " + 
                f.getPrice() + " " + f.getQuantity() + " " + f.getDiscount() + 
                "% off " + f.getCategory()
                );
        }
        int id = in.nextInt();
        System.out.println("Enter item quantity");
        int q = in.nextInt();

        FoodItem dish = r.getItemByID(id);
        dish.setQuantity(q);

        this.addItem(dish);
        System.out.println("Items added to cart");
    }

    @Override
    public void query2() {
        System.out.println("Items in cart -");
        int num = 0;
        for (FoodItem f : cart){
            num += f.getQuantity();
            System.out.println(
                f.getId() + " " + r.getName() + " " + f.getName() + " " + 
                f.getPrice() + " " + f.getQuantity() + " " + f.getDiscount() + 
                "% off " + f.getCategory()
                );
        }
        float bill = r.calcBill(this);
        System.out.println(
            "Delivery charge - " + DELIVERY_CHARGE + "/-\nTotal order value - INR " + 
            bill + "/-\n    1) Proceed to checkout"
            );
        int t = in.nextInt();
        if (t == 1) {
            
            this.checkout(r);
            System.out.println(num + " items successfully bought for INR " + bill + "/-");
        }
    }

    @Override
    public void query3() {
        System.out.println("Total Rewards - " + rewards);
    }

    @Override
    public void query4() {
        for (FoodItem f : getOrderHistory()){
            System.out.println(
                f.getId() + " " + r.getName() + " " + f.getName() + " " + 
                f.getPrice() + " " + f.getQuantity() + " " + f.getDiscount() + 
                "% off " + f.getCategory()
                );
        }
    }
}
