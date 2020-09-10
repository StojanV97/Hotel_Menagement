package Osobe;

import java.util.ArrayList;
import klase.StavkaCenovnika;
import klase.TipSobe;

public class Menadzer extends Korisnik {

	ArrayList<StavkaCenovnika> dodateStavke;

	public Menadzer() {

	}

	public Menadzer(String ime, String prezime, String brLK, String kIme, String lozinka, boolean aktivnost,
			ArrayList<StavkaCenovnika> dodateStavke) {
		super(ime, prezime, brLK, kIme, lozinka, aktivnost);
		this.dodateStavke = dodateStavke;
	}

	public Menadzer(String line) {
		String[] lineP = (line.trim()).split("\\|");
		super.setIme(lineP[1].trim());
		super.setPrezime(lineP[2].trim());
		super.setBrLicneKarte(lineP[3].trim());
		super.setKorisnickoIme(lineP[4].trim());
		super.setLozinka(lineP[5].trim());
		super.setAktivnost(Boolean.valueOf(lineP[6].trim()));
		if (lineP[7].trim().equalsIgnoreCase("null")) {
			this.setDodateStavke(null);
		} else {
			this.dodateStavke = new ArrayList<StavkaCenovnika>();
			String stavke = lineP[7].trim();
			String[] listaDodatihStavki = stavke.split("\\;");
			for (String s : listaDodatihStavki) {
				String[] stavka = (s.trim()).split("\\#");
				StavkaCenovnika sc = new StavkaCenovnika();
				sc.setDnevniBoravak(Double.valueOf(stavka[0].trim()));
				sc.setNocenje(Double.valueOf(stavka[1].trim()));
				sc.setVikendPoskupljenje(Double.valueOf(stavka[2].trim()));
				TipSobe tp = new TipSobe();
				tp.setNazivSobe(stavka[3].trim());
				tp.setBrojKreveta(Integer.valueOf(stavka[4].trim()));
				sc.setTipSobe(tp);
				sc.setAktivnost(Boolean.valueOf(stavka[5].trim()));
				this.dodateStavke.add(sc);
			}

		}
	}

	@Override
	public String toString() {
		/*String dodateStavke = null;
		if (this.dodateStavke != null) {
			String[] string = new String[this.dodateStavke.size()];
			int a = 0;
			for (StavkaCenovnika sc : this.dodateStavke) {
				String linija = String.format("%s,%s,%s,%s", sc.getDnevniBoravak(), sc.getNocenje(),
						sc.getVikendPoskupljenje(), sc.getTipSobe().getNazivSobe());
				string[a] = linija;
				a++;
			}
			dodateStavke = String.join(";", string);
		} else {
			dodateStavke  = "/";
		}*/
		return String.format("%-15s| %-15s | %-15s  | %s ", this.ime, this.prezime, this.brLicneKarte,this.korisnickoIme);
	}

	public ArrayList<StavkaCenovnika> getDodateStavke() {
		return dodateStavke;
	}

	public void setDodateStavke(ArrayList<StavkaCenovnika> dodateStavke) {
		this.dodateStavke = dodateStavke;
	}

}
