import java.sql.*;

public class TrainingExample {
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/skoleni", "root", "Afk539tpk731.");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from firma");
            while (rs.next())
                System.out.println(rs.getInt("id") + "  " + rs.getString("nazev"));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void preparedStatementExample() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sda", "root", "heslo");
            PreparedStatement stmt = con.prepareStatement("select * from produkt WHERE id = ?");
            stmt.setInt(1,1);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                System.out.println(rs.getInt("id") + "  " + rs.getString("nazev") + "  " + rs.getString("popis"));
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Nenalezen ovladac k databazi");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Chyba v programu, chybny SQL prikaz");
            throw new RuntimeException(e);
        }
    }
}