package task1;

import java.sql.*;
import java.util.Scanner;

public final class AddStudents extends Students {

    public static void run(Scanner sc) {
        System.out.print("Сколько студентов ввести (минимум 7)? ");
        int count;
        try {
            count = Integer.parseInt(sc.nextLine().trim());
            if (count < 7) { System.out.println("Минимум 7. Устанавливаю 7."); count = 7; }
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Устанавливаю 7."); count = 7;
        }

        AddStudents helper = new AddStudents();

        for (int i = 0; i < count; i++) {
            System.out.println("\nСтудент " + (i + 1) + ":");
            helper.inputData(sc);

            String sql = "INSERT INTO students4 (student_id, direction, full_name, grp) VALUES (?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, helper.getStudentId());
                ps.setString(2, helper.getDirection());
                ps.setString(3, helper.getFullName());
                ps.setString(4, helper.getGroup());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Ошибка БД: " + e.getMessage());
            }
        }

        System.out.println("\nВсе студенты сохранены.");

        // Полиморфный вызов: Students → SortedStudents
        System.out.println("\n--- Несортированный список (Students.showAll) ---");
        Students unsorted = new Students();
        unsorted.showAll();

        System.out.println("\n--- Отсортированный список по фамилии (SortedStudents.showAll) ---");
        Students sorted = new SortedStudents();
        sorted.showAll();
    }
}
