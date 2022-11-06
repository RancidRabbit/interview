package com.devexperts.service;


import com.devexperts.account.Account;
import com.devexperts.account.AccountKey;
import com.devexperts.exceptions.DataValidationException;
import com.devexperts.exceptions.MoneyAmountException;
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
    public void createAccountTest(){
        Account account = new Account(new AccountKey(5L), "Pete", "Johnson", 1000.);

        accountService.createAccount(account);

        Assertions.assertThrows(IllegalArgumentException.class, ()-> accountService.createAccount(account));

    }





    @Test
    public void getAccountTest() {

        Account account = new Account(new AccountKey(1L), "Pete", "Johnson", 1000.);

        accountService.createAccount(account);


        Assertions.assertEquals(account, accountService.getAccount(1L));

    }

    @Test
    public void getAccountTest1() {

        Account account = new Account(new AccountKey(3L), "Pete", "Johnson", 1000.);

        accountService.createAccount(account);

        Assertions.assertNull(accountService.getAccount(4L));
    }

    @Test
    public void transferTest() {

        Account account = new Account(new AccountKey(1L), "Pete", "Johnson", 1000.);
        Account account2 = new Account(new AccountKey(1L), "Jack", "McGee", 500.);


        accountService.transfer(account, account2, 200.);

        Assertions.assertEquals(800., account.getBalance());
        Assertions.assertEquals(700., account2.getBalance());
    }


    @Test
    public void transferTest1() {

        Account account = new Account(new AccountKey(1L), "Pete", "Johnson", 1000.);

        Account account2 = null;

        Assertions.assertThrows(DataValidationException.class, () -> accountService.transfer(account, account2, 200.));


    }

    @Test
    public void transferTest2() {

        Account account = new Account(new AccountKey(1L), "Grace", "Caldwell", 200.);
        Account account2 = new Account(new AccountKey(1L), "Maud", "Ramsey", 500.);


        Assertions.assertThrows(MoneyAmountException.class, () -> accountService.transfer(account, account2, 205));

    }

    @Test
    public void transferTest3() {

        Account account = new Account(new AccountKey(1L), "Grace", "Caldwell", 200.);
        Account account2 = new Account(new AccountKey(1L), "Maud", "Ramsey", 500.);

        accountService.transfer(account, account2, 200.);

        Assertions.assertEquals(0., account.getBalance());
        Assertions.assertEquals(700., account2.getBalance());

    }

    @Test
    public void transferTest4() {

        Account account = new Account(new AccountKey(1L), "Grace", "Caldwell", 200.);
        Account account2 = new Account(new AccountKey(1L), "Maud", "Ramsey", 500.);

        Assertions.assertThrows(MoneyAmountException.class, ()-> accountService.transfer(account, account2, -100.));
    }



}
