package it.corso.accenture.utils;

public class Gruppo2<T, K> {

	private T oggetto1;
	private K oggetto2;

	public Gruppo2(T oggetto1, K oggetto2) {
		super();
		this.oggetto1 = oggetto1;
		this.oggetto2 = oggetto2;
	}

	public T getOggetto1() {
		return oggetto1;
	}

	public void setOggetto1(T oggetto1) {
		this.oggetto1 = oggetto1;
	}

	public K getOggetto2() {
		return oggetto2;
	}

	public void setOggetto2(K oggetto2) {
		this.oggetto2 = oggetto2;
	}

}
