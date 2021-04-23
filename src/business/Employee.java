package business;

import java.time.LocalDate;

public class Employee {
	
	// instance variables
	private int employeeID;
	private String employeeLastName;
	private String employeeFirstName;
	private String employeeEmail;
	private String employeeClaimType;
	private LocalDate dateOfService;
	private float amountReq;
	
	// empty constructor
	public Employee() {
	}

	// constructor that accepts arguments
	public Employee(int employeeID, String employeeLastName, String employeeFirstName, String employeeEmail,
			String employeeClaimType, LocalDate dateOfService, float amountReq) {
		super();
		this.employeeID = employeeID;
		this.employeeLastName = employeeLastName;
		this.employeeFirstName = employeeFirstName;
		this.employeeEmail = employeeEmail;
		this.employeeClaimType = employeeClaimType;
		this.dateOfService = dateOfService;
		this.amountReq = amountReq;
	}

	// getters and setters
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeClaimType() {
		return employeeClaimType;
	}

	public void setEmployeeClaimType(String employeeClaimType) {
		this.employeeClaimType = employeeClaimType;
	}

	public LocalDate getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(LocalDate dateOfService) {
		this.dateOfService = dateOfService;
	}

	public float getAmountReq() {
		return amountReq;
	}

	public void setAmountReq(float amountReq) {
		this.amountReq = amountReq;
	}
}
