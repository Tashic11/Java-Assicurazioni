package it.corso.accenture.entities;

import java.util.Objects;

public class Auto {

	private String targa;
	private String marca;
	private int cilindrata;
	private int potenza;
	private String codF;
	private String codAss;

	public Auto() {
		super();
	}

	public Auto(String targa, String marca, int cilindrata, int potenza, String codF, String codAss) {
		super();
		this.targa = targa;
		this.marca = marca;
		this.cilindrata = cilindrata;
		this.potenza = potenza;
		this.codF = codF;
		this.codAss = codAss;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}

	public int getPotenza() {
		return potenza;
	}

	public void setPotenza(int potenza) {
		this.potenza = potenza;
	}

	public String getCodF() {
		return codF;
	}

	public void setCodF(String codF) {
		this.codF = codF;
	}

	public String getCodAss() {
		return codAss;
	}

	public void setCodAss(String codAss) {
		this.codAss = codAss;
	}

	@Override
	public String toString() {
		return "Auto targa : " + targa + ", marca : " + marca + ", cilindrata : " + cilindrata + ", potenza : "
				+ potenza
				+ ", codF : " + codF + ", codAss : " + codAss;
	}

	@Override
	public int hashCode() {
		return Objects.hash(targa.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Auto))
			return false;
		Auto other = (Auto) obj;
		return targa.equalsIgnoreCase(other.targa);
	}

}
