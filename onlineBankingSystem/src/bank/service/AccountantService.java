package bank.service;

import bank.exception.AccountantException;
import bank.exception.CustomerException;
import bank.model.Accountant;
import bank.model.Customer;

public interface AccountantService {

	Accountant loginAccountant(String username, String password) throws AccountantException;
    
    int addCustomer(String name, String email, String password, String mobile, String address) throws CustomerException;
    
    void addAccount(int balance, int customerId) throws CustomerException;
    
    void updateCustomer(int accountId, String address) throws CustomerException;
    
    String deleteAccount(int accountId) throws CustomerException;
    
    Customer viewCustomer(String accountNumber) throws CustomerException;
    
    Customer viewAllCustomer() throws CustomerException;
}
