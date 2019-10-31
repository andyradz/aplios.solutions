package com.codigo.aplios.sdk.core.command;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AccountTransactionManager {
	
	private final List<ICommand> transactions;
	
	private final Predicate<ICommand> anyNotCompleted = transaction -> !transaction.isCompleted();
	
	public AccountTransactionManager() {
		
		this.transactions = new ArrayList<>();
	}
	
	public void addTransaction(final ICommand transaction) {
		
		this.transactions.add(transaction);
	}
	
	public void processPendingTransactions() {
		
		this.transactions.stream()
			.filter(anyNotCompleted)
			.forEach(ICommand::execute);
	}
	
	public boolean hasPendingTransactions() {
		
		return transactions.stream()
			.anyMatch(anyNotCompleted);
	}
}
