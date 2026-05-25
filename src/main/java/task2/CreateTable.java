package task2;

import java.sql.*;

public final class CreateTable extends Listik {

    public static void create() {
        String sql = "CREATE TABLE IF NOT EXISTS lists4 (" +
                "id        SERIAL PRIMARY KEY, " +
                "list_type VARCHAR(20), " +
                "idx       INTEGER, " +
                "value     TEXT" +
                ")";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица 'lists4' создана (или уже существует).");
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
