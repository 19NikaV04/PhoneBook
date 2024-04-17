// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что во входной структуре будут повторяющиеся имена с 
// разными телефонами, их необходимо считать, как одного человека с разными телефонами. Вывод 
// должен быть отсортирован по убыванию числа телефонов.


import java.util.*;

public class Book {
    private HashMap<String, TreeSet<String>> phoneBook;

    public Book() {
        phoneBook = new HashMap<>();
    } 

    public void addContact(String name, String phoneNumber) {
        TreeSet<String> phones = phoneBook.getOrDefault(name, new TreeSet<>());
        phones.add(phoneNumber);
        phoneBook.put(name, phones);
    }

    public void removeContact(String name, String phoneNumber) {
        TreeSet<String> phones = phoneBook.getOrDefault(name, new TreeSet<>());
        phones.remove(phoneNumber);
        if (phones.isEmpty()) {
            phoneBook.remove(name);
        } else {
            phoneBook.put(name, phones);
        }
    }

    public TreeSet<String> getPhones(String name) {
        return phoneBook.get(name);
    }

    public void printBook() {
        for (String name : phoneBook.keySet()) {
            TreeSet<String> phones = phoneBook.get(name);
            System.out.println(name + ": " + phones);
        }
    }

    public List<String> sortContactsByPhones() {
        List<Map.Entry<String, TreeSet<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());
        sortedEntries.sort(Comparator.comparing(entry -> -entry.getValue().size()));

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, TreeSet<String>> entry : sortedEntries) {
            result.add(entry.getKey() + ": " + entry.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        Book phoneBook = new Book();
        phoneBook.addContact("Михаил", "+7 921-164-55-05");
        phoneBook.addContact("Михаил", "+7 921-284-67-00");
        phoneBook.addContact("Оксана", "+7 921-800-34-54");
        phoneBook.addContact("Оксана", "+7 900-123-76-00");
        phoneBook.addContact("Мария", "+7 981-326-06-98");

        phoneBook.printBook();

        TreeSet<String> phones = phoneBook.getPhones(null);
        System.out.println();
    }
}
