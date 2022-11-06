package com.devexperts.service;

import com.devexperts.account.Account;
import com.devexperts.account.AccountKey;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    /*private final List<Account> accounts = new ArrayList<>();*/

    private final Map<Long, Account> acc = new HashMap<>();

    @Override
    public void clear() {
       /* accounts.clear();*/
        acc.clear();
    }

    @Override
    public void createAccount(Account account) {
       /* accounts.add(account);*/
       acc.put(account.getAccountKey().getAccountId(), account);
    }

    /* You should have used HashMap to store Accounts because if you have many Accounts your complexity of getAccount method
     * will be O(1) instead of O(n) while you're traversing your Array list to match exact value(Account) */
    @Override
    public Account getAccount(long id) {
        return acc.get(id);
    }

    @Override
    public void transfer(Account source, Account target, double amount) {
        //do nothing for now
    }
}
