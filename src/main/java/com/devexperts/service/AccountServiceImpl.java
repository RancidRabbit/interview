package com.devexperts.service;

import com.devexperts.account.Account;
import com.devexperts.account.AccountKey;
import com.devexperts.exceptions.DataValidationException;
import com.devexperts.exceptions.MoneyAmountException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {


    private final Map<Long, Account> acc = new HashMap<>();

    @Override
    public void clear() {
        acc.clear();
    }

    @Override
    public void createAccount(Account account) throws IllegalArgumentException {

        if(acc.containsKey(account.getAccountKey().getAccountId())) throw new IllegalArgumentException();

        acc.put(account.getAccountKey().getAccountId(), account);
    }


    /* You should have used HashMap to store Accounts because if you have many Accounts your complexity of getAccount method
     * will be O(1) instead of O(n) while you're traversing your Array list to match exact value(Account) */
    @Override
    public Account getAccount(long id) {
        return acc.get(id);
    }


    @Override
    @Transactional
    /* Foremost let's check that all accounts are exist and we have enough money on balance to transfer */
    public void transfer(Account source, Account target, double amount) throws DataValidationException, MoneyAmountException {
        if (source == null | target == null)
            throw new DataValidationException("Account doesn't exist!");
        if (source.getBalance() < amount | amount <= 0.)
            throw new MoneyAmountException("Not enough money to transfer!");

        source.setBalance(source.getBalance() - amount);
        target.setBalance(target.getBalance() + amount);

    }


}
