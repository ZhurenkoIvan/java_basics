import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class XMLHandler extends DefaultHandler {
    private static PreparedStatement preparedStatement;


    public XMLHandler() throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate) VALUES(?, ?)";
        preparedStatement = DBConnection.getConnection().prepareStatement(sql);

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("voter")) {
            try {
                String birthDay = attributes.getValue("birthDay");
                birthDay = birthDay.replace('.', '-');
                preparedStatement.setString(1, attributes.getValue("name"));
                preparedStatement.setString(2, birthDay);
                preparedStatement.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    public void executePreparedStatement() throws SQLException {
        preparedStatement.executeBatch();
        printVoterCounts();
    }

    private static void printVoterCounts() throws SQLException {
        String sql = "Select name, birthDate, count(*) as count FROM learn.voter_count Group by name, birthDate Having count > 1;";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
        rs.close();
    }
}
