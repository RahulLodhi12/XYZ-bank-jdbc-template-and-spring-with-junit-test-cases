package com.jpmc.dao;

import com.jpmc.bean.Customer;
import com.jpmc.bean.Login;

public class DummyDao{ //For JUnit Testing

    public boolean customerLogin_Test(Login login) {
        if("174877832905923273".equals(login.getAccountNumber()) && "688526".equals(login.getPin())) return true;
        return false;
    }


    public boolean createAccount_Test(Customer customer){
        if("174877791759875275".equals(customer.getAccountNumber()) && "rekha singh".equals(customer.getCustomerName()) && "jodhpur".equals(customer.getBranch())) return true;
        return false;
    }


    public boolean deposit_Test(Customer customer, Double amt) {
        if("174877832905923273".equals(customer.getAccountNumber()) && amt>100) return true;
        return false;
    }


    public boolean withdraw_Test(Customer customer, Double amt) {
        if("174877832905923273".equals(customer.getAccountNumber()) && customer.getBalance()-amt>1000) return true;
        return false;
    }


    public boolean fundTransfer_Test(Customer customer1, Customer customer2, Double amt) {
        if("174877832905923273".equals(customer1.getAccountNumber()) && customer1.getBalance()-amt>1000){
            if("174880400724795934".equals(customer2.getAccountNumber())) return true;
        }
        return false;
    }


    public boolean isLowBalance_Test(double balance) {
        if(balance<1000) return true;
        return false;
    }
}
