public class BankAccount {
  public double moneyInTheAccount = 0.0;

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

  public boolean take(double amountToTake) {
    // метод списывает деньги со счета
    if (amountToTake < moneyInTheAccount) {
      moneyInTheAccount -= amountToTake;
      return true;
    }
    return false;
  }

  public boolean send (BankAccount receiver, double amountToSend) {
    if (take(amountToSend)) {
      receiver.put(amountToSend);
      return true;
    }
    return false;
  }

}
