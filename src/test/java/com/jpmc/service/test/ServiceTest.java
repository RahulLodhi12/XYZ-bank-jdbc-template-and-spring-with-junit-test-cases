package com.jpmc.service.test;

import com.jpmc.bean.Customer;
import com.jpmc.bean.Login;
import com.jpmc.service.DummyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//NOTE: We Test the Service Class, since Service Class contains the Business Logic.

//TDD - [Test Driven Development] -> Writing Test Case first then Write the Develop Method(means implement the Methods).
//Purpose of TDD - When we write Test Case first, then check the Test Case it will fail, then Write the implementation then few test case passes... so on so far.
public class ServiceTest {
    DummyService dummyService = new DummyService();

    @Test
    void customerLogin_Success() {
        assertTrue(dummyService.customerLogin_Test(new Login("174877832905923273","688526")));
    }

    @Test
    void customerLogin_Failure() {
        assertFalse(dummyService.customerLogin_Test(new Login("174877832905923299","688599")));
    }

    @Test
    void createAccount_Success(){
        assertTrue(dummyService.createAccount_Test(new Customer("174877791759875275","rekha singh","jodhpur")));
    }

    @Test
    void createAccount_Failure(){
        assertFalse(dummyService.createAccount_Test(new Customer("174877791759875299","rekha singh","jodhpur")));
    }

    @Test
    void desposit_Success(){
        assertTrue(dummyService.deposit_Test(new Customer("174877832905923273"),200.0));
    }

    @Test
    void desposit_Failure(){
        assertFalse(dummyService.deposit_Test(new Customer("174877832905923273"),50.0));
    }

    @Test
    void withdraw_Success(){
        assertTrue(dummyService.withdraw_Test(new Customer("174877832905923273",4500.00),1000.00));
    }

    @Test
    void withdraw_Failure(){
        assertFalse(dummyService.withdraw_Test(new Customer("174877832905923273",1500.00),1000.00));
    }

    @Test
    void fundTransfer_Success(){
        assertTrue(dummyService.fundTransfer_Test(new Customer("174877832905923273",4500.00), new Customer("174880400724795934"),2500.00));
    }

    @Test
    void fundTransfer_Failure(){
        assertFalse(dummyService.fundTransfer_Test(new Customer("174877832905923273",4500.00), new Customer("174880400724795934"),4000.00));
    }

    @Test
    void isLowBalance_True(){
        assertTrue(dummyService.isLowBalance_Test(500));
    }

    @Test
    void isLowBalance_False(){
        assertFalse(dummyService.isLowBalance_Test(1500));
    }
}
