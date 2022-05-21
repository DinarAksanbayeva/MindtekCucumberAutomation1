package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {
    //Java Database Connectivity
    public static void main(String[] args) throws SQLException {

        //Interfaces
        //Connection,Statement,ResultSet

        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/HR_production",
                "postgres",
                "admin"
        );

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("Select * from employees");
        resultSet.next();
        System.out.println(resultSet.getString("first_name"));
        System.out.println(resultSet.getString("salary"));


        resultSet.next();
        System.out.println(resultSet.getString("first_name"));

        resultSet.next();
        System.out.println(resultSet.getString("first_name"));
        System.out.println(resultSet.getString("email"));
        System.out.println(resultSet.getString("department_id"));

        ResultSetMetaData rsMetaData= resultSet.getMetaData();
        System.out.println(rsMetaData.getColumnName(1));
        System.out.println(rsMetaData.getColumnCount());
        System.out.println(rsMetaData.getTableName(1));

        ResultSet resultSetForIT= statement.executeQuery("select first_name,last_name from employees\n" +
                "where department_id=6");
        resultSetForIT.next();
        resultSetForIT.next();
        System.out.println(resultSetForIT.getString("Last_name"));



        ResultSet rsHighSalaries= statement.executeQuery("\n" +
                "select * \n" +
                "from employees \n" +
                "where salary>12000 ");
        ResultSetMetaData rsMetaDataHighS=rsHighSalaries.getMetaData();
        System.out.println("==========================================");

        while(rsHighSalaries.next()){
            System.out.println(rsHighSalaries.getString("first_name"));

        }
        System.out.println("+++++++++++++++++++++++++++++++++++");
        for(int order=1;order<=rsMetaDataHighS.getColumnCount();order++){
            System.out.println(rsMetaDataHighS.getColumnName(order));
        }

        List<Map<String,Object>> tableData=new ArrayList<>();

        rsHighSalaries.beforeFirst();

        while (rsHighSalaries.next()){
            Map<String,Object> rowData=new HashMap<>();
            for(int order=1;order<=rsMetaDataHighS.getColumnCount();order++){
                rowData.put(rsMetaDataHighS.getColumnName(order),rsHighSalaries.getString(rsMetaDataHighS.getColumnName(order)));
            }
            tableData.add(rowData);
        }
        System.out.println(tableData);

        System.out.println(tableData.get(1).get("salary"));

        System.out.println(tableData.get(5).get("last_name"));

        System.out.println(tableData.get(3).get("job_id"));



    }
}
