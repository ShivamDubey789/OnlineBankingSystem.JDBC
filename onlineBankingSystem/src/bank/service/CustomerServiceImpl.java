package bank.service;

import bank.dao.CustomerDao;
import bank.dao.CustomerDaoImpl;
import bank.exception.CustomerException;
import bank.model.Customer;
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
    }

    @Override
    public void withdraw(int accountNumber, int amount) throws CustomerException {
        customerDao.Withdraw(accountNumber, amount);
    }

    @Override
    public void transfer(int accountNumber, int amount, int toAccountNumber) throws CustomerException {
        customerDao.Transfer(accountNumber, amount, toAccountNumber);
    }
}