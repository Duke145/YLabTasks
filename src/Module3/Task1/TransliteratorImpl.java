package Module3.Task1;

import java.util.HashMap;

public class TransliteratorImpl implements Transliterator {
    static {
        HashMap<Character, String> map = new HashMap<>();
        map.put('А', "A");
        map.put('Б', "B");
        map.put('В', "V");
        map.put('Г', "G");
        map.put('Д', "D");
        map.put('Е', "E");
        map.put('Ё', "E");
        map.put('Ж', "ZH");
        map.put('З', "Z");
        map.put('И', "I");
        map.put('Й', "I");
        map.put('К', "K");
        map.put('Л', "L");
        map.put('М', "M");
        map.put('Н', "N");
        map.put('О', "O");
        map.put('П', "P");
        map.put('Р', "R");
        map.put('С', "S");
        map.put('Т', "T");
        map.put('У', "U");
        map.put('Ф', "F");
        map.put('Х', "KH");
        map.put('Ц', "TS");
        map.put('Ч', "CH");
        map.put('Ш', "SH");
        map.put('Щ', "SHCH");
        map.put('Ъ', "IE");
        map.put('Ы', "Y");
        map.put('Ь', "");
        map.put('Э', "E");
        map.put('Ю', "IU");
        map.put('Я', "IA");

        TransliteratorImpl.map = map;
    }

    private static HashMap<Character, String> map;

    @Override
    public String transliterate(String source) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (map.containsKey(source.charAt(i))) {
                out.append(map.get(source.charAt(i)));
            } else {
                out.append(source.charAt(i));
            }
        }
        return out.toString();
    }
}
