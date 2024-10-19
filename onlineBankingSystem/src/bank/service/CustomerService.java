package bank.service;


import bank.exception.CustomerException;
import bank.model.Customer;

public interface CustomerService {
    Customer loginCustomer(String username, String password, int accountNumber) throws CustomerException;

    int viewBalance(int accountNumber) throws CustomerException;

    void deposit(int accountNumber, int amount) throws CustomerException;

    void withdraw(int accountNumber, int amount) throws CustomerException;

    void transfer(int accountNumber, int amount, int toAccountNumber) throws CustomerException;
}
