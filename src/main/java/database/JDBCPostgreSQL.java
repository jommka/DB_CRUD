package database;

import jsonparsing.DataMap;
import player.Currency;
import player.Item;
import player.Progress;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPostgreSQL {

    public static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    public static final String USER = "postgres";
    public static final String PASS = "1403";
    public static final DataMap map = new DataMap();
    public static Connection connection;
    public static Statement statement;
    public static String sql;

    public static void testConnect(){
        connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);

        } catch (ClassNotFoundException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    protected Statement getStatement(){
        return statement;
    }

    private static void createTablePlayer(){
        sql = "CREATE TABLE PLAYER (playerId INT PRIMARY KEY NOT NULL," +
                " nickname VARCHAR(50))";
        command(sql);
        System.out.println("-- Table PLAYER created successfully");
    }

    private static void createTableProgress(){
        sql = "CREATE TABLE PROGRESS (playerId INT NOT NULL," +
                "id INT PRIMARY KEY NOT NULL, " +
                "resourceId INT NOT NULL, " +
                "score INT DEFAULT NULL, " +
                "maxScore INT DEFAULT NULL)";
        command(sql);
        System.out.println("-- Table PROGRESS created successfully");

    }

    private static void createTableItem(){
        try {
            statement = connection.createStatement();
            sql = "CREATE TABLE ITEM (playerId INT NOT NULL," +
                    "id INT PRIMARY KEY NOT NULL, " +
                    "resourceId INT NOT NULL, " +
                    "count INT DEFAULT NULL, " +
                    "level INT DEFAULT NULL)";
            statement.execute(sql);
            statement.close();
            connection.commit();
            System.out.println("-- Table ITEM created successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTableCurrency(){
        sql = "CREATE TABLE CURRENCY (playerId INT NOT NULL," +
                "id INT PRIMARY KEY NOT NULL, " +
                "resourceId INT NOT NULL, " +
                "name VARCHAR(50), " +
                "count INT DEFAULT NULL)";
        command(sql);
        System.out.println("-- Table CURRENCY created successfully");
    }

    private static void insertTablePlayer(){
        for (Integer name: map.getPlayerMap().keySet()){
            sql = "INSERT INTO player (playerId, nickname) VALUES (" +
                    name.toString() + ", '" + map.getPlayerMap().get(name) +
                    "' );";
            command(sql);

        }
        System.out.println("-- Table UPDATE successfully");
    }

    private static void insertTableProgress(){
        for (Integer key: map.getProgressMap().keySet()){
            for (Progress p: map.getProgressMap().get(key)){
                sql = "INSERT INTO progress (playerId, id, resourceId, score, maxScore) VALUES (" +
                        p.playerId + ", " + p.id + ", " + p.resourceId + ", " + p.score +
                        ", " + p.maxScore + " );";
                command(sql);
            }
        }
        System.out.println("-- Table UPDATE successfully");
    }

    private static void insertTableItem(){
        for (Integer key: map.getItemMap().keySet()){
            for (Item p: map.getItemMap().get(key)){
                sql = "INSERT INTO item (playerId, id, resourceId, count, level) VALUES (" +
                        p.playerId + ", " + p.id + ", " + p.resourceId + ", " + p.count +
                        ", " + p.level + " );";
                command(sql);
            }
        }
        System.out.println("-- Table UPDATE successfully");
    }

    private static void insertTableCurrency(){
        for (Integer key: map.getCurrencyMap().keySet()){
            for (Currency p: map.getCurrencyMap().get(key)){
                sql = "INSERT INTO currency (playerId, id, resourceId, name, count) VALUES (" +
                        p.playerId + ", " + p.id + ", " + p.resourceId + ", '" + p.name +
                        "', " + p.count + " );";
                command(sql);
            }
        }
        System.out.println("-- Table UPDATE successfully");
    }

    private static void dropTable(String nameTable){
        command("DROP TABLE " + nameTable);
        System.out.println("-- Table DROP successfully");
    }


    public static void command(String command){
        try {
            statement = connection.createStatement();
            statement.execute(command);
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createTable(){
        createTablePlayer();
        createTableCurrency();
        createTableItem();
        createTableProgress();
    }

    public static void insertTable(){
        insertTablePlayer();
        insertTableProgress();
        insertTableCurrency();
        insertTableItem();
    }

    public static void dropTable(){
        dropTable("PLAYER");
        dropTable("CURRENCY");
        dropTable("ITEM");
        dropTable("PROGRESS");
    }

}
