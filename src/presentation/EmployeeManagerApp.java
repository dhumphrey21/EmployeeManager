//Programmer: Daniel Humphrey
//Program: Java04
//Description: Employee manager application

package presentation;

import business.Employee;
import business.Validation;
import db.DAO;
import db.EmployeeTextFile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


import java.util.Scanner;

public class EmployeeManagerApp {
	
	private static DAO<Employee> employeeFile = new EmployeeTextFile();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Employee Manager\n");
        displayMenu();

        // perform 1 or more actions
        String action = "";
        while (!action.equalsIgnoreCase("exit")) 
        {
            // get the input from the user
            System.out.print("Enter a command: ");
            action = sc.nextLine();
            System.out.println();

            if (action.equalsIgnoreCase("list")) 
            {
                displayAllEmployees();
            } 
            else if (action.equalsIgnoreCase("add")) 
            {
                addEmployee();
            } 
            else if (action.equalsIgnoreCase("del") || 
                       action.equalsIgnoreCase("delete")) 
            {
                deleteEmployee();
            } 
            else if (action.equalsIgnoreCase("help") || 
                       action.equalsIgnoreCase("menu")) 
            {
                displayMenu();
            } 
            else if (action.equalsIgnoreCase("exit")) 
            {
                System.out.println("Bye.\n");
            } 
            else 
            {
                System.out.println("Error! Not a valid command.\n");
            }
        }
    }
	
    public static void displayMenu() 
    {
        System.out.println("COMMAND MENU");
        System.out.println("list    - List all employees");
        System.out.println("add     - Add an employee");
        System.out.println("del     - Delete an employee");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }
    
    public static void displayAllEmployees() 
    {
        System.out.println("EMPLOYEE LIST");

        List<Employee> employees = employeeFile.getAll();
        StringBuilder sb = new StringBuilder();
        for (Employee e : employees) {
            sb.append(StringUtils.padWithSpaces(
                    Integer.toString(e.getEmployeeID()), 8));
            sb.append(StringUtils.padWithSpaces(
                    e.getEmployeeLastName(), 20));
            sb.append(StringUtils.padWithSpaces(
                    e.getEmployeeFirstName(), 20));
            sb.append(StringUtils.padWithSpaces(
                    e.getEmployeeEmail(), 30));
            sb.append(StringUtils.padWithSpaces(
                    e.getEmployeeClaimType(), 5));
            sb.append(StringUtils.padWithSpaces(
                    e.getDateOfService().toString(), 30));
            sb.append(
                    e.getAmountReq());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
    public static void addEmployee() 
    {
    	String employeeID = "";
    	int min = 1;
    	int max = 99999;
		boolean errorCheck = false;		
	    Scanner sc = new Scanner(System.in);
	    		
			do
	  		{
	 			System.out.print("Please enter employee ID: ");
	 			employeeID = sc.nextLine();
	 			errorCheck = Validation.isInteger(employeeID,"Employee ID") &&
	 						 Validation.isWithinRangeInteger(employeeID, min, max,"Employee ID");
	 			
	 					 		
	  		}while(!errorCheck);
		
			String employeeLastName = "";
			errorCheck = false;		
			
			do
		  	{
		 		System.out.print("Please enter employee last name: ");
		 		employeeLastName = sc.nextLine();
		 		errorCheck = Validation.isStringPresent(employeeLastName,"Employee Last Name");
		 		
		  	}while(!errorCheck);
			
			String employeeFirstName = "";
			errorCheck = false;		
			
			do
		  	{
		 		System.out.print("Please enter employee first name: ");
		 		employeeFirstName = sc.nextLine();
		 		errorCheck = Validation.isStringPresent(employeeFirstName,"Employee First Name");
		 		
		  	}while(!errorCheck);
			
			String employeeEmail = "";
			errorCheck = false;		
			
			do
		  	{
		 		System.out.print("Please enter employee email: ");
		 		employeeEmail = sc.nextLine();
		 		errorCheck = Validation.isStringPresent(employeeEmail,"Employee Email");
		 		
		  	}while(!errorCheck);
			
			String employeeClaimType = "";
			errorCheck = false;		
	 		
			do
		  	{
				System.out.print("Please enter type of claim ('H' or 'C'): ");
		 		employeeClaimType = sc.nextLine();
		 		if (!employeeClaimType.equalsIgnoreCase("H") && !employeeClaimType.equalsIgnoreCase("C")) {
		 			System.out.println("Type of Claim must be either 'H' or 'C'. Please re-enter.  ");
		 		} else {
		 			errorCheck = true;
		 		}
		 		
		  	}while(!errorCheck);
			
			String dateOfService = "";
			LocalDate dateOfServiceConverted = LocalDate.now();
			errorCheck = false;		
			
			do
		  	{
				System.out.print("Please enter date of service: ");
		 		dateOfService = sc.nextLine();
		 		errorCheck = Validation.isDateValid(dateOfService);
		 			
		 		 if (errorCheck) {
		 			dateOfServiceConverted = LocalDate.parse(dateOfService);
					long numDays = ChronoUnit.DAYS.between(LocalDate.now(), dateOfServiceConverted);
			 		if (numDays < -60 || numDays > 0) 
			 		{
			 			System.out.println("Date of Service must within the last 60 days. Please re-enter.");
			 			errorCheck = false;
			 		} else 
			 		{
			 			errorCheck = true;
			 		}	
		 		
		 		 }		
		 		
		  	}while(!errorCheck);
			
			String amountReq = "";
			float minAmount = 1;
			float maxAmount = 10000;
			errorCheck = false;		

			do
			{   
				System.out.print("Please enter amount requested: ");
		  	 	amountReq = sc.nextLine();
		  	 	errorCheck = Validation.isDouble(amountReq, "Amount Requested")&&  
				     		 Validation.isWithinRangeDouble(amountReq, minAmount, maxAmount,"Amount Requested");
		    }while(!errorCheck); 
			
	        Employee employee = new Employee();
	        
	        employee.setEmployeeID(Integer.parseInt(employeeID));
	        employee.setEmployeeLastName(employeeLastName);
	        employee.setEmployeeFirstName(employeeFirstName);
	        employee.setEmployeeEmail(employeeEmail);
	        employee.setEmployeeClaimType(employeeClaimType.toUpperCase());
	        employee.setDateOfService(LocalDate.parse(dateOfService));
	        employee.setAmountReq(Float.parseFloat(amountReq));
	        employeeFile.add(employee);

	        System.out.println("Employee " + employee.getEmployeeID()
	        		+ " has been added.\n");
	    }

	    public static void deleteEmployee() 
	    {
	    	Scanner sc = new Scanner(System.in);
	    	
	        System.out.print("Enter employee ID to delete: ");
	        int employeeID = sc.nextInt();    
	        
	        Employee e = employeeFile.get(employeeID);
	        
	        if (e != null) 
	        {
	            employeeFile.delete(e);
	            System.out.println("Employee " + e.getEmployeeID()
	                    + " has been deleted.\n");
	        } 
	        else 
	        {
	            System.out.println("No employee matches that ID.\n");
	        }
	}
}
