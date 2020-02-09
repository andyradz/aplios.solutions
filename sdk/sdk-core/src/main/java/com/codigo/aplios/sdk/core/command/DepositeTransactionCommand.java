package com.codigo.aplios.sdk.core.command;

import java.util.Objects;

public class DepositeTransactionCommand implements ICommand {

	private final double amount;
	private final Account account;

	private boolean isCompleted;

	public DepositeTransactionCommand(final Account account, final double amount) {

		if (Objects.isNull(account))
			throw new NullPointerException("Obiekt konta [account] jest nieokreślony!");

		if (amount <= .0)
			throw new IllegalArgumentException("Wartość kwoty [amount] depozytu musi być większa od zera!");

		this.amount = amount;
		this.account = account;
		this.isCompleted = false;
	}

	@Override
	public boolean isCompleted() {

		return this.isCompleted;
	}

	@Override
	public void execute() {

		final var newBalance = this.account.getBalance() + this.amount;

		this.account.setBalance(newBalance);

		this.isCompleted = true;
	}
}
