import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    public final String EMAIL_REGEX = "[A-Za-z.]+[@][a-z]+[.][A-Za-z]+";
    public final String FULL_NAME_REGEX = "[А-яёЁ]+[\\s][А-яёЁ]+";
    public final String PHONE_NUMBER_REGEX = "[+][0-9]{11}";
    public final String REGEX = FULL_NAME_REGEX + "[\\s]" + EMAIL_REGEX + "[\\s]" + PHONE_NUMBER_REGEX;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data){
        if(!data.matches(REGEX)){
            throw new IllegalArgumentException();
        }
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;
            String[] components = data.split("\\s+");
                String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
                storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}