public class Main {
    public static void main(String[] args) {
        IndividualBusinessman individualBusinessman = new IndividualBusinessman();
        LegalPerson legalPerson = new LegalPerson();
        PhysicalPerson physicalPerson = new PhysicalPerson();

        individualBusinessman.put(10000);
        legalPerson.put(40000);
        physicalPerson.put(30000);

        individualBusinessman.getTermsOfService();
        legalPerson.getTermsOfService();
        physicalPerson.getTermsOfService();
    }
}
