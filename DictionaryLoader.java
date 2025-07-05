package translate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.Optional;

public class DictionaryLoader {

    public static Optional<String> translate(String word, String src, String tgt) {
        try {
            word = word.trim().toLowerCase();
            src = src.trim().toLowerCase();
            tgt = tgt.trim().toLowerCase();

            JAXBContext context = JAXBContext.newInstance(Dictionary.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            InputStream is = DictionaryLoader.class.getResourceAsStream("/dictionary.xml");
            Dictionary dictionary = (Dictionary) unmarshaller.unmarshal(is);

            for (Dictionary.Entry entry : dictionary.getEntry()) {
                String srcWord = (String) Dictionary.Entry.class
                        .getMethod("get" + capitalize(src))
                        .invoke(entry);
                if (srcWord != null && srcWord.trim().equalsIgnoreCase(word)) {
                    String tgtWord = (String) Dictionary.Entry.class
                            .getMethod("get" + capitalize(tgt))
                            .invoke(entry);
                    return Optional.ofNullable(tgtWord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
