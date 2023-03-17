package Module3.Task1;

public class TransliteratorTest {
    public static void main(String[] args) {
        Transliterator transliterator = new TransliteratorImpl();

        System.out.println(transliterator.transliterate("HELLO! САША, КАКЪЪЬЬЬЬ ДЕЛА! Go, boy!"));
        System.out.println(transliterator.transliterate("А, Б, В, Г, Д, Е, Ё, Ж, З, И, Й, К, Л, М, Н, О, П, Р, С, Т, У, Ф, Х, Ц, Ч, Ш, Щ, Ъ, Ы, Ь, Э, Ю, Я фбвгд abasdasdASDASD"));
    }
}
