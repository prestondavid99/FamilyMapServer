package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * Communicates with the database
 */
public class Database {

    private Connection conn;

    public Connection openConnection() throws DataAccessException {
        try {
            // Desktop String: "jdbc:sqlite:E:/School/Winter Semester 2022/CS 240/FamilyMap/DB/FamilyMapDatabase.db"
            // Laptop String: "jdbc:sqlite:C:/Users/TheBuh/IdeaProjects/FamilyMapServer/DB/FamilyMapDatabase.db"
            final String CONNECTION_URL = "jdbc:sqlite:DB/FamilyMapDatabase.db";

            // Open a database connection
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DataAccessException("openConnection failed", e);
        }
        return conn;
    }

    public void closeConnection(boolean commit) throws DataAccessException {
        try {
            if (commit) {
                conn.commit();
            } else {
                conn.rollback();
            }

            conn.close();
            conn = null;
        } catch (SQLException e) {
            throw new DataAccessException("closeConnection failed", e);
        }
    }

    public void createTables() throws DataAccessException {
        try (Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("drop table if exists dictionary");
            stmt.executeUpdate("create table dictionary ( word text not null unique )");
        } catch (SQLException e) {
            throw new DataAccessException("createTables failed", e);
        }
    }

    public void fillDictionary() throws DataAccessException {
        String[] words = {"fred", "wilma", "betty", "barney"};

        String sql = "insert into dictionary (word) values (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (String word : words) {
                stmt.setString(1, word);

                if (stmt.executeUpdate() != 1) {
                    throw new DataAccessException("fillDictionary failed: Could not insert word");
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("fillDictionary failed", e);
        }
    }

    public Set<String> loadDictionary() throws DataAccessException {

        String sql = "select word from dictionary";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            Set<String> words = new HashSet<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String word = rs.getString(1);
                    words.add(word);
                }
            }

            return words;
        } catch (SQLException e) {
            throw new DataAccessException("fillDictionary failed", e);
        }
    }

    public Connection getConnection() throws DataAccessException {
        if(conn == null) {
            return openConnection();
        } else {
            return conn;
        }
    }

    public static void main(String[] args) {
        try {
            Database db = new Database();

            db.openConnection();
            db.createTables();
            db.fillDictionary();
            db.closeConnection(true);

            System.out.println("Database created and loaded.");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}