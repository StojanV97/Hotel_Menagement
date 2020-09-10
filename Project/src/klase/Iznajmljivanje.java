package klase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Osobe.Osoba;
import mein.Main;

public class Iznajmljivanje {

	private Date datumPocetka;
	private	Date datumZavrsetka;
	private	Soba soba;
	private ArrayList<Osoba> gosti;
	private boolean aktivnost;


	public Iznajmljivanje() {
		
	}
	
	public Iznajmljivanje(String line) {
		String lineP[] = (line.trim()).split("\\|");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH.mm");
		try {
			Date datumP = sdf.parse(lineP[0].trim());
			Date datumZ = sdf.parse(lineP[1].trim());
			this.datumPocetka = datumP;
			this.datumZavrsetka = datumZ;
		} catch (ParseException e) {
			System.out.println("Ne mogu da parsiram  datum!!!");
			e.printStackTrace();
		}	
		this.soba = new Soba(lineP[2].trim(),2);
		this.gosti = new ArrayList<Osoba>();
		String[] osobe = (lineP[3].trim()).split("\\;");
		for(String s : osobe) {
			Osoba o = new Osoba();
			String []split = s.split("\\#");
			o.setBrLicneKarte(split[2].trim());
			o.setIme(split[0].trim());
			o.setPrezime(split[1].trim());
			this.gosti.add(o);
		}
		this.aktivnost = Boolean.valueOf(lineP[4]);
		
	}
	
	
	public Iznajmljivanje(Date datumPocetka, Date datumZavrsetka, Soba soba, ArrayList<Osoba> gosti) {
		super();
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.soba = soba;
		this.gosti = gosti;
		this.aktivnost = true;
	}

	public Iznajmljivanje(Iznajmljivanje i) {
		super();
		this.datumPocetka = i.datumPocetka;
		this.datumZavrsetka = i.datumZavrsetka;
		this.soba = i.soba;
		this.gosti = i.gosti;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Iznajmljivanje other = (Iznajmljivanje) obj;
		if (aktivnost != other.aktivnost)
			return false;
		if (datumPocetka == null) {
			if (other.datumPocetka != null)
				return false;
		} else if (!datumPocetka.equals(other.datumPocetka))
			return false;
		if (datumZavrsetka == null) {
			if (other.datumZavrsetka != null)
				return false;
		} else if (!datumZavrsetka.equals(other.datumZavrsetka))
			return false;
		if (gosti == null) {
			if (other.gosti != null)
				return false;
		} else if (!gosti.equals(other.gosti))
			return false;
		if (soba == null) {
			if (other.soba != null)
				return false;
		} else if (!soba.equals(other.soba))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String[]gosti = new String[this.gosti.size()];
		int i = 0;
		for(Osoba o : this.gosti) {
			String osoba = String.format("%s,%s,%s", o.getIme(),o.getPrezime(),o.getBrLicneKarte());
			gosti[i] = osoba;
		}
	
		String linijaGosti = String.join(";", gosti);
		return String.format("%-20s | %-20s | %-10s | %-15s", Main.sdf.format(this.datumPocetka), Main.sdf.format(this.datumZavrsetka),this.soba.broj,linijaGosti);
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}
	public Date getDatumZavrsetka() {
		return datumZavrsetka;
	}
	public void setDatumZavrsetka(Date datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}
	public Soba getSoba() {
		return soba;
	}
	
	public void setSoba(Soba soba) {
		this.soba = soba;
	}
	
	public boolean getAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(boolean aktivnost) {
		this.aktivnost = aktivnost;
	}

	public ArrayList<Osoba> getGosti() {
		return gosti;
	}
	public void setGosti(ArrayList<Osoba> gosti) {
		this.gosti = gosti;
	}
	
	
}
