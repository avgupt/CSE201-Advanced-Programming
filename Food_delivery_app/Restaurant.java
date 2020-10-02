import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Restaurant implements Reward, Login{

    protected final String name;
    protected static final Company Zotato = new Company();
    protected String category;
    protected int rewards;
    protected HashMap<Integer, FoodItem> menu;
    protected float discount;

    protected static Scanner in = new Scanner(System.in);
    
    Restaurant(String name){
        this.name = name;
        category = "";
        menu = new HashMap<Integer, FoodItem>();
    }

    void addItem(int id, String name, float price, int quantity, String category, float discount) {
        FoodItem dish = new FoodItem(name, price, quantity, category, discount);
        menu.put(Integer.valueOf(id), dish);
    }

    FoodItem getItemByID (int id) {
        return menu.get(Integer.valueOf(id));
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public float getDiscount() {
        return 0;
    }

    public Collection<FoodItem> getFoodItems() {
        return menu.values();
    }

    public Company getZotato() {
        return Zotato;
    }

    public void setDiscount(float discount) {
    }

    public float calcBill(Customer c) {
        float bill = 0;
        for (FoodItem f : c.getCart()) {
            bill += f.getFinalPrice() * f.getQuantity();
        }
        bill = c.getCustomerDiscount(bill);
        bill += + c.getDELIVERY_CHARGE();
        return bill;
    }

    public void placeOrder(Customer c) {
        Zotato.updateBalance(calcBill(c)/100);
        Zotato.updateDelivery_charge(c.getDELIVERY_CHARGE());

        for (FoodItem f : c.getCart()) {
            FoodItem menuDish = menu.get(f.getId());
            menuDish.setQuantity(menuDish.getQuantity() - f.getQuantity());
        }

        int rewards = (int) (calcBill(c) / 100) * 5;
        c.addRewards(rewards);
        addRewards(rewards);
    }

    public FoodItem addItem(String name, float price, int quantity, String category, float discount){
        FoodItem dish = new FoodItem(name, price, quantity, category, discount);
        menu.put(Integer.valueOf(dish.getId()), dish);
        return dish;
    }

    @Override
    public int getRewards() {
        return rewards;
    }

    @Override
    public void addRewards(int newRewards) {
        rewards += newRewards;
    }

    @Override
    public void query1() {
        System.out.println("Enter food item details");
        System.out.println("Food name:");
        String name = in.next();
        System.out.println("Item price:");
        float price = in.nextFloat();
        System.out.println("Item quantity");
        int quantity = in.nextInt();
        System.out.println("Item category");
        String category = in.next();
        System.out.println("Offer");
        int discount = in.nextInt();
        FoodItem f = addItem(name, price, quantity, category, discount);
        System.out.println(
            f.getId() + " " + f.getName() + 
            " " + f.getPrice() + " " + f.getQuantity() + " " + f.getDiscount() + 
            "% off " + f.getCategory()
        );
    }

    @Override
    public void query2() {
        for(FoodItem f : getFoodItems()) {
            System.out.println(
                f.getId() + " " + f.getName() + " " + 
                f.getPrice() + " " + f.getQuantity() + " " + 
                f.getDiscount() + "% off " + f.getCategory()
            ); 
        }
        int id = in.nextInt();
        FoodItem f = getItemByID(id);
        
        System.out.println("Welcome " + name);

        System.out.println(
            "Choose an attribute to edit:\n" +
            "1) Name\n" +
            "2) Price\n" +
            "3) Quantity\n" +
            "4) Category\n" +
            "5) Offer"
        );
        int option = in.nextInt();
        System.out.println("Enter the new attribute:");
        switch(option) {
            case 1:
                String name = in.nextLine();
                f.setName(name);
                break;
            
            case 2:
                float price = in.nextFloat();
                f.setPrice(price);
                break;

            case 3:
                int quantity = in.nextInt();
                f.setQuantity(quantity);
                break;

            case 4:
                String category = in.next();
                f.setCategory(category);
                break;

            case 5:
                float discount = in.nextFloat();
                f.setDiscount(discount);
                break;
        }
        
        System.out.println(
            f.getId() + " " + f.getName() + " " + 
            f.getPrice() + " " + f.getQuantity() + " " + 
            f.getDiscount() + "% off " + f.getCategory()
        );
    }

    @Override
    public void query3() {
        System.out.println("Reward Points: " + rewards);
    }

    @Override
    public void query4() {
        System.out.println("Offer on bill value - " + discount);
    }

}