package com.codigo.aplios.sdk.core.hamcrest;

@FunctionalInterface
public interface IThrowingRunnable<E extends Throwable> {

	void run() throws E;
}