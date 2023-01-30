package guavaUtil;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;
import java.util.Set;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/12/6 23:05
 * @Description:
 */
public class GuavaTable {
    public static void main(String[] args) {
        Table<String,String,String> emplyeeTable = HashBasedTable.create();
        emplyeeTable.put("IBM","101","Mahesh");
        emplyeeTable.put("IBM","102","Ramesh");
        emplyeeTable.put("IBM","103","Suresh");

        emplyeeTable.put("Microsoft","111","Sohan");
        emplyeeTable.put("Microsoft","112","Mohan");
        emplyeeTable.put("Microsoft","113","Rohan");

        emplyeeTable.put("TCS","121","Mahesh");
        emplyeeTable.put("TCS","122","Shyam");
        emplyeeTable.put("TCS","123","Sunil");

        Map<String, String> ibmEmployees = emplyeeTable.row("IBM");
        System.out.println(ibmEmployees);
        System.out.println("List of IBM Employees");
        for (Map.Entry<String, String> entry : ibmEmployees.entrySet()) {
            System.out.println("Emp Id: " + entry.getKey() + ", Name: " + entry.getValue());
        }
        // get all the unique keys of the table
        Set<String> employers = emplyeeTable.rowKeySet();
        for (String employer : employers) {
            System.out.print(employer + " ");
        }
        System.out.println(" ");
        // get a Map corresponding to 102
        Map<String, String> employerMap = emplyeeTable.column("102");
        for (Map.Entry<String, String> entry : employerMap.entrySet()) {
            System.out.println("Employer: " + entry.getKey() + ",Name: " + entry.getValue());
        }

    }
}
