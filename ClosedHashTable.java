import java.util.*;

public class ClosedHashTable {
    private static final int M = 13;
    private static final String PROVERB = "Без труда нема плода, а без науки — мудрості";
    private static final Map<Character, Integer> UKR_POSITIONS = buildUkrPositions();

    public static void main(String[] args) {
        List<String> words = tokenizeUkrWords(PROVERB);
        String[] hashTable = buildClosedHashTable(words, M);
        displayHashTable(hashTable);
    }

    private static int primaryHash(String key) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            sum += UKR_POSITIONS.getOrDefault(ch, 0);
        }
        return Math.floorMod(sum, M);
    }

    private static String[] buildClosedHashTable(List<String> words, int m) {
        String[] table = new String[m];
        for (String word : words) {
            int startAddress = primaryHash(word);
            for (int i = 0; i < m; i++) {
                int address = (startAddress + i) % m;
                if (table[address] == null) {
                    table[address] = word;
                    break;
                }
            }
        }
        return table;
    }

    private static void displayHashTable(String[] table) {
        System.out.println("\n--- Хеш-таблиця (Відкрита адресація, M=" + M + ") ---");
        System.out.println("Індекс | Слово");
        System.out.println("-------|-------");
        for (int i = 0; i < table.length; i++) {
            String value = (table[i] == null) ? "(NULL)" : table[i];
            System.out.printf("%02d | %s%n", i, value);
        }
    }

    private static Map<Character, Integer> buildUkrPositions() {
        char[] ukr = {
                'А','Б','В','Г','Ґ','Д','Е','Є','Ж','З','И','І','Ї','Й','К','Л','М','Н',
                'О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ь','Ю','Я'
        };
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ukr.length; i++) {
            map.put(ukr[i], i + 1);
        }
        return Collections.unmodifiableMap(map);
    }

    private static List<String> tokenizeUkrWords(String text) {
        String up = text.toUpperCase(Locale.ROOT).replaceAll("[^\\p{IsLetter}]+", " ").trim();
        if (up.isEmpty()) return Collections.emptyList();
        return Arrays.asList(up.split("\\s+"));
    }
}
