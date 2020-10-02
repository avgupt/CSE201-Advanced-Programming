public class EliteCustomer extends Customer{
    
    EliteCustomer(String name, String l){
        super(name, l);
        DELIVERY_CHARGE = 0;

        category = "EliteCustomer";
    }

    @Override
    public float getCustomerDiscount(float bill) {
        if (bill > 200) bill -= 50;
        return bill;
    }
}
