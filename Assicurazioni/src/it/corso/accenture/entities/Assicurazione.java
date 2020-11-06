package it.corso.accenture.entities;

import java.util.Objects;

public class Assicurazione {

	private String codAss;
	private String nome;
	private String sede;

	public Assicurazione() {
		super();
	}

	public Assicurazione(String codAss, String nome, String sede) {
		super();
		this.codAss = codAss;
		this.nome = nome;
		this.sede = sede;
	}

	public String getCodAss() {
		return codAss;
	}

	public void setCodAss(String codAss) {
		this.codAss = codAss;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	@Override
	public String toString() {
		return "Assicurazioni codAss : " + codAss + ", nome : " + nome + ", sede : " + sede;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codAss.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Assicurazione))
			return false;
		Assicurazione other = (Assicurazione) obj;
		return codAss.equalsIgnoreCase(other.codAss);
	}

}
