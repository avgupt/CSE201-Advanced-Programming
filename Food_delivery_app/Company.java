public class Company {
    private float balance, delivery_charge;
    Company() {
        balance = 0;
        delivery_charge = 0;
    }

    public float getBalance() {
        return balance;
    }

    public float getDelivery_charge() {
        return delivery_charge;
    }

    public void updateBalance(float balance) {
        this.balance += balance;
    }

    public void updateDelivery_charge(float delivery_charge) {
        this.delivery_charge += delivery_charge;
    }
    
}
