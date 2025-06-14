package com.jpmc.dao;

import com.jpmc.bean.Customer;
import com.jpmc.bean.Login;
import com.jpmc.bean.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

@Repository("dao")
public class DaoClass {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public boolean customerLogin(Login login) throws SQLException {
        //Write a Query
        String query = "select * from login where AccountNumber=?";

        Login login1 = jdbcTemplate.queryForObject(query, (ResultSet rs, int rowNum)->(
           new Login(rs.getString("AccountNumber"),rs.getString("Pin"),rs.getDouble("Balance"))
        ),login.getAccountNumber());

        if(login1.getAccountNumber().equals(login.getAccountNumber())) return true;

        return false;

    }


    public boolean createAccount(Customer customer) throws SQLException {
        //Write a Query
        String query = "insert into customer(AccountNumber,CustomerName,Branch,Balance) values(?,?,?,?)";

        if (isLowBalance(customer.getBalance())) {
            System.out.println("Minimum Balance should be Rs. 1000 to Create a Account..");
            return false;
        }

        if(jdbcTemplate.update(query,customer.getAccountNumber(),customer.getCustomerName(),customer.getBranch(),customer.getBalance())>0){
            System.out.println("Customer Account Created Successfully..");
            generateLoginCredentials(customer);
            return true;
        }

        return false;

    }

    public void generateLoginCredentials(Customer customer) throws SQLException {
        //Write a Query
        String query = "insert into login(AccountNumber,Pin,Balance) values(?,?,?)";

        //generating login pin - 6 digits
        Random random = new Random();
        String pin = String.valueOf(100000 + random.nextInt(900000)); // Range: 100000 to 999999


        if(jdbcTemplate.update(query,customer.getAccountNumber(),pin,customer.getBalance())>0){
            System.out.println("Login Credentials Generated Successfully..");
            System.out.println("Your Username[AccountNumber]: " + customer.getAccountNumber());
            System.out.println("Your Pin: " + pin);
        }
        else{
            System.out.println("Process Failed..");
        }

    }

    public void deposit(Customer customer, Double amt) throws SQLException {
        //Write a Query
        String query1 = "select Balance from customer where AccountNumber=?";
        String query2 = "update customer set Balance=? where AccountNumber=?";


        Double balance = jdbcTemplate.queryForObject(query1,Double.class,customer.getAccountNumber());
        if(balance<=0){
            System.out.println("Process Failed..");
            return;
        }


        if(jdbcTemplate.update(query2,balance+amt,customer.getAccountNumber())>0){
            updateTransactions(customer, customer, "deposit", amt);
            System.out.println(amt + " Amount Deposit and DataBase Updated..");
        }
        else{
            System.out.println("Process Failed..");
            return;
        }


    }

    public void withdraw(Customer customer, Double amt) throws SQLException {
        //Write a Query
        String query1 = "select Balance from customer where AccountNumber=?";
        String query2 = "update customer set Balance=? where AccountNumber=?";

        Double balance = jdbcTemplate.queryForObject(query1,Double.class,customer.getAccountNumber());
        if(balance>0){
            if(isLowBalance(balance-amt)){
                System.out.println("Process Failed..");
                System.out.println("Minimum Balance should be Rs. 1000 after the Withdraw..");
                return;
            }
        }
        else{
            System.out.println("Process Failed..");
            return;
        }


        if(jdbcTemplate.update(query2,balance-amt,customer.getAccountNumber())>0){
            updateTransactions(customer, customer, "withdraw", amt);
            System.out.println(amt + " Amount Withdraw and DataBase Updated..");
        }
        else{
            System.out.println("Process Failed..");
            return;
        }

    }

    public void fundTransfer(Customer customer1, Customer customer2, Double amt) throws SQLException {
        //Write a Query
        String query1 = "select Balance from customer where AccountNumber=?";

        Double balance1 = jdbcTemplate.queryForObject(query1,Double.class,customer1.getAccountNumber());
        if(balance1>0){
            if (isLowBalance(balance1 - amt)) {
                System.out.println("Process Failed..");
                System.out.println("Minimum Balance should be Rs. 1000 after the Amount Debited..");
                return;
            }
        }
        else{
            System.out.println("Process Failed..");
            return;
        }

//        withdraw(connection,customer1,amt); //update
        //Updating Debited of customer1
        //Write a Query
        String query11 = "update customer set Balance=? where AccountNumber=?";

        if(jdbcTemplate.update(query11,balance1-amt,customer2.getAccountNumber())>0){
            System.out.println(amt + " Amount Debited and DataBase Updated..");
        }
        else{
            System.out.println("Process Failed..");
            return;
        }


        //Write a Query
        String query2 = "select Balance from customer where AccountNumber=?";


        Double balance2 = jdbcTemplate.queryForObject(query2,Double.class,customer2.getAccountNumber());
        if(balance2<=0){
            System.out.println("Process Failed..");
            return;
        }


//        deposit(connection,customer2,amt); //update
        //Updating Credited of customer2
        //Write a Query
        String query22 = "update customer set Balance=? where AccountNumber=?";

        if(jdbcTemplate.update(query22,balance2+amt,customer2.getAccountNumber())>0){
            System.out.println(amt + " Amount Credited and DataBase Updated..");
        }
        else {
            System.out.println("Process Failed..");
            return;
        }


        updateTransactions(customer1, customer2, "transfer", amt);
        System.out.println("Fund Transfer Successfully and DataBase Updated..");
    }

    public void updateTransactions(Customer customer1, Customer customer2, String transactionsType, Double amt) throws SQLException {
        //Write a Query
        String query = "insert into transactions(AccountNumber,ToAccountNumber,TransactionsType,Amount) values(?,?,?,?)";

        if(jdbcTemplate.update(query,customer1.getAccountNumber(),customer2.getAccountNumber(),transactionsType,amt)>0){
            System.out.println("Transactions Table Updated Successfully..");
        }
        else {
            System.out.println("Process Failed..");
        }

    }

    public void printTransactions(Customer customer) throws SQLException {
        //Write a Query
        String query = "select * from transactions where AccountNumber=?";


        List<Transactions> allTransactions = jdbcTemplate.query(query, (ResultSet rs, int rowNum)-> (
                new Transactions(rs.getString("AccountNumber"), rs.getDouble("Amount"), rs.getString("ToAccountNumber"), rs.getString("TransactionsType"), rs.getTimestamp("TransactionsDate")))
        ,customer.getAccountNumber());

        System.out.println(allTransactions);

    }

    public boolean isLowBalance(double balance) {
        if (balance < 1000) return true;
        return false;
    }

}
