package task2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Listik {

    static final String URL      = "jdbc:postgresql://localhost:5432/javalab";
    static final String USER     = "postgres";
    static final String PASSWORD = "postgres";

    protected List<Integer> randomList = new ArrayList<>();
    protected List<String>  inputList  = new ArrayList<>();

    protected void random() {
        randomList.clear();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++)
            randomList.add(rand.nextInt(10000));
    }

    protected void input(Scanner sc) {
        inputList.clear();
        for (int i = 0; i < 10; i++) {
            System.out.print("  Строка " + (i + 1) + ": ");
            inputList.add(sc.nextLine());
        }
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
