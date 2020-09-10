package Osobe;

public class Osoba {

	String ime;
	String prezime;
	String brLicneKarte;

	public Osoba() {
		
	}
	
	
	
	public Osoba(String ime, String prezime, String brLicneKarte) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.brLicneKarte = brLicneKarte;
	}


	public Osoba(String line) {
		String[] lineP = (line.trim()).split("\\#");
		this.ime = lineP[0].trim();
		this.prezime = lineP[1].trim();
		this.brLicneKarte = lineP[2].trim();
	}

	@Override
	public String toString() {
		return "Osoba [ime=" + ime + ", prezime=" + prezime + ", brLicneKarte=" + brLicneKarte + "]";
	}



	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrLicneKarte() {
		return brLicneKarte;
	}
	public void setBrLicneKarte(String brLicneKarte) {
		this.brLicneKarte = brLicneKarte;
	}
	
	
}
