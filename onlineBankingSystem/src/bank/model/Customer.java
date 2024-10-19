package bank.model;

public class Customer {

	private int customerAccountNumber;
	private String customerName;
	private int customerBalance;
	private String customerMail;
	private String customerPassword;
	private String customerMobile;
	private String customerAddress;
	
	
	
	public Customer() {
		super();
	}



	public Customer(int customerAccountNumber, String customerName, int customerBalance, String customerMail,
			String customerPassword, String customerMobile, String customerAddress) {
		super();
		this.customerAccountNumber = customerAccountNumber;
		this.customerName = customerName;
		this.customerBalance = customerBalance;
		this.customerMail = customerMail;
		this.customerPassword = customerPassword;
		this.customerMobile = customerMobile;
		this.customerAddress = customerAddress;
	}



	public int getCustomerAccountNumber() {
		return customerAccountNumber;
	}



	public void setCustomerAccountNumber(int customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public int getCustomerBalance() {
		return customerBalance;
	}



	public void setCustomerBalance(int customerBalance) {
		this.customerBalance = customerBalance;
	}



	public String getCustomerMail() {
		return customerMail;
	}



	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}



	public String getCustomerPassword() {
		return customerPassword;
	}



	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}



	public String getCustomerMobile() {
		return customerMobile;
	}



	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}



	public String getCustomerAddress() {
		return customerAddress;
	}



	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}



	@Override
	public String toString() {
		return "Customer [customerAccountNumber=" + customerAccountNumber + ", customerName=" + customerName
				+ ", customerBalance=" + customerBalance + ", customerMail=" + customerMail + ", customerPassword="
				+ customerPassword + ", customerMobile=" + customerMobile + ", customerAddress=" + customerAddress
				+ "]";
	}



	
	
	
	
	
}
