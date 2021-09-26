public class BankAccount {
  private double moneyInTheAccount = 0.0;
  public static final double COMMISSION = 1.0;

  public double getAmount() {
    // верните значение количества денег не счету
    return moneyInTheAccount;
  }

  public void put(double amountToPut) {
    // метод зачисляет деньги на счет
    if (amountToPut > 0) {
      moneyInTheAccount += amountToPut;
    }
  }

  public boolean take(double amountToTake, double commission) {
    // метод списывает деньги со счета
    if (amountToTake * commission < moneyInTheAccount) {
      moneyInTheAccount -= amountToTake * commission;
      return true;
    }
    return false;
  }

  public boolean send (BankAccount receiver, double amountToSend, double commission) {
    if (take(amountToSend,commission)) {
      receiver.put(amountToSend);
      return true;
    }
    return false;
  }
}
