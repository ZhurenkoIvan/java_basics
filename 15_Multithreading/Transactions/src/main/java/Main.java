import java.util.HashMap;


public class Main {
    public static void main(String[] args) {

        //Создаю банк
        HashMap<String, Account> accounts = new HashMap<>();
        for (int i = 0; i<500; i++) {
            Account account = generateAccount(i);
            accounts.put(account.getAccNumber(), account);
        }
        Bank bank = new Bank();
        bank.setAccounts(accounts);

        //Запускаю потоки
        for (int i = 0; i < 100; i++) {
            TransferCkeck transferCkeck = new TransferCkeck();
            transferCkeck.setBank(bank);
            transferCkeck.start();
        }
    }

    private static Account generateAccount(int id) {
        Account account = new Account();
        long money = (long) (Math.random()*1000000l + 10000);
        String accNumber = String.valueOf(id);
        account.setAccNumber(accNumber);
        account.setMoney(money);
        return account;
    }
}
