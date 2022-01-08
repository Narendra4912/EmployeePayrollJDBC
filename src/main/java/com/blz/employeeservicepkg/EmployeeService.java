package com.blz.employeeservicepkg;

public class EmployeeService {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        //employeeService.retrieveData();
        employeeService.updateDate("Sanket",30000.0);
    }

    private void updateDate(String empName, double salary) {

        EmployeeModule employeeModule = new EmployeeModule();
        employeeModule.updateDate(empName,salary);
    }

    private void retrieveData() {

        EmployeeModule employeeModule = new EmployeeModule();
        System.out.println(employeeModule.retrieveDate());
    }
}
