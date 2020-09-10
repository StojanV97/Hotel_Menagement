package sortiranje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Osobe.Korisnik;
import Utility.Utility;
import klase.Iznajmljivanje;
import klase.Racun;
import mein.Main;

public class sortiranjeIznajmljivanja {

	public static void sortiranje(ArrayList<Iznajmljivanje> lI) {
		System.out.println(
				"1----> Sortiranje po broju sobe \n2----> Soritranje po tipu sobe \3n---> Sortiranje po datumu pocetka\n4----> Sortiranje po datumu zavsetka\n5----> Sortiranje po broju sobe i datumu pocetka\n6----> Sortiranje po broju sobe i datumu zavsetka");
		Main.sc = new Scanner(System.in);
		boolean zavrsio = false;
		while (zavrsio == false) {
			System.out.println("Unesite komandu...");
			int komanda = Utility.unesiInt(Main.sc);
			switch (komanda) {
			case 1:
				sortiranjePoBrojuSobe(lI);
				zavrsio = true;
				break;

			case 2:
				sortiranjePoTIpu(lI);
				zavrsio = true;
				break;
			case 3:
				System.out.println("Nije implementrirana opcija 3!!!");
				zavrsio = true;
				break;
			case 5:
				sortiranjePODatumuPocetka(lI);
				zavrsio = true;
				break;
			case 6:
				sortiranjePoDatumuZavrsetka(lI);
				zavrsio = true;
				break;

			}
		}
	}

	public static void sortiranjePoBrojuSobe(ArrayList<Iznajmljivanje> lI) {

		Main.sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg0, Iznajmljivanje arg1) {
					return Integer.valueOf(arg1.getSoba().getBroj().compareTo(arg0.getSoba().getBroj()));
				}
			});
		} else if (i == 2) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg0, Iznajmljivanje arg1) {
					return Integer.valueOf(arg0.getSoba().getBroj().compareTo(arg1.getSoba().getBroj()));
				}
			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePoBrojuSobe(lI);
		}

	}

	public static void sortiranjePoTIpu(ArrayList<Iznajmljivanje> lI) {
		Main.sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg0, Iznajmljivanje arg1) {
					return Integer.valueOf(arg1.getSoba().getTipSobe().compareTo(arg0.getSoba().getTipSobe()));
				}
			});
		} else if (i == 2) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg0, Iznajmljivanje arg1) {
					return Integer.valueOf(arg0.getSoba().getTipSobe().compareTo(arg1.getSoba().getTipSobe()));
				}
			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePoTIpu(lI);
		}

	}

	public static void sortiranjePODatumuPocetka(ArrayList<Iznajmljivanje> lI) {

		Main.sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg0, Iznajmljivanje arg1) {
					return Integer.valueOf(arg1.getDatumPocetka().compareTo(arg0.getDatumPocetka()));
				}
			});
		} else if (i == 2) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg0, Iznajmljivanje arg1) {
					return Integer.valueOf(arg0.getDatumPocetka().compareTo(arg1.getDatumPocetka()));
				}
			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePODatumuPocetka(lI);
		}

	}

	public static void sortiranjePoDatumuZavrsetka(ArrayList<Iznajmljivanje> lI) {

		Main.sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg0, Iznajmljivanje arg1) {
					return Integer.valueOf(arg1.getDatumZavrsetka().compareTo(arg0.getDatumZavrsetka()));
				}
			});
		} else if (i == 2) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg0, Iznajmljivanje arg1) {
					return Integer.valueOf(arg0.getDatumZavrsetka().compareTo(arg1.getDatumZavrsetka()));
				}
			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePoDatumuZavrsetka(lI);
		}

	}

	public static void sortiranjePoDatumuPiBroju(ArrayList<Iznajmljivanje> lI) {

		System.out.println("1 Opadajuci\n2 Rastuci");
		Main.sc = new Scanner(System.in);
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg, Iznajmljivanje arg1) {
					int vrednostIme = arg1.getDatumPocetka().compareTo(arg.getDatumPocetka());
					int vrednostPrezime = arg1.getSoba().getBroj().compareTo(arg.getSoba().getBroj());
					return vrednostIme + vrednostPrezime;
				}
			});
		} else if (i == 2) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg, Iznajmljivanje arg1) {
					int vrednostIme = arg.getDatumPocetka().compareTo(arg1.getDatumPocetka());
					int vrednostPrezime = arg.getSoba().getBroj().compareTo(arg1.getSoba().getBroj());
					return vrednostIme + vrednostPrezime;
				}

			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePoDatumuPiBroju(lI);
		}

	}

	public static void sortiranjePoDatumuZiBroju(ArrayList<Iznajmljivanje> lI) {

		System.out.println("1 Opadajuci\n2 Rastuci");
		Main.sc = new Scanner(System.in);
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg, Iznajmljivanje arg1) {
					int vrednostIme = arg1.getDatumZavrsetka().compareTo(arg.getDatumZavrsetka());
					int vrednostPrezime = arg1.getSoba().getBroj().compareTo(arg.getSoba().getBroj());
					return vrednostIme + vrednostPrezime;
				}
			});
		} else if (i == 2) {
			Collections.sort(lI, new Comparator<Iznajmljivanje>() {
				@Override
				public int compare(Iznajmljivanje arg, Iznajmljivanje arg1) {
					int vrednostIme = arg.getDatumZavrsetka().compareTo(arg1.getDatumZavrsetka());
					int vrednostPrezime = arg.getSoba().getBroj().compareTo(arg1.getSoba().getBroj());
					return vrednostIme + vrednostPrezime;
				}

			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePoDatumuZiBroju(lI);
		}

	}

}
