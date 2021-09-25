public class LegalPerson extends Client {
    @Override
    public void getTermsOfService() {
        System.out.println("Условия пополнения: без комиссии." +
                System.lineSeparator() + "Условия снятия: комиссия 1%." +
                System.lineSeparator() + "Баланс: " + amountOfMoney + " рублей");
    }

    @Override
    public void take(double amountToTake) {
        if (amountToTake <= amountOfMoney && amountToTake > 0) {
            amountOfMoney -= amountToTake * 1.01;
        }
    }
}
