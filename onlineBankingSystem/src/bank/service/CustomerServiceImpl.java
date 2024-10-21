package bank.service;

import java.util.List;

import bank.dao.CustomerDao;
import bank.dao.CustomerDaoImpl;
import bank.exception.CustomerException;
import bank.model.Customer;
import bank.model.Transaction;
import bank.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public Customer loginCustomer(String username, String password, int accountNumber) throws CustomerException {
        return customerDao.LoginCustomer(username, password, accountNumber);
    }

    @Override
    public int viewBalance(int accountNumber) throws CustomerException {
        return customerDao.viewBalance(accountNumber);
    }

    @Override
    public void deposit(int accountNumber, int amount) throws CustomerException {
        customerDao.Deposit(accountNumber, amount);
        Transaction transaction = new Transaction(0, accountNumber, "deposit", amount, null, null); // targetAccount is null
        customerDao.logTransaction(transaction);
    }

    @Override
    public void withdraw(int accountNumber, int amount) throws CustomerException {
        customerDao.Withdraw(accountNumber, amount);
        Transaction transaction = new Transaction(0, accountNumber, "withdraw", amount, null, null); // targetAccount is null
        customerDao.logTransaction(transaction);
    }

    @Override
    public void transfer(int accountNumber, int amount, int toAccountNumber) throws CustomerException {
        customerDao.Transfer(accountNumber, amount, toAccountNumber);
        Transaction transaction = new Transaction(0, accountNumber, "transfer", amount, toAccountNumber, null);
        customerDao.logTransaction(transaction);
    }

    @Override
    public List<Transaction> viewTransactionHistory(int accountNumber) throws CustomerException {
        return customerDao.getTransactionHistory(accountNumber);
    }
}