public class CardAccount extends BankAccount {
    @Override
    public boolean take(double amountToTake) {
        if (amountToTake * 1.01 < moneyInTheAccount) {
            super.take(amountToTake * 1.01);
        }
        return false;
    }
    // не забывайте, обращаться к методам и конструкторам родителя
    // необходимо используя super, например, super.put(10D);
    
}
