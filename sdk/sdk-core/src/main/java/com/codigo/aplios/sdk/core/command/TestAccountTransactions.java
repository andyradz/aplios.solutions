package com.codigo.aplios.sdk.core.command;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class TestAccountTransactions {

	@Test
	public void Test_AllTransactionsSuccessful() {

		final AccountTransactionManager transactionManager = new AccountTransactionManager();

		final Account suesAccount = new Account("Sue Smith", .0);

		final DepositeTransactionCommand deposit = new DepositeTransactionCommand(suesAccount, 100.);
		transactionManager.addTransaction(deposit);

		// Command has been added to the queue, but not executed.
		assertThat(true, is(transactionManager.hasPendingTransactions()));
		assertThat(.0, is(suesAccount.getBalance()));

		// This executes the commands.
		transactionManager.processPendingTransactions();

		assertThat(false, is(transactionManager.hasPendingTransactions()));
		assertThat(100., is(suesAccount.getBalance()));

		// Add a withdrawal, apply it, and verify the balance changed.
		// final Withdraw withdrawal = new Withdraw(suesAccount, 50);
		// transactionManager.AddTransaction(withdrawal);

		// transactionManager.ProcessPendingTransactions();

		// Assert.IsFalse(transactionManager.HasPendingTransactions);
		/// Assert.AreEqual(50, suesAccount.Balance);
	}

//	        public void Test_OverdraftRemainsInPendingTransactions()
//	        {
//	            TransactionManager transactionManager = new TransactionManager();
//
//	            // Create an account with a balance of 75
//	            Account bobsAccount = new Account("Bob Jones", 75);
//
//	            // The first command is a withdrawal that is larger than the account's balance.
//	            // It will not be executed, because of the check in Withdraw.Execute.
//	            // The deposit will be successful.
//	            transactionManager.AddTransaction(new Withdraw(bobsAccount, 100));
//	            transactionManager.AddTransaction(new Deposit(bobsAccount, 75));
//
//	            transactionManager.ProcessPendingTransactions();
//
//	            // The withdrawal of 100 was not completed,
//	            // because there was not enough money in the account.
//	            // So, it is still pending.
//	            Assert.IsTrue(transactionManager.HasPendingTransactions);
//	            Assert.AreEqual(150, bobsAccount.Balance);
//
//	            // The pending transactions (the withdrawal of 100), should execute now.
//	            transactionManager.ProcessPendingTransactions();
//
//	            Assert.IsFalse(transactionManager.HasPendingTransactions);
//	            Assert.AreEqual(50, bobsAccount.Balance);
//	        }
//
//	        public void Test_Transfer()
//	        {
//	            TransactionManager transactionManager = new TransactionManager();
//
//	            Account checking = new Account("Mike Brown", 1000);
//	            Account savings = new Account("Mike Brown", 100);
//
//	            transactionManager.AddTransaction(new Transfer(checking, savings, 750));
//
//	            transactionManager.ProcessPendingTransactions();
//
//	            Assert.AreEqual(250, checking.Balance);
//	            Assert.AreEqual(850, savings.Balance);
//	        }
//	    }
}
