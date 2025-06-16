package com.jpmc.service;

import com.jpmc.bean.Customer;
import com.jpmc.bean.Login;
import com.jpmc.dao.DummyDao;

public class DummyService {

    DummyDao dummyDao = new DummyDao();

    public boolean customerLogin_Test(Login login) {
        return dummyDao.customerLogin_Test(login);
    }


    public boolean createAccount_Test(Customer customer){
        return dummyDao.createAccount_Test(customer);
    }


    public boolean deposit_Test(Customer customer, Double amt) {
        return dummyDao.deposit_Test(customer,amt);
    }


    public boolean withdraw_Test(Customer customer, Double amt) {
        return dummyDao.withdraw_Test(customer,amt);
    }


    public boolean fundTransfer_Test(Customer customer1, Customer customer2, Double amt) {
        return dummyDao.fundTransfer_Test(customer1,customer2,amt);
    }


    public boolean isLowBalance_Test(double balance) {
        return dummyDao.isLowBalance_Test(balance);
    }
}
