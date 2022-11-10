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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SpringBootTest(classes = AccountServiceImpl.class)
public class AccountServiceImplTest {

    @Autowired
    private AccountServiceImpl accountService;


    @Test
    public void transferTest() {

        try {
            Constructor<AccountKey> constr = AccountKey.class.getDeclaredConstructor(long.class);
            constr.setAccessible(true);
            Account src = new Account(constr.newInstance(1L), "Лолец", "Лольцов", 1000.);
            Account dst = new Account(constr.newInstance(2L), "Бублик", "Бубликов", 100.);

            accountService.transfer(src, dst, 200.);
            System.out.println("\n".repeat(2));
            accountService.transfer(src, dst, 200.);


            Assertions.assertEquals(src.getBalance(), 600);
            Assertions.assertEquals(dst.getBalance(), 500);

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void transferTest1() {
        try {
            Constructor<AccountKey> constr = AccountKey.class.getDeclaredConstructor(long.class);
            constr.setAccessible(true);
            Account src = new Account(constr.newInstance(1L), "Лолец", "Лольцов", 1000.);
            Account dst = new Account(constr.newInstance(2L), "Бублик", "Бубликов", 100.);

            Assertions.assertThrows(MoneyAmountException.class, () -> accountService.transfer(src, dst, 2000.));

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferTest2() {
        try {
            Constructor<AccountKey> constr = AccountKey.class.getDeclaredConstructor(long.class);
            constr.setAccessible(true);
            Account src = new Account(constr.newInstance(1L), "Лолец", "Лольцов", 1000.);
            Account dst = new Account(constr.newInstance(2L), "Бублик", "Бубликов", 100.);

            Assertions.assertThrows(MoneyAmountException.class, () -> accountService.transfer(src, dst, -200));

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferTest3() {

        Account src = null;

        Account dst = null;

        Assertions.assertThrows(DataValidationException.class, () -> accountService.transfer(src, dst, 10));


    }

    @Test
    public void transferTest4(){

        try {
            Constructor<AccountKey> constr = AccountKey.class.getDeclaredConstructor(long.class);
            constr.setAccessible(true);
            Account src = new Account(constr.newInstance(1L), "Лолец", "Лольцов", 1000.);
            Account dst = null;

            Assertions.assertThrows(DataValidationException.class, () -> accountService.transfer(src, dst, 10));

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }



}
