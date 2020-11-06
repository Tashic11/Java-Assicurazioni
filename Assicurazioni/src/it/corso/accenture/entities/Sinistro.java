package it.corso.accenture.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Sinistro {

	private String codS;
	private String localita;
	private Calendar data;

	public Sinistro() {
		super();
	}

	public Sinistro(String codS, String localita, Calendar data) {
		super();
		this.codS = codS;
		this.localita = localita;
		this.data = data;
	}

	public String getCodS() {
		return codS;
	}

	public void setCodS(String codS) {
		this.codS = codS;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Sinistro codS : " + codS + ", localita : " + localita + ", data : " + dataSemplice();
	}

	@Override
	public int hashCode() {
		return Objects.hash(codS.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Sinistro))
			return false;
		Sinistro other = (Sinistro) obj;
		return codS.equalsIgnoreCase(other.codS);
	}
	
	public String dataSemplice() {
		return new SimpleDateFormat("dd/MM/yyyy").format(data.getTime());
	}

}
