


public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        CardAccount cardAccount = new CardAccount();
        DepositAccount depositAccount = new DepositAccount();
        bankAccount.put(100000);
        cardAccount.put(50000);
        depositAccount.put(10000);
        bankAccount.send(depositAccount, 10000);
        bankAccount.send(cardAccount, 10000);
        cardAccount.send(depositAccount, 10000);
        cardAccount.send(bankAccount, 10000);
        System.out.println("Денег на расчетном счету - " + bankAccount.getAmount());
        System.out.println("Денег на кредитном счету - " + cardAccount.getAmount());
        System.out.println("Денег на депозитном счету - " + depositAccount.getAmount());



    }
}
