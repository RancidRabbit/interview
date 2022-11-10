package com.devexperts.entity;

import com.devexperts.account.Account;
import com.devexperts.account.AccountKey;
import com.devexperts.service.Exchange;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public class Receiver implements Runnable {

    private Exchange exchange;

    public Receiver(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public void run() {

        exchange.bankTransfer();


    }
}
