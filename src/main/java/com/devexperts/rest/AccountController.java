package com.devexperts.rest;

import com.devexperts.exceptions.DataValidationException;
import com.devexperts.exceptions.MoneyAmountException;
import com.devexperts.exceptions.ResourceNotFoundException;
import com.devexperts.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class AccountController extends AbstractAccountController {

    private final AccountServiceImpl accountService;


    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestParam long sourceId, @RequestParam long targetId, @RequestParam double amount) {
        if (accountService.getAccount(sourceId) == null | accountService.getAccount(targetId) == null)
            throw new ResourceNotFoundException("Account doesnt exist!");
        if (accountService.getAccount(sourceId).getBalance() < amount)
            throw new MoneyAmountException("Insufficient account balance!");
        if (amount <= 0) throw new DataValidationException("Amount is invalid!");
        accountService.transfer(accountService.getAccount(sourceId), accountService.getAccount(targetId), amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
