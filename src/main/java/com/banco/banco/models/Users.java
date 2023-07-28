package com.banco.banco.models;

import jakarta.persistence.*;

@Entity
@Table(name ="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long balance;
    private String cpf;
    private String email;
    private String password;
    private boolean isShopkeeper;

    public Users(String name, Long balance, String cpf, String email, String password, boolean isShopkeeper) {
        this.name = name;
        this.balance = balance;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.isShopkeeper = isShopkeeper;
    }

    public Users(){}

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isShopkeeper() {
        return isShopkeeper;
    }

    public void setShopkeeper(boolean shopkeeper) {
        this.isShopkeeper = shopkeeper;
    }
}
