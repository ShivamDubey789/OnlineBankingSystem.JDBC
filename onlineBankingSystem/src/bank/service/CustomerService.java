package bank.service;


import java.util.List;

import bank.exception.CustomerException;
import bank.model.Customer;
import bank.model.Transaction;

public interface CustomerService {
    Customer loginCustomer(String username, String password, int accountNumber) throws CustomerException;

    int viewBalance(int accountNumber) throws CustomerException;

    void deposit(int accountNumber, int amount) throws CustomerException;

    void withdraw(int accountNumber, int amount) throws CustomerException;

    void transfer(int accountNumber, int amount, int toAccountNumber) throws CustomerException;

    List<Transaction> viewTransactionHistory(int accountNumber) throws CustomerException;
}
