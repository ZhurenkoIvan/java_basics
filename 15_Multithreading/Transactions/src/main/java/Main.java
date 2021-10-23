import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


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
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(40);
        executor.execute(() -> {
            for (int i = 0; i < 10000; i++) {
                String fromId = String.valueOf((int) (Math.random() * 499));
                String toId = String.valueOf((int) (Math.random() * 499));
                long amount = (long) (Math.random() * 51000l);
                System.out.println("Денег на счету ДО трансфера: " + bank.getAccounts().get(fromId).getMoney() +
                        System.lineSeparator() + "Количество денег, которые нужно снять со счета " + fromId + ": " + amount);
                bank.transfer(fromId, toId, amount);
                System.out.println("Денег на счету ПОСЛЕ трансфера: " + bank.getAccounts().get(fromId).getMoney() +
                        System.lineSeparator() + "Количество денег, снятых со счета " + fromId + ": " + amount);
            }
        });

        executor.shutdown();
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
