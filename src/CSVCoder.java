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

    /**
     * Creates a new CSVCoder that may be used for reading and writing
     * to and from CSV files.
     */
    public CSVCoder() {}

    /**
     * Writes the map as a CSV file
     *
     * @param map: A Map of arbitrary keys and a list for each line item.
     * @param filename: The name of the file, minus the extension.
     * @return True iff the save occurred successfully.
     */
    public boolean writeToFile(Map<String, List<String>> map, String filename) {
        String csvString = csvStringFromMap(map);
        return writeToFile(csvString, filename);
    }

    /**
     * Writes the list as a CSV file
     *
     * @param list: A List of lists for each line item.
     * @param filename: The name of the file, minus the extension.
     * @return True iff the save occurred successfully.
     */
    public boolean writeToFile(List<List<String>> list, String filename) {
        String csvString = csvStringFromList(list);
        return writeToFile(csvString, filename);
    }

    /**
     * Writes the string to disk with the given file name.
     *
     * @param string: The string to write.
     * @param filename: The name of the file, minus the extension.
     * @return True iff the save occurred successfully.
     */
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

    /**
     * Creates a CSV file string from the Map.
     *
     * @param map: A Map of arbitrary keys and a list for each line item.
     * @return A CSV file as a string.
     */
    private String csvStringFromMap(Map<String, List<String>> map) {
        List<List<String>> list = new ArrayList<>(map.values());
        return csvStringFromList(list);
    }

    /**
     * Creates a CSV file string from the List.
     *
     * @param list: A List of lists for each line item.
     * @return A CSV file as a string.
     */
    private String csvStringFromList(List<List<String>> list) {
        String csv = "";

        for (List<String> values : list) {
            String line = lineFromValues(values);
            csv += line + "\n";
        }

        return csv;
    }

    /**
     * Returns a string representing the line of a CSV file for the given list of values.
     *
     * @param values: A list of values to comma-separate.
     * @return A CSV line.
     */
    private String lineFromValues(List<String> values) {
        return String.join(",", values);
    }

    /**
     * Reads a CSV file into a Map.
     *
     * @param filename: The name of the file, minus the file extension.
     * @param keys: A List of hash keys corresponding to the expected CSV values.
     * @return A map where keys are the first CSV value and values are another map mapping the passed keys to CSV values.
     */
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
            if (!lineValues.isEmpty()) {
                lines.put(lineValues.get(primaryKey), lineValues);
            }
        }

        return lines;
    }

    /**
     * Reads a CSV file into a 2D List.
     *
     * @param filename: The name of the file, minus the file extension.
     * @param keys: A List of hash keys corresponding to the expected CSV values.
     * @return A List of maps mapping the passed keys to CSV values.
     */
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
            Map<String, String> lineValues = readLineFromString(line, keys);
            if (!lineValues.isEmpty()) {
                lines.add(lineValues);
            }
        }

        return lines;
    }

    /**
     * Reads a line of CSV text into a Map mapping keys to CSV values.
     *
     * If there are more items in a line than keys in the key array, all remaining items
     * will be bundled as a comma-separated String under the "endingValues" key.
     *
     * @param string: The CSV line of text.
     * @param keys: A List of hash keys corresponding to the expected CSV values.
     * @return A Map mapping keys to CSV values.
     */
    private Map<String, String> readLineFromString(String string, String[] keys) {
        String[] values = string.split(",");

        Map<String, String> keyedValues = new HashMap<>();
        List<String> extras = new ArrayList<>();

        int i = 1;
        for (String value : values) {
            if (keys.length >= i) {
                keyedValues.put(keys[i - 1], value.trim());
            } else {
                extras.add(value.trim());
            }
            i++;
        }

        if (!extras.isEmpty()) {
            keyedValues.put("endingValues", lineFromValues(extras));
        }

        return  keyedValues;
    }

}
