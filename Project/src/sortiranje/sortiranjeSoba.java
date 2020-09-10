package sortiranje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Osobe.Korisnik;
import Utility.Utility;
import klase.Soba;
import mein.Main;

public class sortiranjeSoba {

	public static void sortiranje(ArrayList<Soba> listSoba) {
		System.out.println(
				"1----> Sortiranje po broju\n2 ---->Sortiranje po tipu");
		Main.sc = new Scanner(System.in);
		boolean zavrsio = false;
		while (zavrsio == false) {
			System.out.println("Unesite komandu...");
			int komanda = Utility.unesiInt(Main.sc);
			switch (komanda) {
			case 1:
				sortiranjePoBroju(listSoba);
				zavrsio = true;
				break;

			case 2:
				sortiranjePoTipu(listSoba);
				zavrsio = true;
				break;
			}
		}
	}
	
	public static void sortiranjePoBroju(ArrayList<Soba> listSoba) {
		Main.sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(Main.listaSoba, new Comparator<Soba>() {
				@Override
				public int compare(Soba o1, Soba o2) {
					return o2.getBroj().compareTo(o1.getBroj());
				}
			});
		} else if (i == 2) {
			Collections.sort(Main.listaSoba, new Comparator<Soba>() {
				@Override
				public int compare(Soba o1, Soba o2) {
					return o1.getBroj().compareTo(o2.getBroj());
				}	
			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePoBroju(listSoba);
		}
	}
	public static void sortiranjePoTipu(ArrayList<Soba> listSoba) {
		Main.sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(Main.listaSoba, new Comparator<Soba>() {
				@Override
				public int compare(Soba o1, Soba o2) {
					return o2.getTipSobe().getNazivSobe().compareTo(o1.getTipSobe().getNazivSobe());
				}
			});
		} else if (i == 2) {
			Collections.sort(Main.listaSoba, new Comparator<Soba>() {
				@Override
				public int compare(Soba o1, Soba o2) {
					return o1.getTipSobe().getNazivSobe().compareTo(o2.getTipSobe().getNazivSobe());
				}	
			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjePoTipu(listSoba);
		}
	}
}
