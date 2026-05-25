package task1;

import java.util.Scanner;

public class InformSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Часть 1: Студенты + коллекции (ООП) ===");
            System.out.println("1. Вывести все таблицы из базы данных PostgreSQL");
            System.out.println("2. Создать таблицу в базе данных PostgreSQL");
            System.out.println("3. Ввести данные о всех студентах и сохранить список в PostgreSQL с последующим табличным выводом в консоль");
            System.out.println("4. Вывести данные о студенте по ID из PostgreSQL");
            System.out.println("5. Удалить данные о студенте из PostgreSQL по ID");
            System.out.println("6. Сохранить итоговые результаты из PostgreSQL в Excel и вывести их в консоль");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": ShowTables.show();        break;
                case "2": CreateTable.create();     break;
                case "3": AddStudents.run(sc);      break;
                case "4": GetById.run(sc);          break;
                case "5": DeleteById.run(sc);       break;
                case "6": SaveToExcel.save();       break;
                case "0": System.out.println("Выход."); sc.close(); return;
                default:  System.out.println("Неверный выбор.");
            }
        }
    }
}
