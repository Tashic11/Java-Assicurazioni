package it.corso.accenture.entities;

import java.util.Objects;

public class AutoCoinvolte {

	private String codS;
	private String targa;
	private int importoDelDanno;

	public AutoCoinvolte() {
		super();
	}

	public AutoCoinvolte(String codS, String targa, int importoDelDanno) {
		super();
		this.codS = codS;
		this.targa = targa;
		this.importoDelDanno = importoDelDanno;
	}

	public String getCodS() {
		return codS;
	}

	public void setCodS(String codS) {
		this.codS = codS;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public int getImportoDelDanno() {
		return importoDelDanno;
	}

	public void setImportoDelDanno(int importoDelDanno) {
		this.importoDelDanno = importoDelDanno;
	}

	@Override
	public String toString() {
		return "AutoCoinvolte codS : " + codS + ", targa : " + targa + ", importoDelDanno : " + importoDelDanno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codS.toLowerCase(), targa.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AutoCoinvolte))
			return false;
		AutoCoinvolte other = (AutoCoinvolte) obj;
		return codS.equalsIgnoreCase(other.codS) && targa.equalsIgnoreCase(other.targa);
	}

}
