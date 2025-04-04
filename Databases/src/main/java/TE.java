import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TE {
    private static Connection con;
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/skoleni", "root", "Afk539tpk731.");
        } catch (Exception e) {
            System.out.println("Nepodarilo se vytvorit spojeni do databaze");
            throw new RuntimeException(e);
        }
        changeProduktName(1, "Implementace nové funkcionality");
        printProdukt(1);
    }
    private static void statementExample() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from firma");
            while (rs.next())
                System.out.println(rs.getInt("id") + "  " + rs.getString("nazev"));
            con.close();
        } catch (SQLException e) {
            System.out.println("Chyba v programu, chybny SQL prikaz");
            throw new RuntimeException(e);
        }
    }
    private static void printProdukt(Integer id) {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from firma WHERE id = ?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                System.out.println(rs.getInt("id") + "  " + rs.getString("nazev"));
            con.close();
        } catch (SQLException e) {
            System.out.println("Chyba v programu, chybny SQL prikaz");
            throw new RuntimeException(e);
        }
    }
    private static void changeProduktName(Integer id, String name) {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE firma SET nazev = ? WHERE id = ?");
            stmt.setString(1,name);
            stmt.setInt(2,id);
            int result = stmt.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Error while changing name");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while changing name", e);
        }
    }
}