package main;

import crud.Print;
import database.JDBCPostgreSQL;
import server.StartServer;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {
//        Проверка установки соединения с бд
        JDBCPostgreSQL.testConnect();
//        Создание таблиц
//        JDBCPostgreSQL.createTable();
//        Заполнение значениями
//        JDBCPostgreSQL.insertTable();
//        Получение значений из БД
//        Database.getDatabase();
//        Удаление таблиц
//        JDBCPostgreSQL.dropTable();

//        круды в консоли
//        Print.print();

        StartServer.server();


    }

}