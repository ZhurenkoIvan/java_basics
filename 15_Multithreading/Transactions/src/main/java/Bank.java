import java.util.Map;
import java.util.Random;

public class Bank {
    private Map<String, Account> accounts;

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    private final Random random = new Random();

    public synchronized boolean isFraud(Account fromAccount, Account toAccount, long amount)
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
        String fromAccountId = fromAccount.getAccNumber();
        String toAccountId = toAccount.getAccNumber();
        if (fromAccountId.compareTo(toAccountId) > 0) {
        synchronized (fromAccount) {
                synchronized (toAccount){
                    doTransfer(fromAccount, toAccount, amount);
                }
            }
        } else {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    doTransfer(fromAccount, toAccount, amount);
                }
            }
        }
    }

    private void doTransfer(Account fromAccount, Account toAccount, long amount) {
        if (!fromAccount.isBlocked() && !toAccount.isBlocked() && fromAccount.getMoney() >= amount) {
            if (amount > 50000) {
                try {
                    if (isFraud(fromAccount, toAccount, amount)) {
                        fromAccount.setBlocked(true);
                        toAccount.setBlocked(true);
                    } else {
                        addMoney(fromAccount, toAccount, amount);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                addMoney(fromAccount, toAccount, amount);
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public synchronized long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        return account.getMoney();
    }

    public void addMoney (Account fromAccount,  Account toAccount, long amount) {
        if (fromAccount.getMoney() >= amount) {
            fromAccount.setMoney(fromAccount.getMoney() - amount);
            toAccount.setMoney(toAccount.getMoney() + amount);
        }
    }
}
