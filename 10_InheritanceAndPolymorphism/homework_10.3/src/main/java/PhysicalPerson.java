public class PhysicalPerson extends Client {
    @Override
    public void getTermsOfService() {
        System.out.println("Условия пополнения: без комиссии." +
                System.lineSeparator() + "Условия снятия: без комиссии." +
                System.lineSeparator() + "Баланс: " + amountOfMoney + " рублей");
    }
}
