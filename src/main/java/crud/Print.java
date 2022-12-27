package crud;

import database.CRUD;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Print {
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";

    private static String getCommand(){
        Scanner in = new Scanner(System.in);
        System.out.print("Command: ");
        return in.nextLine();
    }

    private static void getOperations(){
        System.out.printf("%90s", PURPLE + "OPERATIONS" + RESET);
        System.out.println();
        System.out.printf("%91s","------------------------------" );
        System.out.println();
        System.out.printf("%28s %37s %45s %55s %n", BLUE + "create", "read", "update", "delete" + RESET);
        System.out.printf("%-15s %20s %70s %46s %n", PURPLE + "create table_name value1, value2, value3, ...",
                "read table_name", "update table_name column1 = value1, ... where primary_key = value", "delete table_name primary_key = value" + RESET);
        System.out.printf(BLUE + "%82s","exit" + RESET);
        System.out.println();
        System.out.printf("%91s","------------------------------" );
        System.out.println();
    }

    public static void print() throws SQLException {
        getOperations();
        do{
            String[] command = getCommand().trim().split(" ", 2);
            if (Objects.equals(command[0], "exit")){
                System.exit(0);
            }
            CRUD.value(command[0]).make(command[0], command[1]);
        } while(true);
    }

}
