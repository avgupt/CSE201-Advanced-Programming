public class FastFood extends Restaurant{

    FastFood(String name) {
        super(name);
        category = "FastFood";
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
            bill += f.getFinalPrice() * f.getQuantity();
        }
        bill -=  bill * (discount / 100);
        bill = c.getCustomerDiscount(bill);
        bill += + c.getDELIVERY_CHARGE();
        return bill;
    }

    @Override
    public void placeOrder(Customer c) {
        Zotato.updateBalance(calcBill(c)/100);
        Zotato.updateDelivery_charge(c.getDELIVERY_CHARGE());

        int rewards = (int) (calcBill(c) / 150) * 10;
        c.addRewards(rewards);
        addRewards(rewards);
    }
}
