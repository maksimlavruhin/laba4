package task2;

import java.util.Scanner;

public class InformSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Часть 2: Listik — коллекции (ООП) ===");
            System.out.println("1. Вывести все таблицы из базы данных PostgreSQL");
            System.out.println("2. Создать таблицу в базе данных PostgreSQL");
            System.out.println("3. Ввести список и сохранить в PostgreSQL");
            System.out.println("4. Удалить элемент из списка в PostgreSQL по ID");
            System.out.println("5. Сохранить итоговые результаты из PostgreSQL в Excel и вывести их в консоль");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": ShowTables.show();        break;
                case "2": CreateTable.create();     break;
                case "3": InputLists.run(sc);       break;
                case "4": DeleteById.run(sc);       break;
                case "5": SaveToExcel.save();       break;
                case "0": System.out.println("Выход."); sc.close(); return;
                default:  System.out.println("Неверный выбор.");
            }
        }
    }
}
