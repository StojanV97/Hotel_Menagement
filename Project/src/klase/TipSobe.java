package klase;

public class TipSobe implements Comparable {
	
	private String nazivSobe;
	private int brojKreveta;

	public TipSobe() {
		
	}
	
	public TipSobe(String line) {
		String [] lineP = (line.trim()).split("\\;");
		this.nazivSobe = lineP[0].trim();
		this.brojKreveta = Integer.valueOf(lineP[1].trim());
		
	}
	
	public TipSobe(String line, double konstruktorDodele2) {
		String [] lineP = (line.trim()).split("\\#");
		this.nazivSobe = lineP[0].trim();
		this.brojKreveta = Integer.valueOf(lineP[1]);
		
	}
	
	public TipSobe(String nazivSobe, int brojKreveta) {
		super();
		this.nazivSobe = nazivSobe;
		this.brojKreveta = brojKreveta;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipSobe other = (TipSobe) obj;
		if (brojKreveta != other.brojKreveta)
			return false;
		if (nazivSobe == null) {
			if (other.nazivSobe != null)
				return false;
		} else if (!nazivSobe.equals(other.nazivSobe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipSobe [nazivSobe=" + nazivSobe + ", brojKreveta=" + brojKreveta + "]";
	}

	public String getNazivSobe() {
		return nazivSobe;
	}
	public void setNazivSobe(String nazivSobe) {
		this.nazivSobe = nazivSobe;
	}
	public int getBrojKreveta() {
		return brojKreveta;
	}
	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}
	@Override
	public int compareTo(Object o) {
		TipSobe tp = (TipSobe) o;
		return this.nazivSobe.compareTo(tp.getNazivSobe());
		
	}


	
	
}
