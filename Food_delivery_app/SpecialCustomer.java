public class SpecialCustomer extends Customer{

    SpecialCustomer(String name, String l) {
        super(name, l);
        DELIVERY_CHARGE = 20;

        category = "SpecialCustomer";
    }

    @Override
    public float getCustomerDiscount(float bill) {
        if (bill > 200) bill -= 25;
        return bill;
    }
}