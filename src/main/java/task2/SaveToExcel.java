package task2;

import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.sql.*;

public final class SaveToExcel extends Listik {

    public static void save() {
        String fileName = "task2_lists.xlsx";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM lists4 ORDER BY list_type, idx")) {

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Списки");

            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Тип списка");
            header.createCell(2).setCellValue("Индекс");
            header.createCell(3).setCellValue("Значение");

            System.out.println("\nДанные из таблицы lists4:");
            System.out.printf("%-8s %-12s %-8s %-20s%n",
                    "ID", "Тип", "Индекс", "Значение");
            System.out.println("-".repeat(52));

            int rowNum = 1;
            int printedRandom = 0;
            while (rs.next()) {
                int    id       = rs.getInt("id");
                String listType = rs.getString("list_type");
                int    idx      = rs.getInt("idx");
                String value    = rs.getString("value");

                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(id);
                row.createCell(1).setCellValue(listType);
                row.createCell(2).setCellValue(idx);
                row.createCell(3).setCellValue(value);

                if ("input".equals(listType)) {
                    System.out.printf("%-8d %-12s %-8d %-20s%n", id, listType, idx, value);
                } else if (printedRandom < 20) {
                    System.out.printf("%-8d %-12s %-8d %-20s%n", id, listType, idx, value);
                    printedRandom++;
                    if (printedRandom == 20)
                        System.out.println("  ... (показаны первые 20 из 1000 random, все в Excel)");
                }
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
