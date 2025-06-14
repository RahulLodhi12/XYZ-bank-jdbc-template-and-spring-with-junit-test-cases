package com.jpmc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transactions {
    private String AccountNumber;
    private Double Amount;
    private String ToAccountNumber;
    private String TransactionsType;
    private Timestamp TransactionsDate;


}
