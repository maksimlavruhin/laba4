package task1;

import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.sql.*;
import java.util.List;

public final class SaveToExcel extends Students {

    public static void save() {
        String fileName = "task1_students.xlsx";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students4 ORDER BY full_name")) {

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Студенты");

            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("ID студента");
            header.createCell(2).setCellValue("Направление");
            header.createCell(3).setCellValue("ФИО");
            header.createCell(4).setCellValue("Группа");

            System.out.println("\nДанные из таблицы students4 (отсортировано по ФИО):");
            System.out.printf("%-5s %-12s %-30s %-35s %-10s%n",
                    "ID", "ID студ.", "Направление", "ФИО", "Группа");
            System.out.println("-".repeat(95));

            int rowNum = 1;
            while (rs.next()) {
                int    id        = rs.getInt("id");
                String studentId = rs.getString("student_id");
                String direction = rs.getString("direction");
                String fullName  = rs.getString("full_name");
                String grp       = rs.getString("grp");

                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(id);
                row.createCell(1).setCellValue(studentId);
                row.createCell(2).setCellValue(direction);
                row.createCell(3).setCellValue(fullName);
                row.createCell(4).setCellValue(grp);

                System.out.printf("%-5d %-12s %-30s %-35s %-10s%n",
                        id, studentId, direction, fullName, grp);
            }

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                workbook.write(fos);
            }
            workbook.close();
            System.out.println("\nФайл сохранён: " + fileName);

        } catch (SQLException e) {
            System.out.println("Ошибка БД: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка файла: " + e.getMessage());
        }
    }
}
