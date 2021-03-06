import java.text.Normalizer;
import java.util.HashMap;

/**
 *
 * @author jovan
 */
public class RepeatedWords {

    private static class MutableInt {

        private int value = 1;

        public void increment() {
            ++value;
        }

        public int getValue() {
            return value;
        }
    }

    public static String normalize(String word) {
        return Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("[¿?¡!.,;:\\[\\]\\(\\)-<>\"]", "");
    }

    private static HashMap<String, MutableInt> wordRepeated(String str) {
        HashMap<String, MutableInt> dicc = new HashMap();
        String[] words = str.split("\\s+");
        for (String word : words) {
            String normalizedWord = normalize(word);
            boolean wordInDictionary = dicc.containsKey(normalizedWord);
            if (!wordInDictionary) {
                dicc.put(normalizedWord, new MutableInt());
            } else {
                dicc.get(normalizedWord).increment();
            }
        }
        return dicc;
    }

    public static void showWordCount(String text) {
        HashMap<String, MutableInt> wordsRepeated = wordRepeated(text);
        System.out.println("{");
        wordsRepeated.forEach((word, count) -> {
            System.out.println(word + ": " + count.getValue() + ",");
        });
        System.out.println("}");
    }

    public static void main(String[] args) {
        String text = "Yo soy Jovanni y soy el mas perron de de aqui, deberas";
        showWordCount(text);
    }
}
