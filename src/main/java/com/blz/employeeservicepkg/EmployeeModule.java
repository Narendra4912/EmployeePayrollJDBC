package com.blz.employeeservicepkg;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    public List<Employee> retrieveData() {

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

    public void updateData(String empName, double salary) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String query = String.format("update employee_payroll set salary = %.2f where empName = '%s'", salary, empName);
            int result = statement.executeUpdate(query);
            if (result >= 1)
                System.out.println("Employee Record Updated Successfully !!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateDataUsingPreparedStatement(String empName, double salary) {

        try (Connection connection = getConnection()) {
            String query = "update employee_payroll set salary = ? where empName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, salary);
            preparedStatement.setString(2, empName);
            int result = preparedStatement.executeUpdate();
            if (result >= 1)
                System.out.println("Employee Record Updated Successfully !!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee retrieveDataUsingPreparedStatement(String empName) {

        Employee employee = new Employee();

        try (Connection connection = getConnection()) {
            String query = "select * from employee_payroll where empName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, empName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setEmpId(resultSet.getInt("empId"));
                employee.setEmpName(resultSet.getString("empName"));
                employee.setAddress(resultSet.getString("address"));
                employee.setPhoneNo(resultSet.getLong("phoneNo"));
                employee.setGender(resultSet.getString("gender").charAt(0));
                employee.setDepartment(resultSet.getString("department"));
                employee.setStartDate(resultSet.getDate("startDate"));
                employee.setSalary(resultSet.getDouble("salary"));
            }
        } catch (SQLException e) {
        }

        return employee;
    }

    public List<Employee> retrieveDataUsingPreparedStatementForDateRange(Date date1, Date date2) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "select * from employee_payroll where startDate between ? and ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());

            preparedStatement.setDate(1, sqlDate1);
            preparedStatement.setDate(2, sqlDate2);
            ResultSet resultSet = preparedStatement.executeQuery();
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

    public void retrieveSpecificDataUsingFunctionAndGroupBy() {

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String query = "select gender,count(gender),sum(salary),avg(salary),min(salary),max(salary) " +
                    "from employee_payroll group by gender";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.print("Gender = " + resultSet.getString(1).charAt(0) + " ");
                System.out.print("Count = " + resultSet.getInt(2) + " ");
                System.out.print("Sum = " + resultSet.getInt(3) + " ");
                System.out.print("Avg = " + resultSet.getInt(4) + " ");
                System.out.print("Min = " + resultSet.getInt(5) + " ");
                System.out.print("Max = " + resultSet.getInt(6) + " ");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
