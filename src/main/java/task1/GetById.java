package task1;

import java.sql.*;
import java.util.Scanner;

public final class GetById extends Students {

    public static void run(Scanner sc) {
        System.out.print("Введите ID строки (числовой): ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Неверный ID."); return;
        }

        String sql = "SELECT * FROM students4 WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("\nНайден студент:");
                System.out.println("  ID записи:    " + rs.getInt("id"));
                System.out.println("  ID студента:  " + rs.getString("student_id"));
                System.out.println("  Направление:  " + rs.getString("direction"));
                System.out.println("  ФИО:          " + rs.getString("full_name"));
                System.out.println("  Группа:       " + rs.getString("grp"));
            } else {
                System.out.println("Запись с id=" + id + " не найдена.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка БД: " + e.getMessage());
        }
    }
}
