import java.util.*;

public class ArrayFilteringAndMapping {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<Object>> employeesList = new ArrayList<>();
        List<Object> employee = new ArrayList<>();
        String employeeData;
        int threshold = 65000;

        for(int i = 0 ; i < 6; i++){
            employee = new ArrayList<>();
            for(int j = 0 ; j < 4; j++) {
                employeeData = scanner.nextLine();
                employee.add((Object)employeeData);
            }
            employeesList.add(employee);

        }
        scanner.close();
        for(List<Object> employeeObj : employeesList){
            System.out.println(
                    employeeObj.get(0) +" "+
                    employeeObj.get(1) +" "+
                    employeeObj.get(2) +" "+
                    employeeObj.get(3)
            );
        }
        for(List<Object> department : filterAndGroup(employeesList, threshold)){
            System.out.println(department);
        }
    }

    public static List<List<Object>> filterAndGroup(List<List<Object>> employeesList, int threshold){
        Map<String, List<Integer>> departmentObjects = new TreeMap<>();
        List<Integer> departmentSalaryList;
        String departmentName;
        int employeeSalary = 0, averageSalary = 0;
        String isActive = "";
        List<Integer> departmentInfo = new ArrayList<>();
        List<Object> individualObject;
        List<List<Object>> result = new ArrayList<>();

        for(List<Object> employee : employeesList){
            employeeSalary = Integer.parseInt((String)employee.get(2));
            isActive = ((String)employee.get(3));
            if(isActive.equals("1") && employeeSalary > threshold){
                departmentName = (String)employee.get(1);

                if(departmentObjects.containsKey(departmentName)) {
                    departmentSalaryList = departmentObjects.get(departmentName);
                    departmentSalaryList.add(employeeSalary);
                }else{
                    departmentSalaryList = new ArrayList<>();
                    departmentSalaryList.add(employeeSalary);
                }
                departmentObjects.put(departmentName, departmentSalaryList);
            }
        }

        for(Map.Entry<String, List<Integer>> entry : departmentObjects.entrySet()){
            individualObject = new ArrayList<>();
            departmentInfo = entry.getValue();
            for(int salary : departmentInfo){
                averageSalary += salary;
            }
            averageSalary = averageSalary / departmentInfo.size();
            individualObject.add(entry.getKey());
            individualObject.add((int)averageSalary);
            result.add(individualObject);
            averageSalary = 0;
        }
        return result;
    }
}
