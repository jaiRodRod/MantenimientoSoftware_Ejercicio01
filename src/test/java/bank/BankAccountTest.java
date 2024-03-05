package bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    @DisplayName("Se crea con un balance adecuado")
    public void BankAccount_ProperlyInitialized()
    {
        BankAccount b1 = new BankAccount(0);
        BankAccount b2 = new BankAccount(-100);
        BankAccount b3 = new BankAccount(100);

        assertEquals(b1.getBalance(), 0);
        assertEquals(b2.getBalance(), -100);
        assertEquals(b3.getBalance(), 100);
    }

    @Test
    @DisplayName("Cuenta con dinero saca cantidad pequeña")
    public void BankAccount_WithdrawLittle()
    {
        int moneyInAccount = 100;
        int moneyWithdrawn = 20;
        BankAccount b = new BankAccount(moneyInAccount);

        boolean done = b.withdraw(moneyWithdrawn);

        assertTrue(done);
        assertTrue(b.getBalance() >= 0);
        assertEquals(b.getBalance(), moneyInAccount - moneyWithdrawn);
    }

    @Test
    @DisplayName("Cuenta con dinero saca cantidad sobrepasada")
    public void BankAccount_WithdrawMuch()
    {
        int moneyInAccount = 100;
        int moneyWithdrawn = 200;
        BankAccount b = new BankAccount(moneyInAccount);

        boolean done = b.withdraw(moneyWithdrawn);

        assertFalse(done);
        assertTrue(b.getBalance() >= 0);
    }

    @Test
    @DisplayName("Cuenta sin dinero deposita cantidad")
    public void BankAccount_DepositInAccount()
    {
        int moneyInAccount = 0;
        int moneyDeposited = 20;
        BankAccount b = new BankAccount(moneyInAccount);

        b.deposit(moneyDeposited);

        assertTrue(b.getBalance() >= 0);
        assertEquals(b.getBalance(), moneyInAccount + moneyDeposited);
    }

    @Test
    @DisplayName("Cuenta deposita cantidad negativa")
    public void BankAccount_DepositNegativeInAccount()
    {
        int moneyInAccount = 0;
        int moneyDeposited = -20;
        BankAccount b = new BankAccount(moneyInAccount);

        assertThrows(IllegalArgumentException.class, () -> b.deposit(moneyDeposited));
    }

    @Test
    @DisplayName("Obtener balance de una cuenta")
    public void BankAccount_GetBalance()
    {
        int balance = 100;
        BankAccount b = new BankAccount(balance);

        assertEquals(b.getBalance(), balance);
    }

    @Test
    @DisplayName("Pago por mes para un prestamo correcto")
    public void BankAccount_PaymentCorrect()
    {
        int balance = 10000;
        double total_amount =10000;
        double interest = 5;
        int months = 12;

        BankAccount b = new BankAccount(balance);

        assertEquals(b.payment(total_amount, interest, months), 50000.000023, 6);
    }

    @Test
    @DisplayName("Pago pendiente para cierto mes futuro correcto")
    public void BankAccount_PendingMonthCorrect()
    {
        int balance = 10000;
        double total_amount =10000;
        double interest = 5;
        int months = 12;

        BankAccount b = new BankAccount(balance);

        assertEquals(b.pending(total_amount, interest, months, 2), 9999.999839, 6);
    }

    @Test
    @DisplayName("Pago pendiente para mes 0 correcto")
    public void BankAccount_PendingCorrect()
    {
        int balance = 10000;
        double total_amount =10000;
        double interest = 5;
        int months = 12;

        BankAccount b = new BankAccount(balance);

        assertEquals(b.pending(total_amount, interest, months, 0), total_amount);
    }

}

