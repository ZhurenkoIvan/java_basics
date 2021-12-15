import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
//    private HashMap<Voter, Byte> voterCounts;
//    private Voter voter;
    private static PreparedStatement preparedStatement;


    public XMLHandler() throws SQLException {
//
//        voterCounts = new HashMap<>();
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE `count`=`count` + 1";
        preparedStatement = DBConnection.getConnection().prepareStatement(sql);

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter")) {
            try {
                String birthDay = attributes.getValue("birthDay");
                birthDay = birthDay.replace('.', '-');
                preparedStatement.setString(1, attributes.getValue("name"));
                preparedStatement.setString(2, birthDay);
                preparedStatement.setInt(3,1);
                preparedStatement.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        if (qName.equals("voter") && voter == null) {
//            String birthDay = attributes.getValue("birthDay");
//            voter = new Voter(attributes.getValue("name"), birthDay);
//        }else if (qName.equals("visit") && voter != null) {
//           Byte count = voterCounts.getOrDefault(voter, (byte) 0);
//           voterCounts.put(voter, (byte) (count +1));
//        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        if (qName.equals("voter")) {
//            voter = null;
//        }
    }

    public void executePreparedStatement() throws SQLException {
        preparedStatement.executeBatch();
        printVoterCounts();
    }

    private static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
        rs.close();
    }

//    public void printDuplicatedVoters() {
////        for (Voter voter : voterCounts.keySet()) {
////            int count = voterCounts.get(voter);
////            if (count > 1) {
////                System.out.println(voter.toString() + " - " + count);
////            }
////        }
//    }
}
