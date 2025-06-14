package com.jpmc.service;

import com.jpmc.bean.Customer;
import com.jpmc.bean.Login;
import com.jpmc.dao.DaoClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("service")
public class ServiceClass {

    @Autowired
    DaoClass daoClass;

    public boolean createAccount(Customer customer) throws SQLException {
        return daoClass.createAccount(customer);
    }

    public boolean customerLogin(Login login) throws SQLException {
        return daoClass.customerLogin(login);
    }

    public void deposit(Customer customer,Double amt) throws SQLException {
        daoClass.deposit(customer,amt);
    }

    public void withdraw(Customer customer,Double amt) throws SQLException {
        daoClass.withdraw(customer,amt);
    }

    public void fundTransfer(Customer customer, Customer customer1, Double amt) throws SQLException {
        daoClass.fundTransfer(customer,customer1,amt);
    }

    public void printTransactions(Customer customer) throws SQLException {
        daoClass.printTransactions(customer);
    }
}
