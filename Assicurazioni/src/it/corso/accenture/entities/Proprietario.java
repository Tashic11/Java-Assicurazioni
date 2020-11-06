package it.corso.accenture.entities;

import java.util.Objects;

public class Proprietario {

	private String codF;
	private String nome;
	private String residenza;

	public Proprietario() {
		super();
	}

	public Proprietario(String codF, String nome, String residenza) {
		super();
		this.codF = codF;
		this.nome = nome;
		this.residenza = residenza;
	}

	public String getCodF() {
		return codF;
	}

	public void setCodF(String codF) {
		this.codF = codF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	@Override
	public String toString() {
		return "Proprietario codF : " + codF + ", nome : " + nome + ", residenza : " + residenza;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codF.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Proprietario))
			return false;
		Proprietario other = (Proprietario) obj;
		return codF.equalsIgnoreCase(other.codF);
	}

}
