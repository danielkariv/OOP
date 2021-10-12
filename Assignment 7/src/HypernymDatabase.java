import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * HypernymDatabase class. Deals with the logic of creating database from files,
 * saving databse to file, and searching for relations.
 * Used by other classes.
 */
public class HypernymDatabase {

    private HashMap<String, HashMap<String, Integer>> database;

    /**
     * Constructor.
     */
    public HypernymDatabase() {
        database = new HashMap<String, HashMap<String, Integer>>();
    }

    /**
     * compiling databse of relations from files in corpus.
     * including find files, find patterns in files, and extract the relations from them into database.
     * @param corpusPath given path to corpus folder.
     */
    public void compileDatabase(String corpusPath) {
        // get list of files from given folder's path.
        List<File> listOfFiles = this.getFilesInFolder(corpusPath, ".possf2");

        // NP {,} such as NP {, NP, ..., {and|or} NP}
        Pattern firstPattern =
                Pattern.compile("(<np>[-. \\w]*<\\/np>)(?: ,)* such as (<np>[-. \\w]*<\\/np>)"
                        + "(?: , (<np>[-. \\w]*<\\/np>))*(?: and (<np>[-. \\w]*<\\/np>)\\B)*");
        // such NP as NP {, NP, ..., {and|or} NP}
        Pattern secondPattern =
                Pattern.compile("such (<np>[-. \\w]*<\\/np>) as (<np>[-. \\w]*<\\/np>)"
                        + "(?: , (<np>[-. \\w]*<\\/np>))*(?: and (<np>[-. \\w]*<\\/np>)\\B)*");
        // NP {,} including NP {, NP, ..., {and|or} NP}
        Pattern thirdPattern =
                Pattern.compile("(<np>[-. \\w]*<\\/np>)(?: ,)* including (<np>[-. \\w]*<\\/np>)"
                        + "(?: , (<np>[-. \\w]*<\\/np>))*(?: and (<np>[-. \\w]*<\\/np>)\\B)*");
        // NP {,} especially NP {, NP, ..., {and|or} NP}
        Pattern fourthPattern =
                Pattern.compile("(<np>[-. \\w]*<\\/np>)(?: ,)* especially (<np>[-. \\w]*<\\/np>)"
                        + "(?: , (<np>[-. \\w]*<\\/np>))*(?: and (<np>[-. \\w]*<\\/np>)\\B)*");
        // NP {,} which is {{an example|a kind|a class} of} NP
        Pattern fivethPattern =
                Pattern.compile("(<np>[-. \\w]*<\\/np>) (?:, )*which is "
                        + "(?:(?:an example|a kind|a class)* of )*(<np>[-. \\w]*<\\/np>)");
        // run over each file, and each line in file, and search for the patterns in line.
        try {
            for (int id = 0; id < listOfFiles.size(); id++) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfFiles.get(id)));
                String line;
                // reads line by line from text, and try to match to patterns
                while ((line = bufferedReader.readLine()) != null) {
                    this.searchPatternInLine(line, firstPattern, true);
                    this.searchPatternInLine(line, secondPattern, true);
                    this.searchPatternInLine(line, thirdPattern, true);
                    this.searchPatternInLine(line, fourthPattern, true);
                    this.searchPatternInLine(line, fivethPattern, false);
                }
                bufferedReader.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred. (Reads lines from file)");
            e.printStackTrace();
            return;
        }
        // remove Hypernym with less then 3 Hyponyms ( as requested by the ass details).
        this.removeLessThan3Hyponyms();

        System.out.println("Finished compiling the Database.");
        System.out.println("Found " + database.size() + " Hypernyms.");
    }

    /**
     * reads all files in given corpus path and make a list of all files end with given fileEnd.
     * @param corpusPath given path to a corpus folder.
     * @param fileEnd given ending stirng of files.
     * @return list of all files with given fileEnd.
     */
    public List<File> getFilesInFolder(String corpusPath, String fileEnd) {
        List<File> possf2Files = new ArrayList<>();
        try {
            File folder = new File(corpusPath);
            File[] listOfFiles = folder.listFiles();

            // prints the files name in the folder.
            System.out.println("Folder at: " + corpusPath);
            for (int index = 0; index < listOfFiles.length; index++) {
                if (listOfFiles[index].isFile() && listOfFiles[index].getName().endsWith(fileEnd)) {
                    System.out.println("File " + listOfFiles[index].getName());
                    possf2Files.add(listOfFiles[index]);
                }
            }
            possf2Files.sort(Comparator.naturalOrder());
        } catch (NullPointerException e) {
            System.out.println("An error occurred. (Reading files from folder)");
            e.printStackTrace();
            return null;
        }
        return possf2Files;
    }

    /**
     * Save database to a file at given export path.
     * @param exportPath given path to export the file to.
     * @return true if success to save.
     */
    public boolean saveFile(String exportPath) {
        // create txt file, should put the database here.
        try {
            // open Writer to file in given export path.
            File myFile = new File(exportPath + "/hypernym_db.txt");
            FileWriter writer = new FileWriter(myFile);
            // check if already file exist.
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            // get database sorted by key names (A-Z,a-z).
            Set<Map.Entry<String, HashMap<String, Integer>>> keysSorted = sortByKey(database);
            //Style of each lines is "hypernym: hyponym1 (x), hyponym2 (x).."
            // the loop runs on each entry in the Sorted Database, and print styled.
            for (Map.Entry<String, HashMap<String, Integer>> entry : keysSorted) {
                HashMap<String, Integer> valueSorted = sortByValue(entry.getValue());
                // first has "hypernym:" in the line.
                String line = entry.getKey() + ":";
                // add each hyponym linked to the hypernym.
                for (Map.Entry<String, Integer> valueEntry : valueSorted.entrySet()) {
                    line += " " + valueEntry.getKey() + " (" + valueEntry.getValue() + "),";
                }
                // remove last ',' from the line text, and add endline to the string.
                line = line.substring(0, line.length() - 1);
                line += "\n";
                // write it on file.
                writer.write(line);
            }
            // we end writing so we close the writer.
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred. (Saving the database to file)");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * search for given pattern in given line, could appear multiple times in a line.
     * when find pattern in line, extract Hypernyms and Hyponyms based on given if first word is Hypernym or not.
     * @param line given line to search on.
     * @param pattern the pattern to search for.
     * @param isFirstHypernym if the first work in the pattern is an Hypernym ot not (=Hypony)
     */
    public void searchPatternInLine(String line, Pattern pattern, boolean isFirstHypernym) {
        Matcher m = pattern.matcher(line);
        while (m.find()) {
            // extract NP words from matching pattern.
            Matcher np = Pattern.compile("<np>[-. \\w]*<\\/np>").matcher(m.group());
            List<String> listOfNP = new ArrayList<String>();
            // remove NP tags from the word and add it to a list.
            while (np.find()) {
                String npString = np.group();
                npString = npString.replaceAll("<np>", "");
                npString = npString.replaceAll("</np>", "");
                listOfNP.add(npString);
            }
            // add found NP words to the database based on isFirstHypernym value.
            if (isFirstHypernym) {
                String hypernym = listOfNP.get(0);
                HashMap<String, Integer> hypernymMap = database.get(hypernym);
                if (hypernymMap == null) {
                    hypernymMap = new HashMap<>();
                }
                for (int i = 1; i < listOfNP.size(); i++) {
                    String hyponym = listOfNP.get(i);
                    Integer count = hypernymMap.get(hyponym);
                    if (count == null) {
                        count = 0;
                    }
                    count++;
                    hypernymMap.put(hyponym, count);
                }
                database.put(hypernym, hypernymMap);
            } else {
                // this only happens when [NP {,} which is {{an example|a kind|a class} of} NP]
                // which means there is only two words.
                String hypernym = listOfNP.get(1);
                HashMap<String, Integer> hypernymMap = database.get(hypernym);
                if (hypernymMap == null) {
                    hypernymMap = new HashMap<>();
                }
                String hyponym = listOfNP.get(0);
                Integer count = hypernymMap.get(hyponym);
                if (count == null) {
                    count = 1;
                }
                hypernymMap.put(hyponym, count);
                database.put(hypernym, hypernymMap);
            }
        }
    }

    /**
     * Sorting hypernyms in given Hashmap by their name ( from A-Z,a-z).
     * @param hm given database/HashMap.
     * @return sorted HashMap.
     */
    public Set<Map.Entry<String, HashMap<String, Integer>>> sortByKey(HashMap<String, HashMap<String, Integer>> hm) {
        // TreeMap to store values of HashMap
        TreeMap<String, HashMap<String, Integer>> sorted = new TreeMap<>();
        // Copy all data from hashMap into TreeMap
        sorted.putAll(hm);
        return sorted.entrySet();
    }

    /**
     * Clear Hyernyms from database that has less than 3 Hyponyms.
     */
    public void removeLessThan3Hyponyms() {
        HashMap<String, HashMap<String, Integer>> newDB = new HashMap<>();
        for (Map.Entry<String, HashMap<String, Integer>> entry : database.entrySet()) {
            if (entry.getValue().size() >= 3) {
                newDB.put(entry.getKey(), entry.getValue());
            }
        }
        database = newDB;
    }
    /**
     * Get a HashMap, and sorting it by it vaules, from top to bottom.
     * @param hm given HashMap.
     * @return sorted HashMap.
     */
    public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return -(o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /**
     * Get a lemma and search for hypernym that has it in the database.
     * Prints the result sorted from top to bottom.
     * @param lemma lemma string to search for.
     */
    public void searchLemma(String lemma) {
        HashMap<String, Integer> lemmaMap = new HashMap<>();
        for (Map.Entry<String, HashMap<String, Integer>> entry: database.entrySet()) {
            // check if hypernym has the lemma in its relations HashMap.
            if (entry.getValue().containsKey(lemma)) {
                Integer value = entry.getValue().get(lemma);
                lemmaMap.put(entry.getKey(), value);
            }
        }
        // sorting the lemma HashMap.
        lemmaMap = sortByValue(lemmaMap);
        // print it, styled as requested.
        for (Map.Entry<String, Integer> entry: lemmaMap.entrySet()) {
            System.out.println(entry.getKey() + ": (" + entry.getValue() + ")");
        }
    }

}
