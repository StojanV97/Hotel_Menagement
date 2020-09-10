package func;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Utility.Utility;
import klase.Iznajmljivanje;
import klase.Soba;
import klase.StavkaCenovnika;
import klase.TipSobe;
import klase.TipSobeEnum;
import mein.Main;

public class funcSoba {

	public static void prikaz(ArrayList<Soba> listaStavki) {
		System.out.printf("\"%-15s | %-14s | %-15s | %-20s | %s\n", "BROJ.", "TV ", "MINI BAR.", "NAZIV SOBE",
				"BR. KREVETA");
		System.out.println("-----------------------------------------------------------------------------------------");
		for (Soba r : listaStavki) {
			if (r.gettAktivnost()) {
				System.out.println(r.toString());
			}
		}
	}

	public static void ucitajSobe() {
		String linija;
		try {
			Main.bf = new BufferedReader(new FileReader("." + Main.separator + "sobe.txt"));
			while ((linija = Main.bf.readLine()) != null) {
				try {
					Soba soba = new Soba(linija);
					Main.listaSoba.add(soba);

				} catch (Exception e) {
					// do nothing
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!!");
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println("Linija nevalidna");
			e.printStackTrace();
		}
		try {
			Main.bf.close();
		} catch (IOException e) {
			System.out.println("Ne mogu da zatvorim stream reader!!!");
			e.printStackTrace();
		}
	}

	public static void upisiSobe() {
		String linijaUpis;
		try {
			Main.pr = new PrintWriter(new FileWriter("." + Main.separator + "sobe.txt"));
			for (Soba soba : Main.listaSoba) {
				linijaUpis = String.format("%s|%s|%s|%s;%s|%s", soba.getBroj(), soba.getTv(), soba.getMiniBar(),
						soba.getTipSobe().getNazivSobe(), soba.getTipSobe().getBrojKreveta(), soba.gettAktivnost());
				Main.pr.append(linijaUpis);
				Main.pr.append("\n");
				Main.pr.flush();
			}
		} catch (IOException e) {
			System.out.println("Execption pri upisivanju soba!!!!");
			e.printStackTrace();
		}
		Main.pr.close();
	}

	public static void unosSobe() {
		prikaz(Main.listaSoba);
		Main.sc = new Scanner(System.in);
		String brojSobe = null;
		boolean validanBroj = false;
		while (validanBroj == false) {
			System.out.println("Unesite broj sobe");
			brojSobe = Utility.unesiTekst(Main.sc);
			boolean flag = false;
			for (Soba s : Main.listaSoba) {
				if (s.getBroj().equalsIgnoreCase(brojSobe)) {
					System.out.println("Uneti broj sobe vec postoji, mora biti jedinstven!!!");
					flag = true;
					break;
				}
			}
			if (flag == false) {
				validanBroj = true;
			}
		}
		System.out.print("Unos tipa sobe -----> ");
		TipSobeEnum tpE = Utility.unesiEnum(Main.sc);
		TipSobe tipSobe = new TipSobe(tpE.name(), tpE.ordinal() + 1);
		System.out.println("Da li soba ima tv (da/ne): ");
		boolean tv = Utility.unesiOdluku(Main.sc);
		System.out.println("Da li soba ima mini bar (da/ne): ");
		boolean miniBar = Utility.unesiOdluku(Main.sc);

		Soba soba = new Soba(brojSobe, tv, miniBar, tipSobe);
		Main.listaSoba.add(soba);
		Main.sc.close();
	}

	public static void brisanjeSobe() {
		Main.sc = new Scanner(System.in);
		String brojSobe = Utility.unesiTekst(Main.sc);
		boolean pronasaoSobu = false;
		for (Soba s : Main.listaSoba) {
			if (brojSobe.equalsIgnoreCase(s.getBroj()) && s.gettAktivnost() == true) {
				s.setAktivnost(false);
				pronasaoSobu = true;
				System.out.printf("Izbrisana je soba sa brojem %s", s.getBroj());
				break;
			}
		}
		if (pronasaoSobu == false) {
			System.out.println("Nije pronadjena soba sa unetim brojem!!!");
		}
	}

	public static void izmenaSobe() {
		Main.sc = new Scanner(System.in);
		String brojSobe = Utility.unesiTekst(Main.sc);
		boolean pronasaoSobu = false;
		for (Soba soba : Main.listaSoba) {
			if (brojSobe.equalsIgnoreCase(soba.getBroj()) && soba.gettAktivnost() != false) {
				String brojSobeNovi = null;
				boolean validanBroj = false;
				while (validanBroj == false) {
					System.out.println("Unesite broj sobe");
					brojSobe = Utility.unesiTekst(Main.sc);
					boolean flag = false;
					for (Soba s : Main.listaSoba) {
						if (s.getBroj().equalsIgnoreCase(brojSobe)) {
							System.out.println("Uneti broj sobe vec postoji, mora biti jedinstven!!!");
							flag = true;
							break;
						}
					}
					if (flag == false) {
						validanBroj = true;
					}
				}
				System.out.print("Unos tipa sobe -----> ");
				System.out.println("Unesite naziv sobe: ");
				String nazivSobe = Utility.unesiTekst(Main.sc);
				System.out.print("\t\t      Unesite broj kreveta: ");
				boolean validanBrojKreveta = false;
				int brojKreveta = 0;
				while (validanBrojKreveta == false) {
					brojKreveta = Utility.unesiInt(Main.sc);
					if (brojKreveta > 0 && brojKreveta < 5) {
						validanBrojKreveta = true;
					} else {
						System.out.print("Uneli ste ne validan broj kreveta , unesite ponovo!!! : ");
					}
				}
				TipSobe tipSobe = new TipSobe(nazivSobe, brojKreveta);
				System.out.println("Da li soba ima tv (da/ne): ");
				boolean tv = Utility.unesiOdluku(Main.sc);
				boolean miniBar = Utility.unesiOdluku(Main.sc);

				soba.setBroj(brojSobeNovi);
				soba.setAktivnost(true);
				soba.setMiniBar(miniBar);
				soba.setTv(tv);
				soba.setTipSobe(tipSobe);
				break;
			}
		}
		if (pronasaoSobu == false) {
			System.out.println("Nije pronadjena soba sa unetim brojem!!!");
		}
	}

	public static void pregledSoba() {
		boolean zavrsiPetlju = false;
		Main.sc = new Scanner(System.in);
		while (zavrsiPetlju == false) {
			System.out.println("1----->Ptretraga po broju sobe\n2----->Pretraga po tipu sobe"
					+ "\n3----->Pretraga po stanju" + "\n4----->Pretraga  tv i mini bar" + "\n0----->Izlaz\n");
			System.out.print("Unesite opciju: ");
			switch (Utility.unesiInt(Main.sc)) {
			case 1:
				pretragaPoBroju(Main.sc);
				break;
			case 2:
				pretragaPoTipu(Main.sc);
				break;
			case 3:
				pretragaPoStanju(Main.sc);
				break;
			case 4:
				pretragaTVMINIBAR(Main.sc);
				break;
			case 0:
				zavrsiPetlju = true;
				break;
			}
		}
		Main.sc.close();
	}

	public static void pretragaPoBroju(Scanner sc) {
		ArrayList<Soba> listaS = new ArrayList<Soba>();

		System.out.print("Unesite broj sobe: ");
		String brojSobe = Utility.unesiTekst(sc);
		boolean pronasaoSobu = false;
		for (Soba soba : Main.listaSoba) {
			if (soba.gettAktivnost()) {
				if (soba.getBroj().equalsIgnoreCase(brojSobe) || soba.getBroj().contains(brojSobe)) {
					//System.out.println(soba.toString());
					listaS.add(soba);
					pronasaoSobu = true;
				}

			}
		}
		if (pronasaoSobu == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		sortiranje.sortiranjeSoba.sortiranje(listaS);
		prikaz(listaS);
	}

	public static void pretragaPoTipu(Scanner sc) {
		ArrayList<Soba> listaS = new ArrayList<Soba>();

		System.out.print("Uneiste naziv sobe");
		String naziv = Utility.unesiTekst(sc);
		System.out.print("Unesite broj kreveta: ");
		int brojKreveta = Utility.unesiInt(sc);
		boolean pronasaoSobu = false;
		for (Soba s : Main.listaSoba) {
			if (s.gettAktivnost()) {
				if ((s.getTipSobe().getNazivSobe().equalsIgnoreCase(naziv)
						|| s.getTipSobe().getNazivSobe().contains(naziv))
						&& s.getTipSobe().getBrojKreveta() == brojKreveta) {
					//System.out.println(s.toString());
					listaS.add(s);
					pronasaoSobu = true;
				}

			}
		}
		if (pronasaoSobu == false) {
			System.out.println("Pretraga nema rezultata!!");
		}
		sortiranje.sortiranjeSoba.sortiranje(listaS);
		prikaz(listaS);
	}

	public static void pretragaPoStanju(Scanner sc) {
		ArrayList<Soba> listaS = new ArrayList<Soba>();

		System.out.print("Unesite stanje sobe(Slobodna/Zauzeta): ");
		if (Utility.unosStanjaSobe(sc)) {
			boolean pronasaoSobu = false;
			System.out.println("Zauzete sobe: ");
			for (Iznajmljivanje i : Main.listaIznajmljivanja) {
				System.out.println(i.getSoba().toString());
				
				pronasaoSobu = true;
			}
			if (pronasaoSobu == false) {
				System.out.println("Pretraga nema rezultata!!!");
			}
		} else {
			System.out.println("Slobodne sobe: ");
			boolean pronasaoSobu = false;
			for (Iznajmljivanje i : Main.listaIznajmljivanja) {
				for (Soba s : Main.listaSoba) {
					if (!(i.getSoba().getBroj().equals(s.getBroj()))) {
						//System.out.println(s.toString());
						listaS.add(s);
						pronasaoSobu = true;
					}
				}
			}
			if (pronasaoSobu == false) {
				System.out.println("Pretraga nema rezultata!!!");
			}

		}
		sortiranje.sortiranjeSoba.sortiranje(listaS);
		prikaz(listaS);
	}

	public static void pretragaTVMINIBAR(Scanner sc) {
		ArrayList<Soba> listaS = new ArrayList<Soba>();
		System.out.println("TV (Sadrzi/Ne sadrzi): ");
		boolean tv = Utility.unosTVBAR(sc);
		System.out.println("Mini Bar (Sadrzi/Ne sadrzi): ");
		boolean miniBar = Utility.unosTVBAR(sc);
		boolean pronasaoSobu = false;
		for (Soba s : Main.listaSoba) {
			if(s.gettAktivnost()) {
				if (s.getMiniBar() == miniBar && s.getTv() == tv) {
					//System.out.println(s.toString());
					listaS.add(s);
					pronasaoSobu = true;
				}
			
			}
		}
		if (pronasaoSobu == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		sortiranje.sortiranjeSoba.sortiranje(listaS);
		prikaz(listaS);
	}
}
