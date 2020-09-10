package func;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import Osobe.Korisnik;
import Osobe.Menadzer;
import Osobe.Recepcioner;
import Utility.Utility;
import klase.Iznajmljivanje;
import klase.Racun;
import klase.StavkaCenovnika;
import mein.Main;
import sortiranje.sortiranjeKorisnika;

public class funcKorisnik {

	public static void prikaz(ArrayList<Korisnik> listaKor) {
		System.out.printf("%-15s| %-15s | %-15s  | %s", "IME", "PREZIME", "LICNA KARTA", "K. IME\n");
		System.out.println("-------------------------------------------------------------");
		for (Korisnik i : listaKor) {
			if (i.getAktivnost()) {
				System.out.println(i.toString());
			}
		}

	}

	public static void ucitajKorisnika() {
		String linija;
		try {
			Main.bf = new BufferedReader(new FileReader("." + Main.separator + "korisnici.txt"));
			while ((linija = Main.bf.readLine()) != null) {
				try {

					String[] tip = (linija.trim()).split("\\|");
					if (tip[0].equalsIgnoreCase("menadzer")) {
						Menadzer menadzer = new Menadzer(linija);
						Main.listaKorisnika.add(menadzer);
					} else if (tip[0].equalsIgnoreCase("recepcioner")) {
						Recepcioner recepcioner = new Recepcioner(linija);
						Main.listaKorisnika.add(recepcioner);
					}

				} catch (Exception e) {
					// do nothign
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

	public static void upisKorisnika() {

		try {
			Main.pr = new PrintWriter(new FileWriter("." + Main.separator + "korisnici.txt"));
			String linijaUpis = null;
			for (Korisnik k : Main.listaKorisnika) {
				if (k instanceof Menadzer) {
					linijaUpis = upisMenadzera(Main.pr, (Menadzer) k);
				} else if (k instanceof Recepcioner) {
					linijaUpis = upisRecepcionera(Main.pr, (Recepcioner) k);
				}
				Main.pr.append(linijaUpis);
				Main.pr.append("\n");
				Main.pr.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.pr.close();
	}

	public static String upisMenadzera(PrintWriter p, Menadzer k) {
		String dodateStavkeString = null;
		String korisnikString = null;
		if (k.getDodateStavke() == null) {
			korisnikString = String.format("%s|%s|%s|%s|%s|%s|%s|%s", "menadzer", k.getIme(), k.getPrezime(),
					k.getBrLicneKarte(), k.getKorisnickoIme(), k.getLozinka(), k.getAktivnost(), "null");
		} else {
			int a = 0;
			String[] nizDodatihStavnki = new String[k.getDodateStavke().size()];
			for (StavkaCenovnika sc : k.getDodateStavke()) {
				String stavke = String.format("%s#%s%s#%s%s#%s", sc.getDnevniBoravak(), sc.getNocenje(),
						sc.getVikendPoskupljenje(), sc.getTipSobe().getNazivSobe(), sc.getTipSobe().getBrojKreveta(),
						sc.getAktivnost());
				// dodateStavkeString = dodateStavkeString + ";" + stavke;
				nizDodatihStavnki[a] = stavke;
				a++;
			}
			dodateStavkeString = String.join(";", nizDodatihStavnki);
			korisnikString = String.format("%s|%s|%s|%s|%s|%s|%s|%s", "menadzer", k.getIme(), k.getPrezime(),
					k.getBrLicneKarte(), k.getKorisnickoIme(), k.getLozinka(), k.getAktivnost(), dodateStavkeString);
		}

		return korisnikString;

	}

	public static void pregledIzvestaja() {
		System.out.println("Pregled stanja poslovanja: ");
		System.out.println(
				"1--->Iznajmljivanja u opsegu\n2---->Recepcionari(Dodata Iznajmljivanja)\n3--->Recepcionar(Izdati racuni)");
		Main.sc = new Scanner(System.in);
		int unos = Utility.unesiInt(Main.sc);
		switch (unos) {
		case 1:
			ArrayList<Iznajmljivanje> iznm = new ArrayList<Iznajmljivanje>();
			System.out.println("Unesite prvi datum :");
			Date datum = (Date) Utility.unesiVreme(Main.sc);
			System.out.println("Unesite drugi datum: ");
			Date datum2 = (Date) Utility.unesiVreme(Main.sc);
			for (Iznajmljivanje i : Main.listaIznajmljivanja) {
				if (i.getDatumPocetka().after(datum) && i.getDatumZavrsetka().before(datum2)) {
					iznm.add(i);
				}
			}
			func.funcIznajmljivanje.prikaz(iznm);
			break;
		case 2:
			prikaz(Main.listaKorisnika);
			System.out.println("Unesite ime: ");
			String ime = Utility.unesiTekst(Main.sc);
			boolean flag = false;
			System.out.println("Unesite licnu kartu: ");
			String linca = Utility.unesiTekst(Main.sc);
			for (Korisnik r : Main.listaKorisnika) {
				if (r.getAktivnost()) {
					if (r instanceof Recepcioner) {
						if (ime.equals(r.getIme()) && r.getBrLicneKarte().equals(linca)) {
							flag = true;
							func.funcIznajmljivanje.prikaz(((Recepcioner) r).getOtvorenaIznm());
						}
					}
				}
			}
			if (flag == false) {
				System.out.println("Nepostojeci unos!!!");
			}
			break;
		case 3:
			prikaz(Main.listaKorisnika);
			System.out.println("Unesite ime: ");
			String ime2 = Utility.unesiTekst(Main.sc);
			boolean flag2 = false;
			System.out.println("Unesite licnu kartu: ");
			String linca2 = Utility.unesiTekst(Main.sc);
			for (Korisnik r : Main.listaKorisnika) {
				if (r.getAktivnost()) {
					if (r instanceof Recepcioner) {
						if (ime2.equals(r.getIme()) && r.getBrLicneKarte().equals(linca2)) {
							flag = true;
							func.funRacun.prikaz(((Recepcioner) r).getIzdatiRacuni());
						}
					}
				}
			}
			if (flag2 == false) {
				System.out.println("Nepostojeci unos!!!");
			}
			break;

		}
	}

	public static String upisRecepcionera(PrintWriter p, Recepcioner k) {
		String korisnikString = null;
		String izdatiRacuni = null;
		String otvorenaIzmnajmljivanje = null;
		int a = 0;
		if (k.getIzdatiRacuni() == null) {
			izdatiRacuni = "null";
		} else if (k.getIzdatiRacuni() != null) {
			String[] racuniNiz = new String[k.getIzdatiRacuni().size()];
			for (Racun r : k.getIzdatiRacuni()) {
				String datumIzdavanja = Main.sdf.format(r.getDatumIzdavanja());
				String Stringracun = String.format("%s#%s#%s#%s#%s#%s", datumIzdavanja, r.getUkupnaCena(),
						r.getPlatioc().getBrLicneKarte(), r.getPlatioc().getIme(), r.getPlatioc().getPrezime(),
						r.getAktivnost());
				/// izdatiRacuni = izdatiRacuni + ";" + Stringracun;
				racuniNiz[a] = Stringracun;
				a++;
			}
			izdatiRacuni = String.join(";", racuniNiz);
		}
		if (k.getOtvorenaIznm() == null) {
			otvorenaIzmnajmljivanje = "null";
		} else if (k.getOtvorenaIznm() != null) {
			String[] otvorenaIznajmljivanja2 = new String[k.getOtvorenaIznm().size()];
			int b = 0;
			for (Iznajmljivanje i : k.getOtvorenaIznm()) {
				String datum = Main.sdf.format(i.getDatumPocetka());
				String iznm = String.format("%s#%s", datum, i.getSoba().getBroj());
				// otvorenaIzmnajmljivanje = otvorenaIzmnajmljivanje + ";" + iznm;
				otvorenaIznajmljivanja2[b] = iznm;
				b++;
			}
			otvorenaIzmnajmljivanje = String.join(";", otvorenaIznajmljivanja2);
		}

		korisnikString = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s", "recepcioner", k.getIme(), k.getPrezime(),
				k.getBrLicneKarte(), k.getKorisnickoIme(), k.getLozinka(), k.getAktivnost(), otvorenaIzmnajmljivanje,
				izdatiRacuni);
		return korisnikString;

	}

	public static void prijava() {
		Scanner sc = new Scanner(System.in);
		boolean prijavljen = false;
		while (prijavljen == false) {
			System.out.print("Korisnicko ime: ");
			String kIme = Utility.unesiTekst(sc);
			System.out.print("Lozinka: ");
			String lozinka = Utility.unesiTekst(sc);
			for (Korisnik k : Main.listaKorisnika) {
				if (kIme.equalsIgnoreCase(k.getKorisnickoIme()) && lozinka.equals(k.getLozinka())) {
					if (k instanceof Menadzer) {
						System.out.printf("Ulogovali ste se kao Menadzer:  %s %s\n", k.getIme(), k.getPrezime());
						prijavljen = true;
						Main.prijavljenKornik = k;
						sc.close();
						break;
					} else {
						System.out.printf("Ulogovali ste se kao Recepcioter:  %s %s\n", k.getIme(), k.getPrezime());
						prijavljen = true;
						Main.prijavljenKornik = k;
						sc.close();
						break;
					}
				}
			}
			if (prijavljen == false) {
				System.out.println("Pogresan unos!!!");
			}
		}

	}

	public static void unosKorisnika() {

		Scanner sc = new Scanner(System.in);
		System.out.print("Unesite ime: ");
		String ime = Utility.unesiTekst(sc);
		System.out.print("Unesite prezime: ");
		String prezime = Utility.unesiTekst(sc);
		String korisnicoIme = null, lozinka = null;
		System.out.print("Unesite broj licne karte: ");
		// double brLicneKarte2 = Utility.unesiDouble(Main.sc);
		String brLicneKarte = Utility.unesiTekst(sc);
		korisnicoIme = proveriKorisnickoIme();
		System.out.print("Unesite lozinku: ");
		lozinka = Utility.unesiTekst(sc);
		napraviKorisnika(ime, prezime, brLicneKarte, korisnicoIme, lozinka);
		sc.close();

	}

	public static String proveriKorisnickoIme() {
		String korisnicoIme = null;
		boolean flag = false;
		while (flag == false) {
			boolean flag2 = false;
			System.out.print("Unesite korisnicko ime: ");
			korisnicoIme = Utility.unesiTekst(Main.sc);
			for (Korisnik k : Main.listaKorisnika) {
				if (korisnicoIme.equalsIgnoreCase(k.getKorisnickoIme())) {
					System.out.println("Postojece korisnicko ime , unesite ponovo!!!!");
					flag2 = true;
					break;
				}
			}
			if (flag2 == false) {
				flag = true;
			}
		}
		return korisnicoIme;
	}

	public static void napraviKorisnika(String ime, String prezime, String brLicneKarte, String korisnicoIme,
			String lozinka) {
		boolean flagTip = false;
		while (flagTip == false) {
			System.out.print("Menadzer(1) ili Recepcioner(2): ");
			int tip = Utility.unesiInt(Main.sc);
			switch (tip) {
			case 1:
				Menadzer m = new Menadzer(ime, prezime, brLicneKarte, korisnicoIme, lozinka, true, null);
				flagTip = true;
				Main.listaKorisnika.add(m);
				break;
			case 2:
				Recepcioner r = new Recepcioner(ime, prezime, brLicneKarte, korisnicoIme, lozinka, true, null, null);
				flagTip = true;
				Main.listaKorisnika.add(r);
				break;
			}
		}
	}

	public static void izmenaKorisnika() {
		System.out.println("Unesite ime, prezime i korisnicko ime.");
		System.out.print("Ime : ");
		String ime = Utility.unesiTekst(Main.sc);
		System.out.print("Prezime : ");
		String prezime = Utility.unesiTekst(Main.sc);
		System.out.print("Korisnico ime : ");
		String korisnickoIme = proveriKorisnickoIme();
		boolean flag = false;
		for (Korisnik k : Main.listaKorisnika) {
			if (k.getAktivnost()) {
				if (ime.equalsIgnoreCase(k.getIme()) && prezime.equalsIgnoreCase(k.getPrezime())
						&& korisnickoIme.equalsIgnoreCase(k.getKorisnickoIme())) {
					flag = true;
					k.setIme(ime);
					k.setKorisnickoIme(korisnickoIme);
					k.setPrezime(prezime);
				}

			}
		}
	}

	public static void brisanjeKorisnika() {
		System.out.println("Unesite ime, prezime i korisnicko ime.");
		System.out.print("Ime : ");
		String ime = Utility.unesiTekst(Main.sc);
		System.out.print("Prezime : ");
		String prezime = Utility.unesiTekst(Main.sc);
		System.out.print("Korisnico ime : ");
		String korisnickoIme = Utility.unesiTekst(Main.sc);
		boolean flag = false;
		for (Korisnik k : Main.listaKorisnika) {
			if (k.getAktivnost()) {
				if (ime.equalsIgnoreCase(k.getIme()) && prezime.equalsIgnoreCase(k.getPrezime())
						&& korisnickoIme.equalsIgnoreCase(k.getKorisnickoIme())) {
					flag = true;
					k.setAktivnost(false);
				}

			}
		}
	}

	public static void pretragaKorisnika() {
		boolean flag = false;
		boolean flag1 = false;
		ArrayList<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
		while (flag == false) {
			System.out.print("Unesi ime: ");
			String ime = Utility.unesiTekst(Main.sc);
			System.out.println("Unesi prezime: ");
			String prezime = Utility.unesiTekst(Main.sc);
			System.out.print("Unesi korisnicko ime: ");
			String korisnickoIme = Utility.unesiTekst(Main.sc);
			int i = 0;
			for (Korisnik k : Main.listaKorisnika) {
				if (k.getAktivnost()) {
					i++;
					if ((k.getIme().equalsIgnoreCase(ime) && k.getPrezime().equalsIgnoreCase(prezime)
							&& k.getKorisnickoIme().equalsIgnoreCase(korisnickoIme))
							|| k.getIme().contains(ime) && k.getPrezime().contains(prezime)
									&& k.getKorisnickoIme().contains(korisnickoIme)) {
						// System.out.println(k.toString());
						listaKorisnika.add(k);
						flag1 = true;

					}

				}
			}
			if (i == Main.listaKorisnika.size() && flag1 == true) {
				flag = true;
			}
		}
		sortiranjeKorisnika.sortiranje2(listaKorisnika);
		prikaz(listaKorisnika);
	}
}
