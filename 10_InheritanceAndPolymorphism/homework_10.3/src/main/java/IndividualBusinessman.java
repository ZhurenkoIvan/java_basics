public class IndividualBusinessman extends Client {
    @Override
    public void getTermsOfService() {
        System.out.println("Условия пополнения: до 1000 рублей - комиссия 1%. От 1000 и более - комиссия 0,5%." +
                System.lineSeparator() + "Условия снятия: без комиссии." +
                System.lineSeparator() + "Баланс: " + amountOfMoney + " рублей");
    }

    @Override
    public void put(double amountToPut) {
        if (amountToPut > 0 && amountToPut < 1000){
            amountOfMoney += amountToPut * 0.99;
        } else if (amountToPut >= 1000) {
            amountOfMoney += amountToPut * 0.995;
        }
    }
}
