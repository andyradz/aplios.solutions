package com.codigo.aplios.sdk.core.command;

//https://www.future-processing.pl/blog/cqrs-simple-architecture/ AXOn
//https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/
public interface ICommand {
	
	boolean isCompleted();
	
	void execute();
}
