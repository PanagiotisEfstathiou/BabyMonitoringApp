package com.edu.babymonitoringapp.dto;

import com.edu.babymonitoringapp.model.Account;
import com.edu.babymonitoringapp.model.Role;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String username;
    private String password;
    private String role;

    public AccountDto(Account account) {
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.role = account.getRole().toString();
    }

    public static List<AccountDto> toListAccountDto(List<Account> accounts) {
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account : accounts) {
            accountDtos.add(new AccountDto(account));
        }
        return accountDtos;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setUsername(this.username);
        account.setPassword(this.password);
        account.setRole(Role.valueOf(this.role));
        return account;
    }
}
