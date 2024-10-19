package bank.service;

import bank.dao.AccountantDao;
import bank.dao.AccountantDaoimpl;
import bank.exception.AccountantException;
import bank.exception.CustomerException;
import bank.model.Accountant;
import bank.model.Customer;
import bank.service.AccountantService;

public class AccountantServiceImpl implements AccountantService {
    private AccountantDao accountantDao = new AccountantDaoimpl();

    @Override
    public Accountant loginAccountant(String username, String password) throws AccountantException {
        return accountantDao.LoginAccountant(username, password);
    }

    @Override
    public int addCustomer(String name, String email, String password, String mobile, String address) throws CustomerException {
        return accountantDao.addCustomer(name, email, password, mobile, address);
    }

    @Override
    public void addAccount(int balance, int customerId) throws CustomerException {
        accountantDao.addAccount(balance, customerId);
    }

    @Override
    public void updateCustomer(int accountId, String address) throws CustomerException {
        accountantDao.updateCustomer(accountId, address);
    }

    @Override
    public String deleteAccount(int accountId) throws CustomerException {
        return accountantDao.deleteAccount(accountId);
    }


    @Override
    public Customer viewCustomer(String accountNumber) throws CustomerException {
        return accountantDao.viewCustomer(accountNumber);
    }

    @Override
    public Customer viewAllCustomer() throws CustomerException {
        return accountantDao.viewAllCustomer();
    }
}