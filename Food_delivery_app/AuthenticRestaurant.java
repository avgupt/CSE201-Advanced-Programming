public class AuthenticRestaurant extends Restaurant {
    AuthenticRestaurant(String name) {
        super(name);
        category = "AuthenticRestaurant";
    }
    
    @Override
    public float getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public float calcBill(Customer c) {
        float bill = 0;
        for (FoodItem f : c.getCart()) {
            bill += f.getFinalPrice() * (float) f.getQuantity();
        }

        bill -=  bill * (discount / 100);
        if (bill > 100) bill -= 50;
        bill = c.getCustomerDiscount(bill);
        bill += (float) c.getDELIVERY_CHARGE();
        return bill;
    }

    @Override
    public void placeOrder(Customer c) {
        Zotato.updateBalance(calcBill(c)/100);
        Zotato.updateDelivery_charge(c.getDELIVERY_CHARGE());

        int rewards = (int) (calcBill(c) / 200) * 25;
        c.addRewards(rewards);
        addRewards(rewards);
    }
}
