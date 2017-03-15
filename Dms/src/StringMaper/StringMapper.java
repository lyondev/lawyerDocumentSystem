/**
 * Java class that takes a string and converts it to a hash map.
 * The hash map keys are the words from the string without duplicates.
 * The hash map values are the number of times the word is in the string.
 */
package StringMaper;

import java.util.HashMap;

/**
 * Java class that takes a string and converts it to a hash map.
 */
public class StringMapper {

    private HashMap<String,Integer> keywordMap = new HashMap<>();

    /**
     * Method that takes a string and returns a hashmap of the words and
     * number of occurances of the word.
     *
     * @param string
     * @return hashmap <String(words) Integer(occurances)>
     *
     */
    public HashMap<String, Integer> processString(String string){
        String[] stringArray = parseString(string);
        mapStringArray(stringArray);
        return keywordMap;
    }

    private String[] parseString(String text){
        text = text.toLowerCase();
        text = text.replaceAll("[!?,.():]", "");
        String[] words = text.split("\\s+");
        return words;
    }

    private void mapStringArray(String[] array){
        for (String word : array) {
            if (!word.equals("")) {
                if (keywordMap.containsKey(word)) {
                    Integer count = keywordMap.get(word);
                    count++;
                    keywordMap.replace(word, count);
                } else {
                    keywordMap.put(word, 1);
                }
            }
        }
    }


}
