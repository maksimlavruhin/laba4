package task2;

import java.sql.*;
import java.util.Scanner;

public final class InputLists extends Listik {

    @Override
    protected void random() {
        super.random();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM lists4 WHERE list_type = 'random'");
        } catch (SQLException e) {
            System.out.println("Ошибка очистки: " + e.getMessage()); return;
        }

        String sql = "INSERT INTO lists4 (list_type, idx, value) VALUES ('random', ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < randomList.size(); i++) {
                ps.setInt(1, i);
                ps.setString(2, String.valueOf(randomList.get(i)));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            System.out.println("Ошибка БД: " + e.getMessage()); return;
        }

        System.out.println("Случайный список (1000 int) сохранён в БД.");
        System.out.print("Первые 20 значений: ");
        for (int i = 0; i < 20; i++) System.out.print(randomList.get(i) + " ");
        System.out.println("...");
    }

    @Override
    protected void input(Scanner sc) {
        super.input(sc);

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM lists4 WHERE list_type = 'input'");
        } catch (SQLException e) {
            System.out.println("Ошибка очистки: " + e.getMessage()); return;
        }

        String sql = "INSERT INTO lists4 (list_type, idx, value) VALUES ('input', ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < inputList.size(); i++) {
                ps.setInt(1, i);
                ps.setString(2, inputList.get(i));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            System.out.println("Ошибка БД: " + e.getMessage()); return;
        }

        System.out.println("\nСтроковый список сохранён в БД:");
        for (int i = 0; i < inputList.size(); i++)
            System.out.printf("  [%2d] %s%n", i, inputList.get(i));
    }

    public static void run(Scanner sc) {
        InputLists il = new InputLists();

        System.out.println("\n--- Генерация случайного списка из 1000 int (метод random) ---");
        il.random();

        System.out.println("\n--- Ввод строкового списка из 10 строк (метод input) ---");
        il.input(sc);
    }
}
