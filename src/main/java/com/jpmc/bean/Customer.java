package com.jpmc.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String AccountNumber;
    private String CustomerName;
    private String Branch;
    private Double Balance;

    public Customer(String accNo) {
        this.AccountNumber = accNo;
    }

    public Customer(String accNo1, String accNo2) {
    }
}

/*
Q. Why we can't use @Component or @Bean in "Bean" Package, for Classes those represent the "table" in our DB.

Ans.
| Annotation   | Managed By    | Purpose                                 | Example Use              |
| ------------ | --------------| --------------------------------------- | ------------------------ |
| `@Entity`    | JPA/Hibernate | Maps class to **DB table**              | `Customer`, `User`, etc. |
| `@Component` | Spring IoC    | Marks a **service/helper** class        | `CustomerService`, etc.  |
| `@Bean`      | Spring IoC    | Declares a **manually configured bean** | Beans in config class    |

*/
