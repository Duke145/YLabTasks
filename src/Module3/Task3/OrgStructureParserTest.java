package Module3.Task3;

import java.io.File;
import java.io.IOException;

public class OrgStructureParserTest {
    public static void main(String[] args) throws IOException {
        OrgStructureParser orgStructureParser = new OrgStructureParserImpl();
        Employee boss = orgStructureParser.parseStructure(new File("src\\Module3\\Task3\\data.csv"));
        System.out.println("Главный Босс: " + boss);
        System.out.println("Сотрудники босса: " + boss.getSubordinate());
        System.out.println("Сотрудники первого сотрудника главного босса: " + boss.getSubordinate().get(0).getSubordinate());
        System.out.println("Босс первого сотрудника первого сотрудника главного босса: " + boss.getSubordinate().get(0).getSubordinate().get(0).getBoss());
    }
}
