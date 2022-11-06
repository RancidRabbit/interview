package com.devexperts.service;


import com.devexperts.account.Account;
import com.devexperts.account.AccountKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AccountServiceImpl.class)
public class AccountServiceImplTest {

    @Autowired
    private AccountServiceImpl accountService;


    @Test
    public void getAccountTest() {

        Account account = new Account(new AccountKey(1L), "Pete", "Johhson", 1000.);

        accountService.createAccount(account);


        Assertions.assertEquals(account, accountService.getAccount(1L));

    }

    @Test
    public void getAccountTest1(){

        Account account = new Account(new AccountKey(1L), "Pete", "Johhson", 1000.);

        accountService.createAccount(account);

        Assertions.assertNull(accountService.getAccount(2L));
    }

}
