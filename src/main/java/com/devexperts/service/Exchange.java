package com.devexperts.service;

import com.devexperts.account.Account;

/* i think to operate with transfer we need to declare separate class where we can write 2 synchronized methods
* to getMoney and to sendMoney */
public class Exchange {

    private Account source;

    private Account target;

    private double amount = 100;

    private double bankStorage = 0;

    /* this is not exact realization */
    public synchronized void getMoney() {
        while (bankStorage == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           target.setBalance(target.getBalance() + bankStorage);
           notify();
        }
    }

    public synchronized void sendMoney() {
        while (amount == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            source.setBalance(source.getBalance() - amount);
            bankStorage = amount;
            amount = 0;
            notify();
        }
    }


}
