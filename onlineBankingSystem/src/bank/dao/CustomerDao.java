package bank.dao;

import bank.exception.CustomerException;
import bank.model.Customer;

public interface CustomerDao {

	public Customer LoginCustomer(String customerUsername,String customerPassword,int customerAccountNumber)
			throws CustomerException;
	
	public int viewBalance(int customerAccountNumber) throws CustomerException;
	
	public int Deposit(int customerAccountNumber,int amount) throws CustomerException;

	public int Withdraw(int customerAccountNumber,int amount) throws CustomerException;
	
	public int Transfer(int customerAccountNumber,int amount,int customerAccountNumber2) throws CustomerException;
}
