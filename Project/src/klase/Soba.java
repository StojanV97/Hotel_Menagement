package klase;

public class Soba {

	String broj;
	boolean tv;
	boolean miniBar;
	TipSobe tipSobe;
	private boolean aktivnost;

	public Soba() {

	}

	public Soba(Soba s) {
		super();
		this.broj = s.broj;
		this.tv = s.tv;
		this.miniBar = s.miniBar;
		this.tipSobe = s.tipSobe;
	}

	public Soba(String line) {
		String[] linePod = (line.trim()).split("\\|");
		this.broj = linePod[0].trim();
		this.tv = Boolean.valueOf(linePod[1].trim());
		this.miniBar = Boolean.valueOf(linePod[2].trim());
		this.tipSobe = new TipSobe(linePod[3].trim());
		this.aktivnost = Boolean.valueOf(linePod[4].trim());
	}

	public Soba(String line, int konstruktorDodele2) {
		String[] linePod = (line.trim()).split("\\;");
		this.broj = linePod[0].trim();
		this.tv = Boolean.valueOf(linePod[1].trim());
		this.miniBar = Boolean.valueOf(linePod[2].trim());
		this.tipSobe = new TipSobe();
		this.tipSobe.setNazivSobe(linePod[3].trim());
		this.tipSobe.setBrojKreveta(Integer.valueOf(linePod[4]));
		this.aktivnost = Boolean.valueOf(linePod[5].trim());
	}

	public Soba(String broj, boolean tv, boolean miniBar, TipSobe tipSobe) {
		super();
		this.broj = broj;
		this.tv = tv;
		this.miniBar = miniBar;
		this.tipSobe = tipSobe;
		this.aktivnost = true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Soba other = (Soba) obj;
		if (aktivnost != other.aktivnost)
			return false;
		if (broj == null) {
			if (other.broj != null)
				return false;
		} else if (!broj.equals(other.broj))
			return false;
		if (miniBar != other.miniBar)
			return false;
		if (tipSobe == null) {
			if (other.tipSobe != null)
				return false;
		} else if (!tipSobe.equals(other.tipSobe))
			return false;
		if (tv != other.tv)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String tv2 = null;
		String miniB = null;
		if (this.tv) {
			tv2 = "IMA";
		} else {
			tv2 = "NEMA";
		}
		if (this.miniBar) {
			miniB = "IMA";

		} else {
			miniB = "NEMA";
		}
		return String.format("%-15s | %-15s | %-15s | %-15s", this.broj, tv2, miniB, this.tipSobe.getNazivSobe(),
				this.tipSobe.getBrojKreveta());
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public boolean getTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean getMiniBar() {
		return miniBar;
	}

	public boolean gettAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(boolean aktivnost) {
		this.aktivnost = aktivnost;
	}

	public void setMiniBar(boolean miniBar) {
		this.miniBar = miniBar;
	}

	public TipSobe getTipSobe() {
		return tipSobe;
	}

	public void setTipSobe(TipSobe tipSobe) {
		this.tipSobe = tipSobe;
	}

}
