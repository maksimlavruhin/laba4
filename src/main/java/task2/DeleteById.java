package task2;

import java.sql.*;
import java.util.Scanner;

public final class DeleteById extends Listik {

    public static void run(Scanner sc) {
        System.out.print("Введите ID записи для удаления: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Неверный ID."); return;
        }

        String sql = "DELETE FROM lists4 WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Запись с id=" + id + " удалена.");
            else          System.out.println("Запись с id=" + id + " не найдена.");
        } catch (SQLException e) {
            System.out.println("Ошибка БД: " + e.getMessage());
        }
    }
}
