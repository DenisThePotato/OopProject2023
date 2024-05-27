//207270521 Denis Mogilevsky
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Denis Mogilevsky.
 *
 */
public class Hypernyms {
    //it will store each hypernym associated with the lemma and the amount of times it appears.
    private HashMap<String, Integer> hypernymMap = new HashMap<>();
    //list of patterns to be searched for.
    private ArrayList<Pattern> patternList = new ArrayList<>();

    /**
     * adds the hypernym associated with the lemma to the member hypernymMap.
     * @param key hypernym.
     */
    public void addKeyToMap(String key) {
        if (this.hypernymMap.containsKey(key)) {
            this.hypernymMap.replace(key, this.hypernymMap.get(key) + 1);
        } else {
            this.hypernymMap.put(key, 1);
        }
    }

    /**
     * constructor.
     * adds the needed patterns to the member patternList.
     * ADDED "\\s,?" AFTER NP AND BEFORE \\S CHECK IN REGEX WHEN UP. (1, 3-5)
     */
    public Hypernyms() {
        // NP {,} such as NP {, NP, ..., {and|or} NP}. Hypernym, hyponyms. WORKS!
        this.patternList.add(Pattern.compile(
                "<np>([a-z]+)</np>\\s*,?\\s+such as\\s+<np>([a-z]+)</np>(?:,\\s*<np>([a-z]+)</np>)*(?:\\s*(?:and|or)\\s*<np>([a-z]+)</np>)?", Pattern.CASE_INSENSITIVE));
        // such NP as NP {, NP, ..., {and|or} NP}.
        this.patternList.add(Pattern.compile(
                "such\\s+<np>([a-z]+)</np>\\s+as\\s+<np>([a-z]+)</np>(?:\\s*,\\s*<np>([a-z]+)</np>)*(?:\\s*(?:and|or)\\s*<np>([a-z]+)</np>)?", Pattern.CASE_INSENSITIVE));
        // NP {,} including NP {, NP, ..., {and|or} NP}.
        this.patternList.add(Pattern.compile(
                "<np>([a-z]+)</np>\\s*,?\\s+including\\s+<np>([a-z]+)</np>(?:,\\s*<np>([a-z]+)</np>)*(?:\\s*(?:and|or)\\s*<np>([a-z]+)</np>)?", Pattern.CASE_INSENSITIVE));
        // NP {,} especially NP {, NP, ..., {and|or} NP}.
        this.patternList.add(Pattern.compile(
                "<np>([a-z]+)</np>\\s*,?\\s+especially\\s+<np>([a-z]+)</np>(?:,\\s*<np>([a-z]+)</np>)*(?:\\s*(?:and|or)\\s*<np>([a-z]+)</np>)?", Pattern.CASE_INSENSITIVE));
        // NP {,} which is {{an example|a kind|a class} of} NP.
        this.patternList.add(Pattern.compile(
                "<np>([a-z]+)</np>\\s*,?\\s+which\\s+is(?:\\s+(?:an example|a kind|a class))?\\s+of\\s+<np>([a-z]+)</np>", Pattern.CASE_INSENSITIVE));
    }

    /**
     *
     * @param lemma the hyponym for which hypernyms are searched.
     * @param file file being searched.
     * Creates the hashmap.
     */
    public void createHypernymList(String lemma, File file) {
        for (Pattern pattern : patternList) {
            Matcher matcher = null;
            try {
                matcher = pattern.matcher(Files.readString(file.toPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while (matcher.find()) {
                if (matcher.group(0).contains("<np>" + lemma + "</np>")) {
                        addKeyToMap(matcher.group(1));
                }
            }
        }
    }

    /**
     * prints the hypernym map.
     */
    public void printHypernymMap() {
        hypernymMap.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
