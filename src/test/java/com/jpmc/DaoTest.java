package com.jpmc;

import com.jpmc.bean.Customer;
import com.jpmc.bean.Login;
import com.jpmc.dao.DummyDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaoTest {
    DummyDao dummyDao = new DummyDao();

    @Test
    void customerLogin_Success() {
        assertTrue(dummyDao.customerLogin(new Login("174877832905923273","688526")));
    }

    @Test
    void customerLogin_Failure() {
        assertFalse(dummyDao.customerLogin(new Login("174877832905923299","688599")));
    }

    @Test
    void createAccount_Success(){
        assertTrue(dummyDao.createAccount(new Customer("174877791759875275","rekha singh","jodhpur")));
    }

    @Test
    void createAccount_Failure(){
        assertFalse(dummyDao.createAccount(new Customer("174877791759875299","rekha singh","jodhpur")));
    }

    @Test
    void desposit_Success(){
        assertTrue(dummyDao.deposit(new Customer("174877832905923273"),200.0));
    }

    @Test
    void desposit_Failure(){
        assertFalse(dummyDao.deposit(new Customer("174877832905923273"),50.0));
    }

    @Test
    void withdraw_Success(){
        assertTrue(dummyDao.withdraw(new Customer("174877832905923273",4500.00),1000.00));
    }

    @Test
    void withdraw_Failure(){
        assertFalse(dummyDao.withdraw(new Customer("174877832905923273",1500.00),1000.00));
    }

    @Test
    void fundTransfer_Success(){
        assertTrue(dummyDao.fundTransfer(new Customer("174877832905923273",4500.00), new Customer("174880400724795934"),2500.00));
    }

    @Test
    void fundTransfer_Failure(){
        assertFalse(dummyDao.fundTransfer(new Customer("174877832905923273",4500.00), new Customer("174880400724795934"),4000.00));
    }

    @Test
    void isLowBalance_True(){
        assertTrue(dummyDao.isLowBalance(500));
    }

    @Test
    void isLowBalance_False(){
        assertFalse(dummyDao.isLowBalance(1500));
    }
}
