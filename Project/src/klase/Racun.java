package klase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Osobe.Osoba;
import mein.Main;

public class Racun {

		Date datumIzdavanja;
		double ukupnaCena;
		Osoba platioc;
		private boolean aktivnost;
		
		public Racun() {
			
		}
		
	
		public Racun(Date datumIzdavanja, double ukupnaCena, Osoba platioc,boolean aktivnost) {
			super();
			this.datumIzdavanja = datumIzdavanja;
			this.ukupnaCena = ukupnaCena;
			this.platioc = platioc;
			this.aktivnost = aktivnost;
		}
		
		
		public Racun(String line) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH.mm");
			String [] lineP = (line.trim()).split("\\|");
			try {
				this.datumIzdavanja = sdf.parse(lineP[0].trim());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.ukupnaCena = Double.valueOf(lineP[1].trim());
			Osoba o = new Osoba();
			o.setBrLicneKarte(lineP[3].trim());
			o.setIme(lineP[4].trim());
			o.setPrezime(lineP[2].trim());
			this.platioc = o;
			this.aktivnost = Boolean.valueOf(lineP[5].trim());
			
		}
		public Racun(String line,int konstruktor2) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH.mm");
			String [] lineP = (line.trim()).split("\\#");
			try {
				this.datumIzdavanja = sdf.parse(lineP[0].trim());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.ukupnaCena = Double.valueOf(lineP[1].trim());
			Osoba o = new Osoba();
			o.setBrLicneKarte(lineP[2].trim());
			o.setIme(lineP[3].trim());
			o.setPrezime(lineP[4].trim());
			this.platioc = o;
			this.aktivnost = Boolean.valueOf(lineP[5].trim());
			
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Racun other = (Racun) obj;
			if (aktivnost != other.aktivnost)
				return false;
			if (datumIzdavanja == null) {
				if (other.datumIzdavanja != null)
					return false;
			} else if (!datumIzdavanja.equals(other.datumIzdavanja))
				return false;
			if (platioc == null) {
				if (other.platioc != null)
					return false;
			} else if (!platioc.equals(other.platioc))
				return false;
			if (Double.doubleToLongBits(ukupnaCena) != Double.doubleToLongBits(other.ukupnaCena))
				return false;
			return true;
		}


		@Override
		public String toString() {
			return String.format("%-20s | %-15s | %-15s | %-15s", Main.sdf.format(this.datumIzdavanja), this.ukupnaCena, this.platioc.getIme(),this.platioc.getPrezime());
		}
		
		public boolean getAktivnost() {
			return aktivnost;
		}
		public void setAktivnost(boolean aktivnost) {
			this.aktivnost = aktivnost;
		}
		
		public Date getDatumIzdavanja() {
			return datumIzdavanja;
		}
		public void setDatumIzdavanja(Date datumIzdavanja) {
			this.datumIzdavanja = datumIzdavanja;
		}
		public double getUkupnaCena() {
			return ukupnaCena;
		}
		public void setUkupnaCena(double ukupnaCena) {
			this.ukupnaCena = ukupnaCena;
		}
		public Osoba getPlatioc() {
			return platioc;
		}
		public void setPlatioc(Osoba platioc) {
			this.platioc = platioc;
		}
		
	
}
