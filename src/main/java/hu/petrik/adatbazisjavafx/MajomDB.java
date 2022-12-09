package hu.petrik.adatbaziskonzol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MajomDB {
    private Connection conn;
    public static String DB_DRIVER = "mysql";
    public static String DB_HOST = "localhost";
    public static String DB_PORT = "3306";

    public static  String DB_NAME = "java";
    public static  String DB_USERNAME = "root";
    public static  String DB_PASSWORD = "";
    public MajomDB() throws SQLException {
        //jdbc:mysql://localhost:3306/hava
        String url = String.format("jdbc:%s://%s:%s/%s", DB_DRIVER,DB_HOST,DB_PORT,DB_NAME);
        conn = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
    }
    public List<Majom> majomlistazas() throws SQLException {
        List<Majom> majoms = new ArrayList<>();
        String sql = "SELECT * FROM majmok";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()){
            int id = result.getInt("id");
            String fajta = result.getString("fajta");
            int max_iq = result.getInt("max_iq");
            boolean szereti_banant = result.getBoolean("szereti_banant");

            Majom majom = new Majom(id, fajta, max_iq, szereti_banant);
            majoms.add(majom);
        }
        return majoms;
    }
    public void majomHozzaadasa(Majom majom) throws SQLException {
        String sql = "INSERT INTO majmok (fajta, max_iq, szereti_banant) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, majom.getFajta());
        stmt.setInt(2, majom.getMax_iq());
        stmt.setBoolean(3, majom.isSzereti_banant());
        stmt.execute();
    }
}
