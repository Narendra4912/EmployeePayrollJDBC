package com.blz.employeeservicepkg;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModule {

    private Connection getConnection() {
        Connection connection = null;
        String dbUrl = "jdbc:mysql://localhost:3300/PayrollService";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, "root", "root");
        } catch (SQLException e) {
            System.out.println("not established");
        } catch (ClassNotFoundException e) {
            System.out.println("drivers not loaded");
        }

        return connection;
    }
    public List<Employee> retrieveDate() {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String query = "select * from employee_payroll";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setEmpId(resultSet.getInt("empId"));
                employee.setEmpName(resultSet.getString("empName"));
                employee.setAddress(resultSet.getString("address"));
                employee.setPhoneNo(resultSet.getLong("phoneNo"));
                employee.setGender(resultSet.getString("gender").charAt(0));
                employee.setDepartment(resultSet.getString("department"));
                employee.setStartDate(resultSet.getDate("startDate"));
                employee.setSalary(resultSet.getDouble("salary"));

                employeeList.add(employee);

            }
        } catch (SQLException e) {
        }

        return employeeList;
    }
}
