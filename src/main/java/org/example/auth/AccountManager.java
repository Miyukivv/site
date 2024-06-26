//package org.example.auth;
//
//import org.example.database.DatabaseConnection;
//
//import javax.naming.AuthenticationException;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class AccountManager {
//
//    private DatabaseConnection databaseConnection;
//
//    public AccountManager(DatabaseConnection databaseConnection) {
//        this.databaseConnection = databaseConnection;
//    }
//
//    public void init() { //Tworzenie tabeli
//        try {
//            String createSQLTable = "CREATE TABLE IF NOT EXISTS users( " +
//                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
//                    "username TEXT NOT NULL," +
//                    "password TEXT NOT NULL)";
//            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(createSQLTable);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void register(String username, String password) {
//        try {
//            String insertSQL = "INSERT INTO users (username, password) VALUES (?, ?)"; //? ? to zabezpieczenie przed wyciągnięciem wszystkich rekordów z bazy danych
//            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(insertSQL);
//
//            statement.setString(1, username);
//            statement.setString(2, password);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Account authenticate(String username, String password) throws AuthenticationException {
//        try {
//            String sql = "SELECT id, username, password FROM users WHERE username = ?";
//
//            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sql);
//            statement.setString(1, username); //pierwszym znakiem zapytania jest username
//
//            if (!statement.execute()) throw new RuntimeException("SELECT failed");
//            ResultSet result = statement.getResultSet(); //Pobranie wyniku zapytania z bazy danych
//
//            if (!result.next()) {
//                throw new AuthenticationException("No such user");
//            }
//
//            if (!result.getString(3).equals(password)) {
//                throw new AuthenticationException("Wrong password");
//            }
//
//            return new Account(
//                    result.getInt(1),
//                    result.getString(2)
//            );
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}