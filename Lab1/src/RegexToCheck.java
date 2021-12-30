import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexToCheck {
    public void regex(String str, List<String> list) {
        String[] words = str.split(",");

        Pattern patternLastName = Pattern.compile("^[k|c]", Pattern.CASE_INSENSITIVE);
        Pattern patternNumber = Pattern.compile("^[+|[0-9]]");

        Matcher matcherLastName = patternLastName.matcher(words[0]);
        Matcher matcherNumber = patternNumber.matcher(words[1]);

        boolean matchFoundLastName = matcherLastName.find();
        boolean matchFoundNumber = matcherNumber.find();

        if (matchFoundLastName && matchFoundNumber) {
            list.add(str);
        }
    }
}
