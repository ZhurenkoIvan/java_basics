import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private HashMap<Voter, Byte> voterCounts;
    private Voter voter;


    public XMLHandler() {

        voterCounts = new HashMap<>();

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter") && voter == null) {
            String birthDay = attributes.getValue("birthDay");
            voter = new Voter(attributes.getValue("name"), birthDay);
        }else if (qName.equals("visit") && voter != null) {
           Byte count = voterCounts.getOrDefault(voter, (byte) 0);
           voterCounts.put(voter, (byte) (count +1));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void printDuplicatedVoters() {
        for (Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println(voter.toString() + " - " + count);
            }
        }
    }
}
