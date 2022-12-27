package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static crud.NotFoundException.notFoundException;
import static database.JDBCPostgreSQL.command;

public enum CRUD {

    DELETE("delete") {
        @Override
        public void make(String command, String str) {
            String condition = getCondition(str, getTableName(str));
            sql = "DELETE FROM " + getTableName(str) + " WHERE " + condition;
            command(sql);
            ChangeCash.value(getTableName(str)).remove(getCondition(condition));
        }
    },
    READ("read") {
        @Override
        public void make(String command, String table_name) throws SQLException {
            ChangeCash.value(table_name).read();
        }
    },
    CREATE("create") {
        @Override
        public void make(String command, String str) {
            String condition = getCondition(str, getTableName(str));
            sql = "INSERT INTO " + getTableName(str) + " VALUES (" + condition + ")";
            command(sql);
            ChangeCash.value(getTableName(str)).insert(getCondition(condition));
        }
    },
    UPDATE("update") {
        @Override
        public void make(String command, String str) {
            String condition = getCondition(str, getTableName(str));
            sql = "UPDATE " + getTableName(str) + " SET " + condition;
            command(sql);
//            System.out.println(Arrays.toString(CRUD.getCondition(condition.replace("where", ","))));
            ChangeCash.value(getTableName(str)).update(getCondition(condition.replace("where", ",")));
        }
    };


    private final String command;
    public static String sql;


    CRUD(String command) {
        this.command = command;
    }


    private static String[] getCondition(String condition){
        return condition.replace(" ","").trim().split("[=,]");
    }

    private static String getCondition(String condition, String table_name){
        return condition.replaceFirst(table_name, "").trim();
    }

    private static String getTableName(String condition){
        int index = condition.indexOf(" ");
        return condition.substring(0, index);
    }


    public abstract void make(String command, String condition) throws SQLException;

    public static CRUD value(String command) {
        return Arrays.stream(values())
                .filter(it -> it.command.equals(command))
                .findFirst()
                .orElseThrow(notFoundException("Oops, incorrect expression!"));
    }
}
