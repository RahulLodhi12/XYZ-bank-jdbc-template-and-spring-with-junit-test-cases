package com.jpmc.service.test;

import com.jpmc.bean.Customer;
import com.jpmc.bean.Login;
import com.jpmc.dao.DaoClass;
import com.jpmc.service.ServiceClass;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ServiceTestMock {

    @Test
    void createAccountTest_Success() throws SQLException {
        //1. Get the Mock Object of "DaoClass"
        DaoClass daoMock = mock(DaoClass.class);

        //2. Stub the Method -> dummy (fake/mock) implementation -> provide isolation -> return true -> No Calling here
        when(daoMock.createAccount(new Customer("174877791759875275","rekha singh","jodhpur"))).thenReturn(true);
        /*
        🔄 When when() method hit, this will be the flow of code is:
        1. daoMock.createAccount(...) – intercepted by Mockito.
        2. Mockito does NOT execute the real method logic.
        3. thenReturn(true) tells Mockito:
            "Next time this exact method is called with the same arguments, return true."
        */

        //3. Inject the mock object into Service class -> create the real service object -> Not mock Object
        ServiceClass serviceClass = new ServiceClass(daoMock);
        //Need of Service Object -> Since Service Class contains the Business logic

        //4. Test the method -> gets the return "true" from the thenReturn(true), via when() method hit.
        assertTrue(serviceClass.createAccount(new Customer("174877791759875275","rekha singh","jodhpur")));

        //5. Verify the method was actually called
        verify(daoMock).createAccount(new Customer("174877791759875275","rekha singh","jodhpur"));
    }

    /* Flow Of Code:
| Step | Layer         | Action                                                                                    |
| ---- | ------------- | ------------------------------------------------------------------------------------------|
| 1    | Test          | Create a mock DAO                                                                         |
| 2    | Test          | Stub method to return `true`                                                              |
| 3    | Test          | Inject mock DAO into `ServiceClass`                                                       |
| 4    | Service → DAO | Call `serviceClass.createAccount()` which internally calls dummy(mock) DAO implementation |
| 5    | Test          | Verify if DAO method was called with correct input                                        |
    */

    @Test
    void createAccountTest_Failure() throws SQLException {
        //1. Get the Mock Object of "DaoClass"
        DaoClass daoClass = mock(DaoClass.class);

        //2. Stub the Method -> dummy (fake) implementation -> provide isolation
        when(daoClass.createAccount(new Customer("","rekha singh","jodhpur"))).thenReturn(false);
        //next time createAccount() method is called return "false" only when the arguments are same.

        //3. Inject the mock object into Service class -> create Mock Service Object
        ServiceClass serviceClass = new ServiceClass(daoClass);

        //4. Test the method
        assertFalse(serviceClass.createAccount(new Customer("","rekha singh","jodhpur")));

        //5. Verify the method was actually called
        verify(daoClass).createAccount(new Customer("","rekha singh","jodhpur"));
    }

    @Test
    void customerLoginTest_Success() throws SQLException {
        //1. Get the mock object of "DaoClass" class
        DaoClass daoClass = mock(DaoClass.class);

        //2. Stub the Method -> dummy (fake) implementation -> provide isolation
        when(daoClass.customerLogin(new Login("174877832905923273","688526"))).thenReturn(true);

        //3. Inject the mock object into Service class -> create Mock Service Object
        ServiceClass serviceClass = new ServiceClass(daoClass);

        //4. Test the method
        assertTrue(serviceClass.customerLogin(new Login("174877832905923273","688526")));

        //5. Verify the method was actually called
        verify(daoClass).customerLogin(new Login("174877832905923273","688526"));
    }

    @Test
    void customerLoginTest_Failure() throws SQLException {
        //1. Get the mock object of "DaoClass" class
        DaoClass daoClass = mock(DaoClass.class);

        //2. Stub the Method -> dummy (fake) implementation -> provide isolation
        when(daoClass.customerLogin(new Login("","688526"))).thenReturn(false);

        //3. Inject the mock object into Service class -> create Mock Service Object
        ServiceClass serviceClass = new ServiceClass(daoClass);

        //4. Test the method
        assertFalse(serviceClass.customerLogin(new Login("","688526")));

        //5. Verify the method was actually called
        verify(daoClass).customerLogin(new Login("","688526"));
    }
}
