package db;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

import business.Employee;

public final class EmployeeTextFile implements DAO<Employee> {
	private List<Employee> employee = null;
    private Path employeePath = null;
    private File employeeFile = null;
    private final String FIELD_SEP = "\t";

    public EmployeeTextFile() 
    {
        employeePath = Paths.get("employees.txt");
        employeeFile = employeePath.toFile();
        employee = this.getAll();
    }


    public List<Employee> getAll() 
    {
        if (employee != null) 
        {
            return employee;
        }

        employee = new ArrayList<>();
        if (Files.exists(employeePath)) 
        {
            try (BufferedReader in = new BufferedReader(
                                     new FileReader(employeeFile))) 
            {

                String line = in.readLine();
                while (line != null) {
                    String[] fields = line.split(FIELD_SEP);
                    int empID = Integer.parseInt(fields[0]);
                    String fName = fields[1];
                    String lName = fields[2];
                    String email = fields[3];
                    String typeOfClaim = fields[4];
                    LocalDate dateOfService = LocalDate.parse(fields[5]);
                    float amountRequested = Float.parseFloat(fields[6]);
                    
                    
                    Employee e = new Employee(empID, fName, lName, email, typeOfClaim, dateOfService, amountRequested);
                    employee.add(e);

                    line = in.readLine();
                }
            } catch (IOException e) 
            {
                System.out.println(e);
                return null;
            }
        } 
        else 
        {
            System.out.println(
                    employeePath.toAbsolutePath() + " doesn't exist.");
            return null;            
        }
        return employee;
    }


    public Employee get(int empID) 
    {
        for (Employee e : employee) 
        {
            if (e.getEmployeeID() == empID) 
            {
                return e;
            }
        }
        return null;
    }
    

    private boolean saveAll() {
        try (PrintWriter out = new PrintWriter(
                               new BufferedWriter(
                               new FileWriter(employeeFile)))) 
        {

            // write all products in the array list
            // to the file
            for (Employee e : employee) {
                out.print(e.getEmployeeID() + FIELD_SEP);
                out.print(e.getEmployeeFirstName() + FIELD_SEP);
                out.print(e.getEmployeeLastName() + FIELD_SEP);
                out.print(e.getEmployeeEmail() + FIELD_SEP);
                out.print(e.getEmployeeClaimType() + FIELD_SEP);
                out.print(e.getDateOfService() + FIELD_SEP);
                out.println(e.getAmountReq());
            }
            return true;
        } 
        catch (IOException e) 
        {
            System.out.println(e);
            return false;
        }
    }
    

    public boolean add(Employee e) 
    {
        employee.add(e);
        return this.saveAll();
    }


    public boolean delete(Employee e) 
    {
        employee.remove(e);
        return this.saveAll();
    }


    public boolean update(Employee newEmployee) 
    {
        Employee oldEmployee = this.get(newEmployee.getEmployeeID());
        int i = employee.indexOf(oldEmployee);
        employee.remove(i);

        employee.add(i, newEmployee);

        return this.saveAll();
    }


}
