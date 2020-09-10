package sortiranje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Osobe.Korisnik;
import Osobe.Menadzer;
import Osobe.Recepcioner;
import Utility.Utility;
import mein.Main;

public class sortiranjeKorisnika {
	public static void sortiranje2(ArrayList<Korisnik> listaKorisnika) {

		System.out.println(
				"1----> Sortiranje po korisnickom imenu\n2----> sortiranje po imenu i prezimenu\n3----> Sortiranje po tipu korisnika");
		Main.sc = new Scanner(System.in);
		boolean zavrsio = false;
		while (zavrsio == false) {
			System.out.println("Unesite komandu...");
			int komanda = Utility.unesiInt(Main.sc);
			switch (komanda) {
			case 1:
				sortiranjeKorisnikaPoKorisnickoImenu(listaKorisnika);
				zavrsio = true;
				break;

			case 2:
				sortiranjeKorisnikaPoImenuIPrezimenu(listaKorisnika);
				zavrsio = true;
				break;
			case 3:
				sortiranjePoTipuKorisnika(listaKorisnika);
				zavrsio = true;
				break;
			}
		}

	}



	public static void sortiranjeKorisnikaPoKorisnickoImenu(ArrayList<Korisnik> listaK) {
		Main.sc = new Scanner(System.in);
		System.out.println("1 Opadajuci\n2 Rastuci");
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(listaK, new Comparator<Korisnik>() {
				@Override
				public int compare(Korisnik arg0, Korisnik arg1) {
					return Integer.valueOf(arg1.getKorisnickoIme().compareTo(arg0.getKorisnickoIme()));
				}
			});
		} else if (i == 2) {
			Collections.sort(listaK, new Comparator<Korisnik>() {
				@Override
				public int compare(Korisnik arg0, Korisnik arg1) {
					return Integer.valueOf(arg0.getKorisnickoIme().compareTo(arg1.getKorisnickoIme()));
				}

			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjeKorisnikaPoKorisnickoImenu(listaK);
		}
	}

	public static void sortiranjeKorisnikaPoImenuIPrezimenu(ArrayList<Korisnik> listaK) {
		System.out.println("1 Opadajuci\n2 Rastuci");
		Main.sc = new Scanner(System.in);
		int i = Utility.unesiInt(Main.sc);
		if (i == 1) {
			Collections.sort(listaK, new Comparator<Korisnik>() {
				@Override
				public int compare(Korisnik arg, Korisnik arg1) {
					int vrednostIme = arg1.getIme().compareTo(arg.getIme());
					int vrednostPrezime = arg1.getPrezime().compareTo(arg.getPrezime());
					return vrednostIme + vrednostPrezime;
				}
			});
		} else if (i == 2) {
			Collections.sort(listaK, new Comparator<Korisnik>() {
				@Override
				public int compare(Korisnik arg, Korisnik arg1) {
					int vrednostIme = arg.getIme().compareTo(arg1.getIme());
					int vrednostPrezime = arg.getPrezime().compareTo(arg1.getPrezime());
					return vrednostIme + vrednostPrezime;
				}

			});
		} else {
			System.out.println("Pogresan unos!");
			sortiranjeKorisnikaPoImenuIPrezimenu(listaK);
		}

	}

	public static void sortiranjePoTipuKorisnika(ArrayList<Korisnik> listaK) {
		Main.sc = new Scanner(System.in);
		ArrayList<Menadzer> men = new ArrayList<Menadzer>();
		ArrayList<Recepcioner> rec = new ArrayList<Recepcioner>();
		ArrayList<Korisnik> novaListaKorisnika = new ArrayList<Korisnik>();
		for (Korisnik k : listaK) {
			if (k instanceof Menadzer) {
				men.add((Menadzer) k);
			} else if (k instanceof Recepcioner) {
				rec.add((Recepcioner) k);
			}
		}
		System.out.println("1----> Menadzeri\n2----> Biletari");
		int unos = Utility.unesiInt(Main.sc);
		if (unos == 1) {
			Collections.sort(men, new Comparator<Korisnik>() {
				@Override
				public int compare(Korisnik arg0, Korisnik arg1) {
					return arg0.getIme().compareTo(arg1.getIme());
				}

			});
			Collections.sort(rec, new Comparator<Korisnik>() {
				@Override
				public int compare(Korisnik arg0, Korisnik arg1) {
					return arg0.getIme().compareTo(arg1.getIme());
				}

			});
			novaListaKorisnika.addAll(men);
			novaListaKorisnika.addAll(rec);
			ArrayList<Korisnik> lk = new ArrayList<Korisnik>(listaK);
			listaK.removeAll(lk);
			listaK.addAll(novaListaKorisnika);
		} else if (unos == 2) {
			Collections.sort(men, new Comparator<Korisnik>() {
				@Override
				public int compare(Korisnik arg0, Korisnik arg1) {
					return arg0.getIme().compareTo(arg1.getIme());
				}

			});
			Collections.sort(rec, new Comparator<Korisnik>() {
				@Override
				public int compare(Korisnik arg0, Korisnik arg1) {
					return arg0.getIme().compareTo(arg1.getIme());
				}

			});
			novaListaKorisnika.addAll(rec);
			novaListaKorisnika.addAll(men);
			ArrayList<Korisnik> lk = new ArrayList<Korisnik>(listaK);
			listaK.removeAll(lk);
			listaK.addAll(novaListaKorisnika);
		} else {
			System.out.println("Uneli ste nepostojecu komandu,pokusajte ponovo...");
			sortiranjePoTipuKorisnika(listaK);
		}

	}

}
