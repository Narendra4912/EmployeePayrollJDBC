package com.blz.employeeservicepkg;

public class EmployeeService {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        employeeService.retrieveData();
    }

    private void retrieveData() {

        EmployeeModule employeeModule = new EmployeeModule();
        System.out.println(employeeModule.retrieveDate());
    }
}
