import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "Vitylin97";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet coursesAverageBuy =  statement.executeQuery("SELECT pl.course_name, " +
                    "(count(course_name) / (max(MONTH (pl.subscription_date)) + 1 - min(month(pl.subscription_date)))) AS average_buy" +
                    " FROM purchaselist pl" +
                    " GROUP BY course_name;");
            while (coursesAverageBuy.next()) {
                String courseName = coursesAverageBuy.getString("course_name");
                double averageBuy = coursesAverageBuy.getDouble("average_buy");
                System.out.println(courseName + " - " + averageBuy);
            }
            coursesAverageBuy.close();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
