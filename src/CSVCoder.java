import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * This class is responsible for reading and writing to and from CSV files.
 * It has no knowledge of the meaning of CSV file.
 * Instead, it simply reads to and writes from dictionaries and/or array combinations.
 *
 * Created by matthewseaman on 10/15/17.
 */
public class CSVCoder {

    /*
     * If false (default), a Map of Maps will be used
     * as the output for writing and input for reading for all methods.
     * The first line item will be the key for the outer Map.
     * The keys of each inner Map will be client-specified keys.
     *
     * If true, a List of Maps will be used instead,
     * where the inner keys will be client-specified.
     */

    /**
     * Creates a new CSVCoder that may be used for reading and writing
     * to and from CSV files.
     */
    public CSVCoder() {}

    public boolean writeToFile(Map<String, List<String>> map, String filename) {
        String csvString = csvStringFromMap(map);
        return writeToFile(csvString, filename);
    }

    public boolean writeToFile(List<List<String>> list, String filename) {
        String csvString = csvStringFromList(list);
        return writeToFile(csvString, filename);
    }

    private boolean writeToFile(String string, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".txt"));
            writer.write(string);
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private String csvStringFromMap(Map<String, List<String>> map) {
        List<List<String>> list = new ArrayList<>(map.values());
        return csvStringFromList(list);
    }

    private String csvStringFromList(List<List<String>> list) {
        String csv = "";

        for (List<String> values : list) {
            String line = lineFromValues(values);
            csv += line + "\n";
        }

        return csv;
    }

    private String lineFromValues(List<String> values) {
        return String.join(",", values);
    }

    public Map<String, Map<String, String>> readMapFromFile(String filename, String[] keys) {
        Scanner reader;
        try {
            reader = new Scanner(new File(filename + ".txt"));
        } catch (IOException e) {
            return new HashMap<>();
        }

        String primaryKey = keys.length > 0 ? keys[0] : "";

        Map<String, Map<String, String>> lines = new HashMap<>();
        while (reader.hasNext()) {
            String line = reader.nextLine();
            Map<String, String> lineValues = readLineFromString(line, keys);
            lines.put(lineValues.get(primaryKey), lineValues);
        }

        return lines;
    }

    public List<Map<String, String>> readListFromFile(String filename, String[] keys) {
        Scanner reader;
        try {
            reader = new Scanner(new File(filename + ".txt"));
        } catch (IOException e) {
            return new ArrayList<>();
        }

        List<Map<String, String>> lines = new ArrayList<>();
        while (reader.hasNext()) {
            String line = reader.nextLine();
            lines.add(readLineFromString(line, keys));
        }

        return lines;
    }

    private Map<String, String> readLineFromString(String string, String[] keys) {
        String[] values = string.split(",");

        Map<String, String> keyedValues = new HashMap<>();
        int i = 1;
        for (String value : values) {
            if (keys.length >= i) {
                keyedValues.put(keys[i - 1], value.trim());
            }
            i++;
        }

        return  keyedValues;
    }

}
