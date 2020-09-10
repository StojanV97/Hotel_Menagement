package Osobe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import klase.Iznajmljivanje;
import klase.Racun;
import klase.Soba;
import klase.StavkaCenovnika;
import mein.Main;

public class Recepcioner extends Korisnik {

	ArrayList<Iznajmljivanje> otvorenaIznm;
	ArrayList<Racun> izdatiRacuni;

	public Recepcioner() {

	}

	public Recepcioner(String ime, String prezime, String brLK, String kIme, String lozinka, boolean aktivnost,
			ArrayList<Iznajmljivanje> otvorenaIznm, ArrayList<Racun> r) {
		super(ime, prezime, brLK, kIme, lozinka, aktivnost);
		this.otvorenaIznm = otvorenaIznm;
		this.izdatiRacuni = r;
	}

	public Recepcioner(String line) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH.mm");
		String[] lineP = (line.trim()).split("\\|");
		super.setIme(lineP[1].trim());
		super.setPrezime(lineP[2].trim());
		super.setBrLicneKarte(lineP[3].trim());
		super.setKorisnickoIme(lineP[4].trim());
		super.setLozinka(lineP[5].trim());
		super.setAktivnost(Boolean.valueOf(lineP[6].trim()));
		if ((lineP[7].trim()).equalsIgnoreCase("null")) {
			this.otvorenaIznm = null;
		} else {
			this.otvorenaIznm = new ArrayList<Iznajmljivanje>();
			String[] iznajmljivanje = (lineP[7].trim()).split("\\;");
			for (String s : iznajmljivanje) {
				String[] iznmPojedinacno = (s.trim()).split("\\#");
				Date datumPocetka = sdf.parse(iznmPojedinacno[0].trim());
				String brojSobe = iznmPojedinacno[1].trim();
				Iznajmljivanje iznm = null;
				for (Iznajmljivanje i : Main.listaIznajmljivanja) {
					if (datumPocetka.equals(i.getDatumPocetka()) && brojSobe.equalsIgnoreCase(i.getSoba().getBroj())) {
						iznm = i;
						this.otvorenaIznm.add(i);
	
						break;
					}
				}
			}
		}
		if ((lineP[8].trim()).equalsIgnoreCase("null")) {
			this.izdatiRacuni = null;
		} else {
			this.izdatiRacuni = new ArrayList<Racun>();
			String[] racuni = (lineP[8].trim()).split("\\;");
			for (String s : racuni) {
				Racun racun = new Racun(s.trim(), 2);
				this.izdatiRacuni.add(racun);
			}
		}

	}

	@Override
	public String toString() {
		/*String dodatiRacuni = "/";

		if (this.izdatiRacuni != null) {
			String[] string = new String[this.izdatiRacuni.size()];
			int a = 0;
			for (Racun r : this.izdatiRacuni) {
				String racun = String.format("%s,%s,%s,%s", Main.sdf.format(r.getDatumIzdavanja()), r.getUkupnaCena(),
						r.getPlatioc().getIme(), r.getPlatioc().getPrezime());
				string[a] = racun;
				a++;
			}
			dodatiRacuni = String.join(";", string);
		} else {
			dodatiRacuni = "/";
		}
		String dodateIznajm = "/";
		if (this.otvorenaIznm != null && this.otvorenaIznm.size() != 0) {
			String[] string = new String[this.izdatiRacuni.size()];
			int b = 0;
			for (Iznajmljivanje r : this.otvorenaIznm) {
				String Iznajmljivanje = String.format("%s,%s,%s", Main.sdf.format(r.getDatumPocetka()),
						Main.sdf.format(r.getDatumZavrsetka()), r.getSoba().getBroj());
				string[b] = Iznajmljivanje;
				b++;
			}
			dodateIznajm = String.join(";", string);
		} else {
			dodateIznajm = "/";
		}
*/
		return String.format("%-15s| %-15s | %-15s  | %-15s ", this.ime, this.prezime, this.brLicneKarte,this.korisnickoIme);
	}

	public ArrayList<Iznajmljivanje> getOtvorenaIznm() {
		return otvorenaIznm;
	}

	public void setOtvorenaIznm(ArrayList<Iznajmljivanje> otvorenaIznm) {
		this.otvorenaIznm = otvorenaIznm;
	}

	public ArrayList<Racun> getIzdatiRacuni() {
		return izdatiRacuni;
	}

	public void setIzdatiRacuni(ArrayList<Racun> izdatiRacuni) {
		this.izdatiRacuni = izdatiRacuni;
	}

}
