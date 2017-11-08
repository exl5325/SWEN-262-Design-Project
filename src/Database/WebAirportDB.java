package Database;

import Itinerary.Airport;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by matthewvogt on 11/3/17.
 */
public class WebAirportDB implements AirportDB {
    private static final String airportInfoStart = "http://services.faa.gov/airport/status/";
    private static final String airportInfoEnd = "?format=application/xml";
    public static void getAirportDelay(String airportCode){

    }

    private static Document connect(String url) throws Exception{
        StringBuilder request = requestURL(url);
        Document returnDoc = parseRequest(request);
        return returnDoc;
    }

    private static StringBuilder requestURL(String url) throws IOException {
        URL YahooURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) YahooURL.openConnection();

        // Set the HTTP Request type method to GET (Default: GET)
        con.setRequestMethod("GET");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        // Created a BufferedReader to read the contents of the request.
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        // MAKE SURE TO CLOSE YOUR CONNECTION!
        in.close();
        return response;
    }

    private static Document parseRequest(StringBuilder response)throws java.io.IOException{
        try {
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(response.toString()));

            Document doc = db.parse(is);
            return doc;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static int parseTime(String str){
        final Pattern pattern = Pattern.compile("\\d+"); // the regex
        final Matcher matcher = pattern.matcher(str); // your string

        final ArrayList<Integer> ints = new ArrayList<Integer>(); // results

        while (matcher.find()) { // for each match
            ints.add(Integer.parseInt(matcher.group())); // convert to int
        }
        int[] timeFactor = {1, 60, 1440};
        int i = 0;
        int delayTime = 0;
        for(int j = ints.size() - 1; j >= 0; j--){
            delayTime = delayTime + ints.get(j) * timeFactor[i];
        }
        return delayTime;
    }

    private static int getTime(Element rootElement){
        NodeList apirpotDelayTimeNode = rootElement.getElementsByTagName("AvgDelay");
        Element airportDelayElement = (Element) apirpotDelayTimeNode.item(0);
        String avgAirportDelayTimeString = getCharacterDataFromElement(airportDelayElement);
        int airportAvgDelayTimeInt = 0;
        int airportMaxDelayTime = 0;
        int airportMinDelayTime = 0;
        if (avgAirportDelayTimeString.equals("?")){
            NodeList maxApirpotDelayTimeNode = rootElement.getElementsByTagName("MaxDelay");
            Element maxAirportDelayElement = (Element) maxApirpotDelayTimeNode.item(0);
            String maxAirportDelayTimeString = getCharacterDataFromElement(maxAirportDelayElement);

            NodeList minApirpotDelayTimeNode = rootElement.getElementsByTagName("MinDelay");
            Element minAirportDelayElement = (Element) minApirpotDelayTimeNode.item(0);
            String minAirportDelayTimeString = getCharacterDataFromElement(minAirportDelayElement);

            if (minAirportDelayTimeString.equals("?") && maxAirportDelayTimeString.equals("?")){
                return 0;
            }
            else if (!minAirportDelayTimeString.equals("?") && !maxAirportDelayTimeString.equals("?")){
                return (parseTime(minAirportDelayTimeString) + parseTime(maxAirportDelayTimeString)) / 2;
            }
            else if (!minAirportDelayTimeString.equals("?") && maxAirportDelayTimeString.equals("?")){
                return parseTime(minAirportDelayTimeString);
            }
            else{
                return parseTime(maxAirportDelayTimeString);
            }
        }
        else{
            return parseTime(avgAirportDelayTimeString);
        }
    }
    public Airport findAirport(String code){
        try {
            String url = airportInfoStart + code + airportInfoEnd;
            Document airportDoc = connect(url);
            Element rootElement = airportDoc.getDocumentElement();
            return findSingleAirport(rootElement, code);
        }
        catch (Exception ex){
            return null;
        }
    }

    private static Airport findSingleAirport(Element rootElement, String code){

        NodeList apirpotNameNode = rootElement.getElementsByTagName("Name");
        Element airportNameElement = (Element) apirpotNameNode.item(0);
        String airportName = getCharacterDataFromElement(airportNameElement);

        NodeList apirpotDelayTimeNode = rootElement.getElementsByTagName("AvgDelay");
        Element airportDelayElement = (Element) apirpotDelayTimeNode.item(0);
        String airportDelayTime = getCharacterDataFromElement(airportDelayElement);

        int airportDelayTimeInt = getTime(rootElement);

        NodeList tempNode = rootElement.getElementsByTagName("Temp");
        Element tempElement = (Element) tempNode.item(0);
        String tempString = getCharacterDataFromElement(tempElement);

        NodeList weatherNode = rootElement.getElementsByTagName("Weather");
        Element weatherElement = (Element) weatherNode.item(1);
        String weatherString = getCharacterDataFromElement(weatherElement);

        String dummyWeatherArray[] = {weatherString, tempString};

        return new Airport(code, airportName, airportDelayTimeInt, dummyWeatherArray);
    }

    private static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
}
