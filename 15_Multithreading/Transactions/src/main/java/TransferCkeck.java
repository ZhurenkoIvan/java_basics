public class TransferCkeck extends Thread {

    private String name;
    private String fromId;
    private String toId;
    private long amount;
    private Bank bank;

    public TransferCkeck() {
        this.name = String.valueOf((int)(Math.random()*1000));
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            fromId = String.valueOf((int) (Math.random() * 499));
            toId = String.valueOf((int) (Math.random() * 499));
            amount = (long) (Math.random() * 51000l);
            System.out.println("Имя потока: " + name + System.lineSeparator() + "Денег на счету ДО трансфера: " + bank.getAccounts().get(fromId).getMoney() +
                    System.lineSeparator() + "Количество денег, которые нужно снять со счета " + fromId + ": " + amount);
            bank.transfer(fromId, toId, amount);
            System.out.println("Имя потока: " + name + System.lineSeparator() + "Денег на счету ПОСЛЕ трансфера: " + bank.getAccounts().get(fromId).getMoney() +
                    System.lineSeparator() + "Количество денег, снятых со счета " + fromId + ": " + amount);
        }

    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
