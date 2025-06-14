package com.jpmc;

import com.jpmc.ui.UIClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml"); //Informing to Spring IOC Container

        UIClass ui = context.getBean("ui", UIClass.class);

        ui.bankOperations();
    }
}