package com.blz.employeeservicepkg;

import java.util.Date;

public class EmployeeService {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        //employeeService.retrieveData();
        //employeeService.updateData("Sanket",30000.0);
        //employeeService.updateDataUsingPreparedStatement("Sanket",30000.0);
        //employeeService.retrieveDataUsingPreparedStatement("Narendra");


//        Date date1 = new Date("2021/03/01");
//        Date date2 = new Date("2021/05/31");
//        employeeService.retrieveDataUsingPreparedStatementForDateRange(date1, date2);

    }

    private void retrieveDataUsingPreparedStatementForDateRange(Date date1, Date date2) {

    EmployeeModule employeeModule = new EmployeeModule();
        System.out.println(employeeModule.retrieveDataUsingPreparedStatementForDateRange(date1, date2));
    }

    private void retrieveDataUsingPreparedStatement(String empName) {

        EmployeeModule employeeModule = new EmployeeModule();
        System.out.println(employeeModule.retrieveDataUsingPreparedStatement(empName));
    }

    private void updateDataUsingPreparedStatement(String empName, double salary) {

        EmployeeModule employeeModule = new EmployeeModule();
        employeeModule.updateDataUsingPreparedStatement(empName,salary);
    }

    private void updateData(String empName, double salary) {

        EmployeeModule employeeModule = new EmployeeModule();
        employeeModule.updateData(empName,salary);
    }

    private void retrieveData() {

        EmployeeModule employeeModule = new EmployeeModule();
        System.out.println(employeeModule.retrieveData());
    }
}
