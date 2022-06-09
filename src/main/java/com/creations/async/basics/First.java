package com.creations.async.basics;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

enum PaymentMode {
    DEBIT_CARD, CREDIT_CARD, UPI, NET_BANKING;
}

interface Pay {
    boolean pay(PaymentMode mode, double amount) throws Exception;
}

interface Receive {
    boolean receive(PaymentMode mode, double amount);
}

class Account implements Pay, Receive {

    double balance;
    List<PaymentMode> modes;

    public Account(Set<PaymentMode> modes) {
        this.balance = 0;
        this.modes = new ArrayList<>(modes);
    }

    @Override
    public boolean pay(PaymentMode mode, double amount) throws Exception {
        if (!modes.contains(mode)) {
            throw new Exception("Unauthorized action attempted");
        }
        if (amount < 0) {
            return false;
        }
        if (balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }

    @Override
    public boolean receive(PaymentMode mode, double amount) {
        if (!modes.contains(mode)) {
            return false;
        }
        if (amount < 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    public double getBalance() {
        return balance;
    }
}


class Test {
    public static void main(String[] args) {
        Account account = new Account(Set.of(PaymentMode.UPI, PaymentMode.DEBIT_CARD));
        System.out.println(account.getBalance());
        try {
            new FileInputStream("file.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(account.receive(PaymentMode.CREDIT_CARD, 500));
////        System.out.println(account.pay(PaymentMode.UPI, 500));
//        System.out.println(account.receive(PaymentMode.UPI, 500));
//        System.out.println(account.pay(PaymentMode.DEBIT_CARD, 400));
//        System.out.println(account.getBalance());
//        PaymentReceivingSystem paymentReceivingSystem = new PaymentReceivingSystem(account);
//        paymentReceivingSystem.test(99);
//        System.out.println(account.getBalance());
    }
}

class PaymentReceivingSystem {
    Pay pay;

    PaymentReceivingSystem(Pay pay) {
        this.pay = pay;
    }

    void test(double amt) {
//        pay.pay(PaymentMode.UPI, amt);
    }

}