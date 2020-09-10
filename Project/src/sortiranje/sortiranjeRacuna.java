package sortiranje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Osobe.Korisnik;
import Utility.Utility;
import klase.Racun;
import mein.Main;

public class sortiranjeRacuna {

	public static void sortiranje(ArrayList<Racun> listRacun) {
		System.out.println("1----> Sortiranje po datumu izdavanja" + "\n2----> Sortiranje po ukupnoj ceni"
				+ "\n3----> Sortiranje po broju sobe\n4----> Sortiranje po tipu sobe\n5----> Soritanje po imenu i prezimenu platioca");
		Main.sc = new Scanner(System.in);
		boolean zavrsio = false;
		while (zavrsio == false) {
			System.out.println("Unesite komandu...");
			int komanda = Utility.unesiInt(Main.sc);
			switch (komanda) {
			case 1:
				sortiranjePoDatumu( listRacun);
				zavrsio = true;
				break;
			case 2:
				sortiranjePoCeni(listRacun);
				zavrsio = true;
				break;
			case 3:
				System.out.println("opcija 3 nije implementirana!!");
				zavrsio = true;
				break;
			case 4:
				System.out.println("Opcija 3 nije implementirana!!");
				zavrsio = true;
				break;
			case 5:
				sortiranjePoImenuIPrezimenu(listRacun);
				zavrsio = true;
				break;
			}
		}
	}

	public static void sortiranjePoDatumu(ArrayList<Racun> listRacun) {
		Main.sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(listRacun, new Comparator<Racun>() {
				@Override
				public int compare(Racun arg0, Racun arg1) {
					return Integer.valueOf(arg1.getDatumIzdavanja().compareTo(arg0.getDatumIzdavanja()));
				}
			});
		} else if (i == 2) {
			Collections.sort(listRacun, new Comparator<Racun>() {
				@Override
				public int compare(Racun arg0, Racun arg1) {
					return Integer.valueOf(arg0.getDatumIzdavanja().compareTo(arg1.getDatumIzdavanja()));
				}
			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePoDatumu(listRacun);
		}

	}

	public static void sortiranjePoCeni(ArrayList<Racun> listRacun) {

		Main.sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(listRacun, new Comparator<Racun>() {
				@Override
				public int compare(Racun arg0, Racun arg1) {
					return (int) (arg1.getUkupnaCena() - arg0.getUkupnaCena());
				}
			});
		} else if (i == 2) {
			Collections.sort(listRacun, new Comparator<Racun>() {
				@Override
				public int compare(Racun arg0, Racun arg1) {
					return (int) (arg0.getUkupnaCena() - arg1.getUkupnaCena());
				}
			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePoCeni(listRacun);
		}

	}

	public static void sortiranjePoImenuIPrezimenu(ArrayList<Racun> listRacun) {

		System.out.println("1 Opadajuci\n2 Rastuci");
		Main.sc = new Scanner(System.in);
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(listRacun, new Comparator<Racun>() {
				@Override
				public int compare(Racun arg, Racun arg1) {
					int vrednostIme = arg1.getPlatioc().getIme().compareTo(arg.getPlatioc().getIme());
					int vrednostPrezime = arg1.getPlatioc().getPrezime().compareTo(arg.getPlatioc().getPrezime());
					return vrednostIme + vrednostPrezime;
				}
			});
		} else if (i == 2) {
			Collections.sort(listRacun, new Comparator<Racun>() {
				@Override
				public int compare(Racun arg, Racun arg1) {
					int vrednostIme = arg.getPlatioc().getIme().compareTo(arg1.getPlatioc().getIme());
					int vrednostPrezime = arg.getPlatioc().getPrezime().compareTo(arg1.getPlatioc().getPrezime());
					return vrednostIme + vrednostPrezime;
				}

			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePoImenuIPrezimenu(listRacun);
		}

	}
}
