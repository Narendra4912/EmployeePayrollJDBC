package com.blz.employeeservicepkg;

public class EmployeeService {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        //employeeService.retrieveData();
        //employeeService.updateData("Sanket",30000.0);
        employeeService.updateDataUsingPreparedStatement("Sanket",30000.0);

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
