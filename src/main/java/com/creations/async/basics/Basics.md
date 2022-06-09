Java from the very basics

1. Classes
    - Every file in java will usually contain a class
    - In some cases, you may have interfaces, enums, aspects
    - Only one entity can be public in a file.
    - Variables & Methods.

2. Interfaces 
    - We only have methods / functions.
    - If there are variables, they will be immutable.
    - Interfaces are more similar to abstract classes.
    - We can't instantiate interfaces.
    - These are additional capabilities which can be added to the skeleton.
    
Any class can extend from only one other class but inherit multiple interfaces.

Access Modifiers
- private 
  - Only accessible from the defining class
- public
  - Accessible everywhere
- protected
  - Accessible from derived classes
- package-private (no modifier)
  - Accessible through-out the package.

3. Enums
   1. An enumeration of same type of values when a set of values is limited.

Unlike CPP, Java doesn't have destructors.
   As soon as the scope for the field, variable is over, the variable gets garbage collected.


```java
abstract class Super {

   abstract void superAbstract();
}

public interface First {
   int F = 0;
   void first();
}

class Second extends Super implements First {
   private final int key;

   private int val;

   Second() {
      this.key = 1;
   }
   public void setKey(int key) {
   }

   @Override
   void superAbstract() {

   }

   @Override
   public void first() {

   }
}

class FirstExtend extends Second {

}

interface Flyer {
   // pushing code to github from leetcode

   void customFly();

   boolean canFly();

   default void fly() {
      System.out.println("i'm flying high");
   }
}

interface Swimmer {}

interface Walker {}

enum Color {
   RED, BLUE, PURPLE, GREEN;
}

enum Country {
   INDIA(1, "NaMo"),
   ALGERIA(2),
   ARGENTINA(3, "Bolsenaro");

   int rank;
   String president;

   Country(int rank) {
      this.rank = rank;
   }

   Country(int rank, String president) {
      this.rank = rank;
      this.president = president;
   }

   public int getRank() {
      return rank;
   }

   public String getPresident() {
      return president;
   }
}

class Counter {
   String name;
}

class Animal {
   Color color;
   Country country;
   String country2;
}

class Bird extends Animal implements Flyer {
   int wings;

   @Override
   public void customFly() {
      Counter counter = new Counter();
   }

   @Override
   public boolean canFly() {
      return true;
   }

   void next() {

   }
}

class Dog extends Animal implements Walker {

}

class Eagle extends Bird {

}
class Duck extends Bird implements Walker {
   @Override
   public boolean canFly() {
      return false;
   }
}

class Crocodile extends Animal implements Walker, Swimmer {

}
```

Payment using multiple interfaces

```java

enum PaymentMode {
    DEBIT_CARD, CREDIT_CARD, UPI, NET_BANKING;
}

interface Pay {
    boolean pay(PaymentMode mode, double amount);
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
    public boolean pay(PaymentMode mode, double amount) {
        if (!modes.contains(mode)) {
            return false;
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
        System.out.println(account.pay(PaymentMode.CREDIT_CARD, 500));
        System.out.println(account.receive(PaymentMode.CREDIT_CARD, 500));
        System.out.println(account.pay(PaymentMode.UPI, 500));
        System.out.println(account.receive(PaymentMode.UPI, 500));
        System.out.println(account.pay(PaymentMode.DEBIT_CARD, 400));
        System.out.println(account.getBalance());
        PaymentReceivingSystem paymentReceivingSystem = new PaymentReceivingSystem(account);
        paymentReceivingSystem.test(99);
        System.out.println(account.getBalance());
    }
}

class PaymentReceivingSystem {
    Pay pay;

    PaymentReceivingSystem(Pay pay) {
        this.pay = pay;
    }

    void test(double amt) {
        pay.pay(PaymentMode.UPI, amt);
    }

}

```
