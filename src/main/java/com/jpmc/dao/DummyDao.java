package com.jpmc.dao;

import com.jpmc.bean.Customer;
import com.jpmc.bean.Login;

import java.sql.SQLException;

public class DummyDao{ //For JUnit Testing

    public boolean customerLogin(Login login) {
        if("174877832905923273".equals(login.getAccountNumber()) && "688526".equals(login.getPin())) return true;
        return false;
    }


    public boolean createAccount(Customer customer){
        if("174877791759875275".equals(customer.getAccountNumber()) && "rekha singh".equals(customer.getCustomerName()) && "jodhpur".equals(customer.getBranch())) return true;
        return false;
    }


    public boolean deposit(Customer customer, Double amt) {
        if("174877832905923273".equals(customer.getAccountNumber()) && amt>100) return true;
        return false;
    }


    public boolean withdraw(Customer customer, Double amt) {
        if("174877832905923273".equals(customer.getAccountNumber()) && customer.getBalance()-amt>1000) return true;
        return false;
    }


    public boolean fundTransfer(Customer customer1, Customer customer2, Double amt) {
        if("174877832905923273".equals(customer1.getAccountNumber()) && customer1.getBalance()-amt>1000){
            if("174880400724795934".equals(customer2.getAccountNumber())) return true;
        }
        return false;
    }


    public boolean isLowBalance(double balance) {
        if(balance<1000) return true;
        return false;
    }
}
