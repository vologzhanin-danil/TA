import java.util.*;

public class OpenHashTable {
    private static final int M = 13;
    private static final String PROVERB = "Без труда нема плода, а без науки — мудрості";
    private static final boolean USE_UKR_ALPHABET = true;

    public static void main(String[] args) {
        Map<Character, Integer> letterPositions = USE_UKR_ALPHABET
                ? buildUkrPositions()
                : buildLatinPositions();

        List<String> words = tokenizeWords(PROVERB);
        List<List<String>> table = buildOpenHashTable(words, M, letterPositions);

        System.out.println("--- Результат хешування (M=" + M + ") ---");
        displayHashTable(table);
    }

    public static List<List<String>> buildOpenHashTable(List<String> words, int m,
                                                        Map<Character, Integer> positions) {
        List<List<String>> table = new ArrayList<>(m);
        for (int i = 0; i < m; i++) table.add(new LinkedList<>());

        for (String w : words) {
            int address = simpleHashFromMap(w, positions, m);
            table.get(address).add(w);
        }
        return table;
    }

    public static int simpleHashFromMap(String key, Map<Character, Integer> positions, int m) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            sum += positions.getOrDefault(ch, 0);
        }
        return Math.floorMod(sum, m);
    }

    public static void displayHashTable(List<List<String>> table) {
        for (int i = 0; i < table.size(); i++) {
            System.out.printf("Індекс %02d: %s%n", i, table.get(i));
        }
    }

    public static List<String> tokenizeWords(String text) {
        String cleaned = text.toUpperCase(Locale.ROOT).replaceAll("[^\\p{IsLetter}]+", " ").trim();
        if (cleaned.isEmpty()) return Collections.emptyList();
        return Arrays.asList(cleaned.split("\\s+"));
    }

    public static Map<Character, Integer> buildLatinPositions() {
        Map<Character, Integer> map = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            map.put(c, (c - 'A') + 1);
        }
        return map;
    }

    public static Map<Character, Integer> buildUkrPositions() {
        char[] ukr = {
                'А','Б','В','Г','Ґ','Д','Е','Є','Ж','З','И','І','Ї','Й','К','Л','М','Н',
                'О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ь','Ю','Я'
        };
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ukr.length; i++) {
            map.put(ukr[i], i + 1);
        }
        return map;
    }
}
