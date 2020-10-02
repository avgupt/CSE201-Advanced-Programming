import java.util.*;
public class Application {

    private static List<Restaurant> restaurants = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    protected static Scanner in = new Scanner(System.in);

    private static Restaurant r = new Restaurant("");
    public static final Company Zotato = r.getZotato();

    static int launchMainMenu() {
    System.out.println("Welcome to Zotato");
    System.out.println("1) Enter as Restaurant Owner");
    System.out.println("2) Enter as Customer");
    System.out.println("3) Check User Details");
    System.out.println("4) Company Account details");
    System.out.println("5) Exit");

    int q = in.nextInt();
    return q;
    }

    static Restaurant launchRestaurantLogin() {
        int count = 1;
        for (Restaurant r : restaurants) {
            String category = "";
            if (r.getCategory() == "FastFood") category = "( Fast Food )";
            else if (r.getCategory() == "AuthenticRestaurant") category = "( Authentic Restaurant )";
            System.out.println(count++ + " " + r.getName() + " " + category);
        }
        int i = in.nextInt();
        return restaurants.get(i-1);
    }

    static int launchRestaurantMenu(Restaurant r) {
        System.out.println("Welcome " + r.getName());
        System.out.println("1) Add item \n2) Edit item\n3) Print Rewards\n4) Discount on bill value\n5) Exit");
        return in.nextInt();
    }

    static int launchCustomerMenu(Customer c) {
        System.out.println("Welcome " + c.getName());
        System.out.println("1) Select Restaurant\n2) checkout cart\n3) reward won\n4) print the recent orders\n5) Exit");
        return in.nextInt();
    }

    static Customer launchCustomerLogin() {
        int count = 1;
        for (Customer c : customers) {
            String category = "( " + c.getClass().getName() + " )";
            if (c.getClass().getName() == "Customer") category = "";
            System.out.println(count++ + " " + c.getName() + " " + category);
        }
        int i = in.nextInt();
        return customers.get(i-1);
    }

    public static void main(String[] args) {

        Restaurant S = new AuthenticRestaurant("Shah");
        S.setDiscount(1);
        Restaurant R = new Restaurant("Ravi's");
        Restaurant T = new AuthenticRestaurant("The Chinese");
        Restaurant W = new FastFood("Wang's");
        Restaurant P = new Restaurant("Paradise");

        restaurants.add(S);
        restaurants.add(R);
        restaurants.add(T);
        restaurants.add(W);
        restaurants.add(P);

        Customer A = new EliteCustomer("Ram", "Pune");
        Customer B = new EliteCustomer("Sam", "Pune");
        Customer C = new SpecialCustomer("Tim", "Pune");
        Customer D = new Customer("Kim", "Pune");
        Customer E = new Customer("Jim", "Pune");

        customers.add(A);
        customers.add(B);
        customers.add(C);
        customers.add(D);
        customers.add(E);

        int query = launchMainMenu();
        int option;
        while (query != 5) {
            switch (query) {
                case 1:
                    Restaurant r = launchRestaurantLogin();
                    option = launchRestaurantMenu(r);
                    while (option != 5) {
                        switch (option) {
                            case 1:
                                r.query1();
                                break;
                            case 2:
                                r.query2();
                                break;
                            case 3:
                                r.query3();
                                break;
                            case 4:
                                r.query4();
                                break;
                        }
                        option = launchRestaurantMenu(r);
                    }
                    break;
                case 2:
                    Customer c = launchCustomerLogin();
                    option = launchCustomerMenu(c);
                    while (option != 5) {
                        switch (option) {
                            case 1:
                                if (!c.restaurantSelected) {
                                    Restaurant re = launchRestaurantLogin();
                                    c.query1(re);
                                }
                                c.query1();
                                break;
                            case 2:
                                c.query2();
                                break;
                            case 3:
                                c.query3();
                                break;
                            case 4:
                                c.query4();
                                break;
                            case 5:
                                c.restaurantSelected = false;
                                break;
                        }
                        option = launchCustomerMenu(c);
                    }
                    break;
                case 3:
                    System.out.println("1) Customer List\n2) Restauant List");
                    int o = in.nextInt();
                    int counter = 1, k;
                    if (o == 1) {
                        for (Customer cust : customers) {
                            System.out.println(counter + ". " + cust.getName());
                            counter++;
                        }
                        k = in.nextInt();
                        Customer cust = customers.get(k-1);
                        System.out.println(
                            cust.getName() + " (" +
                            cust.getCategory() +
                            " ), " + 
                            cust.getLocation() + ", " +
                            cust.getBalance() + "/-"
                        );
                    }
                    else {
                        for (Restaurant rest : restaurants) {
                            System.out.println(counter + ". " + rest.getName());
                            counter++;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Total Company balance - INR " + Zotato.getBalance() + "/-");
                    System.out.println("Total Delivery Charges Collected - INR  " + Zotato.getDelivery_charge() + "/-");
            }
            query = launchMainMenu();
        }
    }
}