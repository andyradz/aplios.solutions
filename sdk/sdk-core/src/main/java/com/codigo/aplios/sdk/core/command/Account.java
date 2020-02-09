package com.codigo.aplios.sdk.core.command;

public class Account {

	private final String owner;
	private double balance;

	public Account(final String owner, final double balance) {

		super();
		this.owner = owner;
		this.balance = balance;
	}

	public double getBalance() {

		return this.balance;
	}

	public void setBalance(final double balance) {

		this.balance = balance;
	}

	public String getOwner() {

		return this.owner;
	}
}
