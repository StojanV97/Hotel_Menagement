package klase;

public class StavkaCenovnika {
	double dnevniBoravak; //cena
	double nocenje; //cena
	double vikendPoskupljenje;
	TipSobe tipSobe;
	private boolean aktivnost;
	
	public StavkaCenovnika() {
		
	}
	
	public StavkaCenovnika(String linija) {
		String[] lineP = (linija.trim()).split("\\|");
		this.dnevniBoravak = Double.valueOf(lineP[0].trim());
		this.nocenje = Double.valueOf(lineP[1].trim());
		this.vikendPoskupljenje = Double.valueOf(lineP[2].trim());
		tipSobe = new TipSobe();
		tipSobe.setNazivSobe(lineP[3].trim());
		tipSobe.setBrojKreveta(Integer.valueOf(lineP[4].trim()));
		this.aktivnost = Boolean.valueOf(lineP[5].trim());
		
	}
	
	public StavkaCenovnika(double dnevniBoravak, double nocenje, double vikendPoskupljenje, TipSobe tipSobe,boolean aktivnost) {
		super();
		this.dnevniBoravak = dnevniBoravak;
		this.nocenje = nocenje;
		this.vikendPoskupljenje = vikendPoskupljenje;
		this.tipSobe = tipSobe;
		this.aktivnost = aktivnost;
	}
	
	@Override
	public String toString() {
		return String.format("%-15s | %-15s | %-15s | %-20s | %-15s", this.dnevniBoravak, this.nocenje, this.vikendPoskupljenje,this.tipSobe.getNazivSobe(),this.getTipSobe().getBrojKreveta());
	}

	
	public boolean getAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(boolean aktivnost) {
		this.aktivnost = aktivnost;
	}

	public double getDnevniBoravak() {
		return dnevniBoravak;
	}
	public void setDnevniBoravak(double dnevniBoravak) {
		this.dnevniBoravak = dnevniBoravak;
	}
	public double getNocenje() {
		return nocenje;
	}
	public void setNocenje(double nocenje) {
		this.nocenje = nocenje;
	}
	public double getVikendPoskupljenje() {
		return vikendPoskupljenje;
	}
	public void setVikendPoskupljenje(double vikendPoskupljenje) {
		this.vikendPoskupljenje = vikendPoskupljenje;
	}
	public TipSobe getTipSobe() {
		return tipSobe;
	}
	public void setTipSobe(TipSobe tipSobe) {
		this.tipSobe = tipSobe;
	}
	
	
}
