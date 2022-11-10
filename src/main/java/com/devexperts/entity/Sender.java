package com.devexperts.entity;

import com.devexperts.service.Exchange;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sender implements Runnable{


    private Exchange exchange;

    public Sender(Exchange exchange) {
        this.exchange = exchange;
    }


    @Override
    public void run() {

            exchange.withdrawMoney();


    }
}
