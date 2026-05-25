package task1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Students {

    static final String URL      = "jdbc:postgresql://localhost:5432/javalab";
    static final String USER     = "postgres";
    static final String PASSWORD = "postgres";

    private String studentId;
    private String direction;
    private String fullName;
    private String group;

    public String getStudentId()         { return studentId; }
    public void   setStudentId(String v) { studentId = v; }
    public String getDirection()         { return direction; }
    public void   setDirection(String v) { direction = v; }
    public String getFullName()          { return fullName; }
    public void   setFullName(String v)  { fullName = v; }
    public String getGroup()             { return group; }
    public void   setGroup(String v)     { group = v; }

    public void inputData(Scanner sc) {
        System.out.print("  ID студента:            "); studentId = sc.nextLine().trim();
        System.out.print("  Направление подготовки: "); direction = sc.nextLine().trim();
        System.out.print("  ФИО (Фамилия Имя Отч.): "); fullName  = sc.nextLine().trim();
        System.out.print("  Группа:                 "); group     = sc.nextLine().trim();
    }

    public void showAll() {
        List<Students> list = loadFromDb();
        printTable(list);
    }

    protected List<Students> loadFromDb() {
        List<Students> list = new ArrayList<>();
        String sql = "SELECT * FROM students4 ORDER BY id";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Students s = new Students();
                s.studentId = rs.getString("student_id");
                s.direction = rs.getString("direction");
                s.fullName  = rs.getString("full_name");
                s.group     = rs.getString("grp");
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка БД: " + e.getMessage());
        }
        return list;
    }

    protected void printTable(List<Students> list) {
        System.out.println("\n" + "-".repeat(95));
        System.out.printf("%-12s %-30s %-35s %-12s%n",
                "ID студ.", "Направление", "ФИО", "Группа");
        System.out.println("-".repeat(95));
        for (Students s : list)
            System.out.printf("%-12s %-30s %-35s %-12s%n",
                    s.studentId, s.direction, s.fullName, s.group);
        System.out.println("-".repeat(95));
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
