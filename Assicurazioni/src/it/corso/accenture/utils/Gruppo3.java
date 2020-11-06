package it.corso.accenture.utils;

public class Gruppo3<T, K, N> {

	private T oggetto1;
	private K oggetto2;
	private N oggetto3;

	public Gruppo3(T oggetto1, K oggetto2, N oggetto3) {
		super();
		this.oggetto1 = oggetto1;
		this.oggetto2 = oggetto2;
		this.oggetto3 = oggetto3;
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

	public N getOggetto3() {
		return oggetto3;
	}

	public void setOggetto3(N oggetto3) {
		this.oggetto3 = oggetto3;
	}

}
