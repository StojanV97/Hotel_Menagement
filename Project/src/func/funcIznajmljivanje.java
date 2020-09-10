package func;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Osobe.Korisnik;
import Osobe.Menadzer;
import Osobe.Osoba;
import Osobe.Recepcioner;
import Utility.Utility;
import klase.Iznajmljivanje;
import klase.Soba;
import mein.Main;

public class funcIznajmljivanje {

	public static void prikaz(ArrayList<Iznajmljivanje> listaIznm) {
		System.out.printf("%-20s | %-20s | %-10s | %s", "DATUM POCETKA", "DATUM ZAVRSETKA", "BROJ SOBE", "GOSTI\n");
		System.out.println("--------------------------------------------------------------------------------");
		for (Iznajmljivanje i : listaIznm) {
			if (i.getAktivnost()) {
				System.out.println(i.toString());
			}
		}

	}

	public static void prikazAktuelnih(ArrayList<Iznajmljivanje> listaIznm) {
		System.out.printf("%-20s | %-20s | %-10s | %s", "DATUM POCETKA", "DATUM ZAVRSETKA", "BROJ SOBE", "GOSTI\n");
		System.out.println("--------------------------------------------------------------------------------");
		Date trenutni = new Date();
		for (Iznajmljivanje i : listaIznm) {
			if (i.getAktivnost()) {
				if (i.getDatumPocetka().before(trenutni) && i.getDatumZavrsetka().after(trenutni)) {
					System.out.println(i.toString());
				}
			}
		}

	}

	public static void ucitajIznajmljivanje() {
		String linija;
		try {
			Main.bf = new BufferedReader(new FileReader("." + Main.separator + "iznajmljivanja.txt"));
			while ((linija = Main.bf.readLine()) != null) {
				try {

					Iznajmljivanje iznm = new Iznajmljivanje(linija);
					Main.listaIznajmljivanja.add(iznm);

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

	public static void upisIznajmljivanje() {
		try {
			Main.pr = new PrintWriter(new FileWriter("." + Main.separator + "iznajmljivanja.txt"));
			String linijaUpis = null;
			for (Iznajmljivanje i : Main.listaIznajmljivanja) {
				String datumP = Main.sdf.format(i.getDatumPocetka());
				String datumK = Main.sdf.format(i.getDatumZavrsetka());
				String soba = String.format("%s;%s;%s;%s;%s;%s", i.getSoba().getBroj(), i.getSoba().getTv(),
						i.getSoba().getMiniBar(), i.getSoba().getTipSobe().getNazivSobe(),
						i.getSoba().getTipSobe().getBrojKreveta(), i.getSoba().gettAktivnost());
				String osobeNiz[] = new String[i.getGosti().size()];
				int a = 0;
				for (Osoba o : i.getGosti()) {
					String osoba = String.format("%s#%s#%s", o.getIme(), o.getPrezime(), o.getBrLicneKarte());
					osobeNiz[a] = osoba;
					a++;
				}
				String stringOsoba = String.join(";", osobeNiz);
				String linijaZaUpis = String.format("%s|%s|%s|%s|%s", datumP, datumK, soba, stringOsoba,
						i.getAktivnost());
				Main.pr.append(linijaZaUpis);
				Main.pr.append("\n");
				Main.pr.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.pr.close();
	}

	public static void unosIznajmljivanja() {
		func.funcSoba.prikaz(Main.listaSoba);
		Main.sc = new Scanner(System.in);
		Soba soba = null;
		Date datumPocetka = null;
		Date datumZavrsetka = null;
		Iznajmljivanje iznm = null;
		boolean postojecaSoba = false;
		while (postojecaSoba == false) {
			System.out.println("Unesite broj sobe: ");
			String brojSobe = Utility.unesiTekst(Main.sc);
			for (Soba s : Main.listaSoba) {
				if (s.getBroj().equalsIgnoreCase(brojSobe)) {
					soba = s;
					postojecaSoba = true;
					break;
				}
			}
			if (postojecaSoba == false) {
				System.out.println("Ne postoji soba sa unetim brojem!!!\n");
			}
		}
		boolean wrongDatum2 = false;
		while (wrongDatum2 == false) {
			System.out.println("Unesite datum pocetka: ");
			datumPocetka = Utility.unesiDatumVK(Main.sc);
			boolean flag = false;
			for (Iznajmljivanje i : Main.listaIznajmljivanja) {
				if (i.getAktivnost()) {
					if (i.getSoba().getBroj().equals(soba.getBroj())) {
						if (i.getDatumPocetka().equals(datumPocetka)) {
							System.out.println("Unet je datum za koji je soba zaouzeta!!!");
							flag = true;
						} else if ((i.getDatumPocetka().before(datumPocetka))
								&& i.getDatumZavrsetka().after(datumPocetka)) {
							System.out.println("Unet je datum za koji je soba zaouzeta!!!");
							flag = true;
						}

					}
				}
			}
			if (flag == false) {
				wrongDatum2 = true;
			}
		}
		boolean wrongDatum = false;
		while (wrongDatum == false) {
			System.out.println("Unesite datum zavrsetka: ");
			datumZavrsetka = Utility.unesiDatumVK(Main.sc);
			boolean flag2 = false;
			for (Iznajmljivanje i : Main.listaIznajmljivanja) {
				if (i.getAktivnost()) {
					if (i.getSoba().getBroj().equals(soba.getBroj())) {
						if (datumZavrsetka.equals(i.getDatumZavrsetka())) {
							System.out.println("Unet je datum za koji je soba zaouzeta!!!");
							flag2 = true;
						} else if (i.getDatumZavrsetka().after(datumZavrsetka)
								&& i.getDatumPocetka().before(datumZavrsetka)) {
							System.out.println("Unet je datum za koji je soba zaouzeta!!!");
							flag2 = true;
						}

					}
				}
			}
			if (flag2 == false) {
				wrongDatum = true;
			}

		}
		// sredi ovo
		iznm = new Iznajmljivanje(datumPocetka, datumZavrsetka, soba, null);

		boolean flag = false;
		ArrayList<Osoba> listaGostiju = new ArrayList<Osoba>();
		while (flag == false) {
			System.out.println("Unesite broj gostiju: ");
			int brojGostiju = Utility.unesiInt(Main.sc);
			if (brojGostiju > 0) {
				for (int i = 0; i < brojGostiju; i++) {
					System.out.println("Unesite ime: ");
					String ime = Utility.unesiTekst(Main.sc);
					System.out.println("Unesite prezime: ");
					String prezime = Utility.unesiTekst(Main.sc);
					System.out.println("Unesite licnu kartu: ");
					String licnaKarta = Utility.unesiTekst(Main.sc);
					Osoba o = new Osoba(ime, prezime, licnaKarta);
					listaGostiju.add(o);
				}
				flag = true;
			} else {
				System.out.println("Broj gostiju mora biti veci od 0!!!");
			}
		}
		iznm.setGosti(listaGostiju);
		Main.listaIznajmljivanja.add(iznm);
		// dodati korisnika koji je dodao iznajmljivanje
		ArrayList<Iznajmljivanje> iz = new ArrayList<Iznajmljivanje>();
		if (((Recepcioner) Main.prijavljenKornik).getOtvorenaIznm() != null) {
			iz.addAll(((Recepcioner) Main.prijavljenKornik).getOtvorenaIznm());
			iz.add(iznm);
			((Recepcioner) Main.prijavljenKornik).setOtvorenaIznm(iz);
		} else {
			iz.add(iznm);
			((Recepcioner) Main.prijavljenKornik).setOtvorenaIznm(iz);
		}
		Main.sc.close();
	}

	public static void izmenaIznajmljivanja() {
		Main.sc = new Scanner(System.in);
		System.out.print("Unesite broj sobe: ");
		String brojSobe = Utility.unesiTekst(Main.sc);
		System.out.print("Unesite datum pocetka: ");
		Date datumP = Utility.unesiVreme(Main.sc);
		System.out.print("Unesite datum zavrsetka: ");
		Date datumZ = Utility.unesiVreme(Main.sc);
		for (Iznajmljivanje izn : Main.listaIznajmljivanja) {
			Iznajmljivanje staroIznajmljivanje = izn;
			if (brojSobe.equals(izn.getSoba().getBroj()) && datumP.equals(izn.getDatumPocetka())
					&& datumZ.equals(izn.getDatumZavrsetka())) {
				Soba soba = null;
				Date datumPocetka = null;
				Date datumZavrsetka = null;
				boolean postojecaSoba = false;
				while (postojecaSoba == false) {
					System.out.println("Unesite novi broj sobe: ");
					String nNrojSobe = Utility.unesiTekst(Main.sc);
					for (Soba s : Main.listaSoba) {
						if (s.getBroj().equalsIgnoreCase(nNrojSobe)) {
							soba = s;
							postojecaSoba = true;
							break;
						}
					}
					if (postojecaSoba == false) {
						System.out.println("Ne postoji soba sa unetim brojem!!!\n");
					}
				}
				boolean wrongDatum2 = false;
				while (wrongDatum2 == false) {
					System.out.println("Unesite datum pocetka: ");
					datumPocetka = Utility.unesiDatumVK(Main.sc);
					boolean flag = false;
					for (Iznajmljivanje i : Main.listaIznajmljivanja) {
						if (i.getAktivnost()) {
							if (i.getSoba().getBroj().equals(soba.getBroj())) {
								if (i.getDatumPocetka().equals(datumPocetka)) {
									System.out.println("Unet je datum za koji je soba zaouzeta!!!");
									flag = true;
								} else if ((i.getDatumPocetka().before(datumPocetka))
										&& i.getDatumZavrsetka().after(datumPocetka)) {
									System.out.println("Unet je datum za koji je soba zaouzeta!!!");
									flag = true;
								}

							}
						}
					}
					if (flag == false) {
						wrongDatum2 = true;
					}
				}
				boolean wrongDatum = false;
				while (wrongDatum == false) {
					System.out.println("Unesite datum zavrsetka: ");
					datumZavrsetka = Utility.unesiDatumVK(Main.sc);
					boolean flag2 = false;
					for (Iznajmljivanje i : Main.listaIznajmljivanja) {
						if (i.getAktivnost()) {
							if (i.getSoba().getBroj().equals(soba.getBroj())) {
								if (datumZavrsetka.equals(i.getDatumZavrsetka())) {
									System.out.println("Unet je datum za koji je soba zaouzeta!!!");
									flag2 = true;
								} else if (i.getDatumZavrsetka().after(datumZavrsetka)
										&& i.getDatumPocetka().before(datumZavrsetka)) {
									System.out.println("Unet je datum za koji je soba zaouzeta!!!");
									flag2 = true;
								}

							}
						}
					}
					if (flag2 == false) {
						wrongDatum = true;
					}

				}

				boolean flag = false;
				ArrayList<Osoba> listaGostiju = new ArrayList<Osoba>();
				while (flag == false) {
					System.out.println("Unesite broj gostiju: ");
					int brojGostiju = Utility.unesiInt(Main.sc);
					if (brojGostiju > 0) {
						for (int i = 0; i < brojGostiju; i++) {
							System.out.println("Unesite ime: ");
							String ime = Utility.unesiTekst(Main.sc);
							System.out.println("Unesite prezime: ");
							String prezime = Utility.unesiTekst(Main.sc);
							System.out.println("Unesite licnu kartu: ");
							String licnaKarta = Utility.unesiTekst(Main.sc);
							Osoba o = new Osoba(ime, prezime, licnaKarta);
							listaGostiju.add(o);
						}
						flag = true;
					} else {
						System.out.println("Broj gostiju mora biti veci od 0!!!");
					}
				}
				izn.setAktivnost(true);
				izn.setDatumPocetka(datumPocetka);
				izn.setDatumZavrsetka(datumZavrsetka);
				izn.setSoba(soba);
				izn.setGosti(listaGostiju);
				ArrayList<Iznajmljivanje> listaI = new ArrayList<Iznajmljivanje>();
				listaI.addAll(((Recepcioner) Main.prijavljenKornik).getOtvorenaIznm());
				listaI.add(izn);
				((Recepcioner) Main.prijavljenKornik).setOtvorenaIznm(listaI);
				for (Korisnik k : Main.listaKorisnika) {
					if (k instanceof Recepcioner) {
						if (k.getAktivnost() && (((Recepcioner) Main.prijavljenKornik).getIzdatiRacuni()) != null) {
							for (Iznajmljivanje otvorenaIznm : ((Recepcioner) k).getOtvorenaIznm()) {
								if (otvorenaIznm.equals(staroIznajmljivanje)) {
									ArrayList<Iznajmljivanje> listaStarihIznm = new ArrayList<Iznajmljivanje>();
									listaStarihIznm.addAll(((Recepcioner) k).getOtvorenaIznm());
									listaStarihIznm.remove(otvorenaIznm);
									((Recepcioner) k).setOtvorenaIznm(listaStarihIznm);
								}
							}
						}
					}
				}
				break;
			}
		}

	}

	public static void brisanjeIznajmljivanja() {
		boolean obrisan = false;
		Main.sc = new Scanner(System.in);
		System.out.print("Unesite broj sobe: ");
		String brojSobe = Utility.unesiTekst(Main.sc);
		System.out.print("Unesite datum pocetka: ");
		Date datumP = Utility.unesiVreme(Main.sc);
		System.out.print("Unesite datum zavrsetka: ");
		Date datumZ = Utility.unesiVreme(Main.sc);
		for (Iznajmljivanje izn : Main.listaIznajmljivanja) {
			if (brojSobe.equals(izn.getSoba().getBroj()) && datumP.equals(izn.getDatumPocetka())
					&& datumZ.equals(izn.getDatumZavrsetka())) {
				izn.setAktivnost(false);
				obrisan = true;
				break;
			}
		}
		if (obrisan == false) {
			System.out.println("Nije pronadjeno iznajmljivanje!!!");
		}
	}

	public static void pretragaIznajmljivanja() {
		boolean zavrsiPetlju = false;
		while (zavrsiPetlju == false) {
			Main.sc = new Scanner(System.in);
			System.out.println("1----->Ptretraga po broju sobe\n2----->Pretraga po datumu pocetka"
					+ "\n3----->Pretraga po datumu zavsetka" + "\n4----->Pretraga po opsegu datuma"
					+ "\n5----->Pretraga po broju licne karte gosta" + "\n0----->Izlaz\n");
			System.out.print("Unesite opciju: ");
			switch (Utility.unesiInt(Main.sc)) {
			case 1:
				pretragaPoBrojuSobe(Main.sc);
				break;
			case 2:
				pretragaPoDatumuP(Main.sc);
				break;
			case 3:
				pretragaPoDatumuZ(Main.sc);
				break;
			case 4:
				pretragaPoOpseguDatuma(Main.sc);
				break;
			case 5:
				pretragaPoLicnojKarti(Main.sc);
				break;
			case 0:
				zavrsiPetlju = true;
				break;
			}
		}
	}

	public static void pretragaPoDatumuP(Scanner s) {
		ArrayList<Iznajmljivanje> listaIzn = new ArrayList<Iznajmljivanje>();

		System.out.print("Unesite datum pocetka: ");
		Date datumPocetka = Utility.unesiVreme(s);
		String datumString = Main.sdf.format(datumPocetka);
		boolean pronasao = false;
		for (Iznajmljivanje i : Main.listaIznajmljivanja) {
			String datumString2 = Main.sdf.format(i.getDatumPocetka());
			if (datumPocetka.equals(i.getDatumPocetka()) || datumString2.contains(datumString)) {
				// System.out.println(i.toString());
				listaIzn.add(i);
				pronasao = true;
			}
		}
		if (pronasao == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		sortiranje.sortiranjeIznajmljivanja.sortiranje(listaIzn);
		prikaz(listaIzn);
	}

	public static void pretragaPoDatumuZ(Scanner s) {
		ArrayList<Iznajmljivanje> listaIzn = new ArrayList<Iznajmljivanje>();

		System.out.print("Unesite datum zavsetka: ");
		Date datumZavrsetka = Utility.unesiVreme(s);
		String datumString = Main.sdf.format(datumZavrsetka);
		boolean pronasao = false;
		for (Iznajmljivanje i : Main.listaIznajmljivanja) {
			String datumString2 = Main.sdf.format(i.getDatumZavrsetka());
			if (datumZavrsetka.equals(i.getDatumZavrsetka()) || datumString2.contains(datumString)) {
				// System.out.println(i.toString());
				listaIzn.add(i);
				pronasao = true;
			}
		}
		if (pronasao == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		sortiranje.sortiranjeIznajmljivanja.sortiranje(listaIzn);
		prikaz(listaIzn);
	}

	public static void pretragaPoBrojuSobe(Scanner s) {
		ArrayList<Iznajmljivanje> listaIzn = new ArrayList<Iznajmljivanje>();

		System.out.print("Unesite broj sobe: ");
		String brojSobe = Utility.unesiTekst(s);
		boolean pronasao = false;
		for (Iznajmljivanje i : Main.listaIznajmljivanja) {
			if (brojSobe.equalsIgnoreCase(i.getSoba().getBroj()) || i.getSoba().getBroj().contains(brojSobe)) {
				// System.out.println(i.toString());
				listaIzn.add(i);
				pronasao = true;
			}
		}
		if (pronasao == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		sortiranje.sortiranjeIznajmljivanja.sortiranje(listaIzn);
		prikaz(listaIzn);
	}

	public static void pretragaPoOpseguDatuma(Scanner s) {
		ArrayList<Iznajmljivanje> listaIzn = new ArrayList<Iznajmljivanje>();

		System.out.print("Unesite pocetni datum: ");
		System.out.print("Unesite krajnji datum: ");
		Date datumPocetka = Utility.unesiVreme(s);
		Date datumZavrsetka = Utility.unesiVreme(s);
		boolean pronasao = false;
		for (Iznajmljivanje i : Main.listaIznajmljivanja) {
			if ((datumPocetka.before(i.getDatumPocetka()) && datumZavrsetka.after(i.getDatumZavrsetka()))
					|| datumPocetka.equals(i.getDatumPocetka()) && datumZavrsetka.equals(i.getDatumZavrsetka())) {
				// System.out.println(i.toString());
				listaIzn.add(i);
				pronasao = true;
			}
		}
		if (pronasao == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		sortiranje.sortiranjeIznajmljivanja.sortiranje(listaIzn);
		prikaz(listaIzn);
	}

	public static void pretragaPoLicnojKarti(Scanner s) {
		ArrayList<Iznajmljivanje> listaIzn = new ArrayList<Iznajmljivanje>();
		System.out.print("Unesite broj licne karte gosta: ");
		String brojLicneKarte = Utility.unesiTekst(s);
		boolean pronasao = false;
		for (Iznajmljivanje i : Main.listaIznajmljivanja) {
			for (Osoba o : i.getGosti()) {
				if (brojLicneKarte.equalsIgnoreCase(o.getBrLicneKarte())) {
					// System.out.println(i.toString());
					listaIzn.add(i);
					pronasao = true;
				}
			}
		}
		if (pronasao == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		sortiranje.sortiranjeIznajmljivanja.sortiranje(listaIzn);
		prikaz(listaIzn);
	}
}
