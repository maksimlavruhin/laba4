package task1;

import java.sql.*;

public final class ShowTables extends Students {

    public static void show() {
        try (Connection conn = getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, "public", "%", new String[]{"TABLE"});
            System.out.println("\nТаблицы в базе данных:");
            boolean found = false;
            while (rs.next()) {
                System.out.println("  - " + rs.getString("TABLE_NAME"));
                found = true;
            }
            if (!found) System.out.println("  (таблиц нет)");
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
