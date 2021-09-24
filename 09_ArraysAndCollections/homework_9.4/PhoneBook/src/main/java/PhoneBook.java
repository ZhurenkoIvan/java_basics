import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneBook {
    public static final String REGEX_NAME = "[А-Яа-я]+";
    public static final String REGEX_PHONE = "[0-9]{11}";

    TreeMap<String, TreeSet<String>> phoneBook = new TreeMap<>();




    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента

        if (phone.matches(REGEX_PHONE) && name.matches(REGEX_NAME)) {
            TreeSet <String> setOfPhones;
            if (phoneBook.get(name) == null) {
                setOfPhones = new TreeSet<>();
            } else {
                setOfPhones = new TreeSet<>(phoneBook.get(name));
            }
            setOfPhones.add(phone);
            if(isContainPhone(phone)) {
                String[] intermediateArray = getNameByPhone(phone).split(" ");
                String previousAbonent = intermediateArray[0];
                TreeSet <String> newSetofPhones = phoneBook.get(previousAbonent);
                newSetofPhones.remove(phone);
                if(newSetofPhones.isEmpty()){
                    phoneBook.remove(previousAbonent);
                } else {
                    phoneBook.put(previousAbonent, newSetofPhones);
                }
                phoneBook.put(name, setOfPhones);
            }else if (phoneBook.containsKey(name)) {
                phoneBook.put(name, setOfPhones);
            } else {
                phoneBook.put(name, setOfPhones);
            }
        }
    }

    public String getNameByPhone(String phone) {
        if (isContainPhone(phone)) {
            for (String key: phoneBook.keySet()) {
                TreeSet<String> setOfPhones = phoneBook.get(key);
                String stringPhoneBook = key + " - " + setOfPhones;
                if (setOfPhones.contains(phone)) {
                    return stringPhoneBook;
                }
            }
        }
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        if (phoneBook.containsKey(name)) {
            TreeSet<String> phones = new TreeSet<>();
            String stringPhones = name + " - " + phoneBook.get(name);
            phones.add(stringPhones);
            return phones;
        }
        else {
            return new TreeSet<>();
        }
    }

    public Set<String> getAllContacts() {
        if (phoneBook.isEmpty()){
            return new TreeSet<>();
        }
        TreeSet<String> setPhoneBook = new TreeSet<>();
        for (String key: phoneBook.keySet()) {
            String stringPhoneBook = key + " - " + phoneBook.get(key);
            setPhoneBook.add(stringPhoneBook);
        }
        return setPhoneBook;

        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet

    }

    public boolean isContainName (String name) {
        return phoneBook.containsKey(name);
    }

    public boolean isContainPhone(String phone) {
        for (String key : phoneBook.keySet()) {
            TreeSet <String> setOfPhones = phoneBook.get(key);
            if (setOfPhones.contains(phone)) {
                return true;
            }
        }
        return false;
    }
}