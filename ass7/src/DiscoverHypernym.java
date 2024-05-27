//207270521 Denis Mogilevsky
import java.io.File;

/**
 * @author Denis Mogilevsky
 */
public class DiscoverHypernym {
    /**
     * Main. launches the lemma search.
     * @param args the file directory and lemma to be searched for.
     */
    public static void main(String[] args) {
        File fileDirectory = new File(args[0]);
        String lemma = args[1];
        Hypernyms hypernyms = new Hypernyms();
        File[] directoryListing = fileDirectory.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                hypernyms.createHypernymList(lemma, child);
            }
        } else {
            System.out.println("Invalid file location.");
        }
        hypernyms.printHypernymMap();
    }
}