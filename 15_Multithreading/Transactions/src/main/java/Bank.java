import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class Bank {
    private Map<String, Account> accounts;

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        if (!fromAccount.isBlocked() && !toAccount.isBlocked()){
            if (amount > 50000){
                try {
                    if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    fromAccount.setBlocked(true);
                    toAccount.setBlocked(true);
                } else {
                    addMoney(fromAccountNum, toAccountNum, amount);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
                addMoney(fromAccountNum, toAccountNum, amount);
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        return account.getMoney();
    }

    public void addMoney (String fromAccountNum, String toAccountNum, long amount) {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        if (fromAccount.getMoney() >= amount) {
            fromAccount.setMoney(fromAccount.getMoney() - amount);
            toAccount.setMoney(toAccount.getMoney() + amount);
        }
    }
}
