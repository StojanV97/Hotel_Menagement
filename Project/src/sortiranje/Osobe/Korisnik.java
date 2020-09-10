package Osobe;

public class Korisnik extends Osoba{

	protected String korisnickoIme;
	protected String lozinka;
	protected boolean aktivnost;
	
	
	public Korisnik() {
		
	}
	
	
	public Korisnik(String ime,String prezime,String brLK,String korisnickoIme, String lozinka, boolean aktivnost) {
		super(ime,prezime,brLK);
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.aktivnost = aktivnost;
	}
	
	public Korisnik(String linija) {
		
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public boolean getAktivnost() {
		return aktivnost;
	}
	public void setAktivnost(boolean aktivnost) {
		this.aktivnost = aktivnost;
	}
	
}
