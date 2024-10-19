package bank.dao;

import bank.exception.AccountantException;
import bank.exception.CustomerException;
import bank.model.Accountant;
import bank.model.Customer;

public interface AccountantDao {

	public Accountant LoginAccountant(String accountantUsername,String accountantPassword) throws AccountantException;
						
	public int addCustomer(String customerName , String customerMail,String customerPassword,
			String customerMobile,String customerAddress) throws CustomerException;

	public String addAccount(int customerBalance,int cid) throws CustomerException;

	public String updateCustomer(int customerAccountNumber,String customerAddress) throws CustomerException;

	public String deleteAccount(int customerAccountNumber) throws CustomerException;
	
	public Customer viewCustomer(String customerAccountNumber) throws CustomerException;

	public Customer viewAllCustomer() throws CustomerException;
}
