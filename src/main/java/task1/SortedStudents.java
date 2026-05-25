package task1;

import java.util.List;

public final class SortedStudents extends Students {

    @Override
    public void showAll() {
        List<Students> list = loadFromDb();
        list.sort((a, b) -> {
            String surnameA = a.getFullName().split("\\s+")[0];
            String surnameB = b.getFullName().split("\\s+")[0];
            return surnameA.compareToIgnoreCase(surnameB);
        });
        System.out.println("(Отсортировано по алфавиту по фамилии)");
        printTable(list);
    }
}
