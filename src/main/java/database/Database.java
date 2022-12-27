package database;

import com.fasterxml.jackson.databind.ObjectMapper;
import jsonparsing.DataMap;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Database {

    public static Connection connection = JDBCPostgreSQL.getConnection();
    public static Statement statement;
    static DataMap map = new DataMap();

    private static Map<Integer, String> getDatabasePlayer() throws SQLException {
        statement = connection.createStatement();
        ResultSet database = statement.executeQuery("SELECT * FROM PLAYER");
        Map<Integer, String> player = new HashMap<>();
        while (database.next()){
            player.put(database.getInt(1), database.getString(2));
        }
        statement.close();
        map.setPlayerMap(player);
        return map.getPlayerMap();
    }

    private static Map<Integer, List<String>> getDatabaseProgress() throws SQLException {
        Map<Integer, List<String>> progress = new HashMap<>();
        statement = connection.createStatement();
        ResultSet database = statement.executeQuery("SELECT * FROM PROGRESS");
        while (database.next()) {
            List<String> lst = null;
            for (int i = 0; i < 4; i++) {
                lst = new ArrayList<>();
                lst.add(database.getString(1));
                lst.add(database.getString(3));
                lst.add(database.getString(4));
                lst.add(database.getString(5));
            }
            progress.put(database.getInt(2), lst);
        }
        statement.close();
//        map.setProgressMap(progress);
        return progress;
    }

    private static Map<Integer, List<String>> getDatabaseItem() throws SQLException {
        Map<Integer, List<String>> item = new HashMap<>();
        statement = connection.createStatement();
        ResultSet database = statement.executeQuery("SELECT * FROM ITEM");
        while (database.next()) {
            List<String> lst = null;
            for (int i = 0; i < 4; i++) {
                lst = new ArrayList<>();
                lst.add(database.getString(1));
                lst.add(database.getString(3));
                lst.add(database.getString(4));
                lst.add(database.getString(5));
            }
            item.put(database.getInt(2), lst);
        }
        statement.close();

        return item;
    }

    private static Map<Integer, List<String>> getDatabaseCurrency() throws SQLException {
        Map<Integer, List<String>> currency = new HashMap<>();
        statement = connection.createStatement();
        ResultSet database = statement.executeQuery("SELECT * FROM CURRENCY");
        while (database.next()) {
            List<String> lst = null;
            for (int i = 0; i < 4; i++) {
                lst = new ArrayList<>();
                lst.add(database.getString(1));
                lst.add(database.getString(3));
                lst.add(database.getString(4));
                lst.add(database.getString(5));
            }
            currency.put(database.getInt(2), lst);
        }
        statement.close();

        return currency;
    }


    public static Map<Integer, String> getPlayer() throws SQLException {
        return getDatabasePlayer();
    }

    public static Map<Integer, List<String>> getProgress() throws SQLException {
        return getDatabaseProgress();
    }

    public static Map<Integer, List<String>> getItem() throws SQLException {
        return getDatabaseItem();
    }

    public static Map<Integer, List<String>> getCurrency() throws SQLException {
        return getDatabaseCurrency();
    }

}

