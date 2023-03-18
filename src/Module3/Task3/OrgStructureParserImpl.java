package Module3.Task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class OrgStructureParserImpl implements OrgStructureParser {
    private Map<Long, Employee> employees = new HashMap<>();

    @Override
    public Employee parseStructure(File csvFile) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(csvFile);
             Scanner scanner = new Scanner(fileInputStream)) {
            Employee newEmployee;
            Employee Boss = null;
            boolean bossCond;
            while (scanner.hasNextLine()) {
                bossCond = false;
                String stringFromFile = scanner.nextLine();
                newEmployee = new Employee();
                String[] tempMassive = stringFromFile.split(";");
                if (tempMassive.length == 4 && tempMassive[0].matches("\\d+") && tempMassive[1].matches("\\d*")) {
                    newEmployee.setId(Long.parseLong(tempMassive[0]));
                    if (tempMassive[1].equals("")) {
                        bossCond = true;
                    } else {
                        newEmployee.setBossId(Long.parseLong(tempMassive[1]));
                    }
                    newEmployee.setName(tempMassive[2]);
                    newEmployee.setPosition(tempMassive[3]);
                    employees.put(newEmployee.getId(), newEmployee);
                }
                if (bossCond) {
                    Boss = newEmployee;
                }
            }
            for (Map.Entry<Long, Employee> entry : employees.entrySet()) {
                //Проверка на наличие босса сотрудника по ID
                if (employees.containsKey(entry.getValue().getBossId())) {
                    //Добавление сотрудника в список сотрудников босса
                    employees.get(entry.getValue().getBossId()).getSubordinate().add(entry.getValue());
                    //Добавление босса в соответствующее поле сотрудника
                    entry.getValue().setBoss(employees.get(entry.getValue().getBossId()));
                }
            }
            //Вывод списка сотрудников, для наглядности
//            for (Map.Entry<Long,Employee> entry : employees.entrySet()) {
//                System.out.println("Сотрудник: " + entry.getValue());
//                System.out.println("Непосредственный начальник: " + entry.getValue().getBoss());
//                System.out.println("Список прямых подчиненных: " + entry.getValue().getSubordinate());
//                System.out.println();
//            }
            return Boss;
        }
    }
}
