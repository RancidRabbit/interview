package com.devexperts.service;

import com.devexperts.account.Account;
import com.devexperts.account.AccountKey;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is going to emulate work of bank's system.
 * First, we withdraw the money from the sender through the {@link #withdrawMoney()}
 * method and transfer it to bankVault, and then our Thread receiver
 * must wait for the money to be transferred from the bank and
 * end up in the {@link #bankVault} and finally we transfer required amount
 * of money from bank to receiver {@link #bankTransfer()}
 * Note that the logic of the methods in the {@link com.devexperts.service.Exchange}
 * is not suited for loop cycles due to business logic.
 */

@Getter
@Setter
@Slf4j
public class Exchange {


    private double amount;

    private double bankVault;

    private Account sender;

    private Account receiver;

    public synchronized void withdrawMoney() {

        try {
            while (amount == 0) {
                wait();
            }
            sender.setBalance(sender.getBalance() - amount);
            log.info("Money withdraw from sender ");
            Thread.sleep(500);
            log.info(sender.getFirstName() + " balance: " + sender.getBalance());
            bankVault = amount;
            amount = 0;
            log.info("bank vault: " + bankVault);
            notify();
        } catch (InterruptedException e) {
            e.getMessage();
        }

    }


    public synchronized void bankTransfer() {

        try {
            while (bankVault == 0) {
                wait();
            }
            log.info("Money transfer to receiver");
            receiver.setBalance(receiver.getBalance() + bankVault);
            Thread.sleep(500);
            log.info(receiver.getFirstName() + " balance: " + receiver.getBalance());

        } catch (InterruptedException e) {
            e.getMessage();
        }

    }


}
