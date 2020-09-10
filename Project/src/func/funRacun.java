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

import Osobe.*;
import Utility.Utility;
import klase.*;
import mein.Main;

public class funRacun {

	public static void prikaz(ArrayList<Racun> listRacun) {
		System.out.printf("%-20s | %-15s | %-15s | %-15s", "DATUM IZDAVANJA", "UKUPNA CENA", "IME PLATIOCA",
				"PREZIME P.\n");
		System.out.println("--------------------------------------------------------------");
		for (Racun r : listRacun) {
			if (r.getAktivnost()) {
				System.out.println(r.toString());
			}
		}
	}

	public static void ucitajRacun() {

		try {
			Main.bf = new BufferedReader(new FileReader("." + Main.separator + "racuni.txt"));
			String linija = null;
			while ((linija = Main.bf.readLine()) != null) {
				try {

					Racun racun = new Racun(linija);
					Main.listaRacuna.add(racun);

				} catch (Exception e) {
					// do nothing
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Main.bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void upisiRacun() {
		try {
			Main.pr = new PrintWriter(new FileWriter("." + Main.separator + "racuni.txt"));
			for (Racun racun : Main.listaRacuna) {
				String datum = Main.sdf.format(racun.getDatumIzdavanja());
				String linijaZaUpis = String.format("%s|%s|%s|%s|%s|%s", datum, racun.getUkupnaCena(),
						racun.getPlatioc().getBrLicneKarte(), racun.getPlatioc().getIme(),
						racun.getPlatioc().getPrezime(), racun.getAktivnost());

				Main.pr.append(linijaZaUpis);
				Main.pr.append("\n");
				Main.pr.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.pr.close();
	}

	public static void obrisiRacun() {
		Main.sc = new Scanner(System.in);
		System.out.println("Unesite ime platioca: ");
		String ime = Utility.unesiTekst(Main.sc);
		System.out.println("Unesite prezime platioca: ");
		String prezime = Utility.unesiTekst(Main.sc);
		Boolean flag = false;
		for (Racun racun : Main.listaRacuna) {
			if (ime.equalsIgnoreCase(racun.getPlatioc().getIme())
					&& prezime.equalsIgnoreCase(racun.getPlatioc().getPrezime())) {
				System.out.printf("Obrisan je racun : %s", racun.toString());
				flag = true;
			}
		}
		if (flag == false) {
			System.out.println("Nema racuna sa unetim platiocem!!");
		}

	}

	public static void izdajRacun() {
		Main.sc = new Scanner(System.in);
		SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy.");
		Date datumIzdavanja = new Date();
		Iznajmljivanje iznajmljivanje = null;
		double cena = 0;
		System.out.print("Unesite broj sobe : ");
		String brojSobe = Utility.unesiTekst(Main.sc);
		System.out.print("Unesite datum : ");
		Date datumP = Utility.unesiVreme(Main.sc);
		boolean aktivnost = true;
		boolean flag = false;
		for (Iznajmljivanje i : Main.listaIznajmljivanja) {
			if (i.getAktivnost()) {
				StavkaCenovnika sc = new StavkaCenovnika();
				for (StavkaCenovnika s : Main.listaStavki) {
					if (i.getSoba().getTipSobe().getNazivSobe().equalsIgnoreCase(s.getTipSobe().getNazivSobe())) {
						System.out.println(s.toString());
						sc = s;
					}
				}
				if (brojSobe.equalsIgnoreCase(i.getSoba().getBroj()) && datumP.equals(i.getDatumPocetka())) {
					flag = true;
					System.out.println(i.toString());
					String dPIZNM = formater.format(i.getDatumPocetka());
					String dKIZNM = formater.format(i.getDatumZavrsetka());
					String datumIzdavanjString = formater.format(datumIzdavanja);
					if (dPIZNM.equals(dKIZNM)) {
						if (datumIzdavanjString.equals(dKIZNM)) {
							if (datumIzdavanja.getHours() < 22) {
								if (i.getDatumPocetka().getDay() > 5) {
									cena = sc.getDnevniBoravak() + sc.getVikendPoskupljenje() / 100;
								} else {
									cena = sc.getDnevniBoravak();
								}
							} else {
								System.out.println(
										"Zakasnili ste za odjavu , naplata sobe ce se izvrsiti po standardnoj ceni nocenja!!!");
								if (i.getDatumPocetka().getDay() > 5) {
									cena = sc.getNocenje() * sc.getVikendPoskupljenje() / 100;
								} else {
									cena = sc.getNocenje();
								}
							}
						} else if (i.getDatumZavrsetka().getDate() > datumIzdavanja.getDate()) {
							System.out.println(
									"Zakasnili ste za odjavu , naplata sobe ce se izvrsiti po standardnoj ceni nocenja!!!");
							if (i.getDatumPocetka().getDay() > 5) {
								cena = sc.getNocenje() * sc.getVikendPoskupljenje() / 100;
							} else {
								cena = sc.getNocenje();
							}
						}
					} else {
						int danPocetak = i.getDatumPocetka().getDate();
						int danIzlaza = datumIzdavanja.getDate();
						Date datumProvere = i.getDatumPocetka();
						for (int a = danPocetak; a < danIzlaza; a++) {
							datumProvere.setDate(a);
							if (datumProvere.getDay() > 5) {
								cena += sc.getNocenje() * sc.getVikendPoskupljenje() / 100;
							} else {
								cena += sc.getNocenje();
							}
						}

					}

				}

			}
		}
		if (flag == false) {
			System.out.println("Nije pronadjena soba sa unetim brojem!!!");
		} else {
			System.out.print("Unesite ime platioca : ");
			String ime = Utility.unesiTekst(Main.sc);
			System.out.print("Unesite prezime platioca: ");
			String prezime = Utility.unesiTekst(Main.sc);
			System.out.print("Unesite broj licne karte: ");
			String licna = Utility.unesiTekst(Main.sc);
			Osoba o = new Osoba(licna, ime, prezime);
			Racun r = new Racun(datumIzdavanja, cena, o, true);
			System.out.println(r.toString());
			Main.listaRacuna.add(r);
			ArrayList<Racun> ir = new ArrayList<Racun>();
			if (((Recepcioner) Main.prijavljenKornik).getIzdatiRacuni() != null) {
				ir.addAll(((Recepcioner) Main.prijavljenKornik).getIzdatiRacuni());
				ir.add(r);
				((Recepcioner) Main.prijavljenKornik).setIzdatiRacuni(ir);
			} else {
				ir.add(r);
				((Recepcioner) Main.prijavljenKornik).setIzdatiRacuni(ir);
			}

		}
		Main.sc.close();
	}

	public static void pregledRacuna() {
		boolean zavrsiPetlju = false;
		Main.sc = new Scanner(System.in);
		while (zavrsiPetlju == false) {
			System.out.println("1----->Ptretraga po datumu izdavanja\n2----->Pretraga po opsegu datuma"
					+ "\n3----->Pretraga po ukupnoj ceni"
					+ "\n4----->Pretraga  po opsegu cena\n5----->Pretraga po broju sobe\n6----->Pretraga po tipu sobe\n7----->Pretraga po imenu i prezimnu platioca"
					+ "\n0----->Izlaz\n");
			System.out.print("Unesite opciju: ");
			switch (Utility.unesiInt(Main.sc)) {
			case 1:
				pretragaPoDatumu(Main.sc);
			case 2:
				pretragaPoOpseguDatuma(Main.sc);
				break;
			case 3:
				pretragaPoUkupnojCeni(Main.sc);
				break;
			case 4:
				pretragaPoOpseguCena(Main.sc);
				break;
			case 5:
				pretragaPoBrojuSobe(Main.sc);
				break;
			case 6:
				pretragaPoTipuSobe(Main.sc);
				break;
			case 7:
				pretragaPoImenuiPrezimenu(Main.sc);
				break;
			case 0:
				zavrsiPetlju = true;
				break;
			}
		}
		Main.sc.close();
	}

	public static void pretragaPoDatumu(Scanner sc) {
		ArrayList<Racun> listaR = new ArrayList<Racun>();

		System.out.println("Unesite datum izdavanja: ");
		Date datumIzdavanja = Utility.unesiVreme(sc);
		String datumIS = Main.sdf.format(datumIzdavanja);
		boolean flag = false;
		for (Racun r : Main.listaRacuna) {
			if (r.getAktivnost()) {
				String d = Main.sdf.format(r.getDatumIzdavanja());
				if (d.contains(datumIS) || datumIzdavanja.equals(r.getDatumIzdavanja())) {
					//System.out.println(r.toString());
					listaR.add(r);
					flag = true;
				}
			}
		}
		if (flag == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		try{
			sortiranje.sortiranjeRacuna.sortiranje(listaR);
		}catch(Exception e) {
			
		}
		prikaz(listaR);
	}

	public static void pretragaPoOpseguDatuma(Scanner sc) {
		ArrayList<Racun> listaR = new ArrayList<Racun>();

		System.out.print("Unesite datum: ");
		Date datumPocetak = Utility.unesiVreme(sc);
		System.out.print("Unesite datum: ");
		Date datumKraj = Utility.unesiVreme(sc);
		boolean flag = false;
		for (Racun r : Main.listaRacuna) {
			if (r.getAktivnost()) {
				if ((r.getDatumIzdavanja().after(datumPocetak)
						|| r.getDatumIzdavanja().equals(datumPocetak) && (r.getDatumIzdavanja().before(datumKraj))
						|| r.getDatumIzdavanja().equals(datumKraj))) {
					//System.out.println(r.toString());
					listaR.add(r);
					flag = true;
				}
			}
		}
		if (flag == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		try{
			sortiranje.sortiranjeRacuna.sortiranje(listaR);
		}catch(Exception e) {
			
		}		prikaz(listaR);
	}

	public static void pretragaPoUkupnojCeni(Scanner sc) {
		ArrayList<Racun> listaR = new ArrayList<Racun>();
		
		System.out.print("Unesite ukupnu cenu: ");
		double uCena = Utility.unesiDouble(sc);
		boolean flag = false;
		for (Racun r : Main.listaRacuna) {
			if (r.getAktivnost()) {
				if (uCena == r.getUkupnaCena()) {
					//System.out.println(r.toString());
					listaR.add(r);
					flag = true;
				}
			}
		}
		if (flag == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		try{
			sortiranje.sortiranjeRacuna.sortiranje(listaR);
		}catch(Exception e) {
			
		}		prikaz(listaR);
	}

	public static void pretragaPoBrojuSobe(Scanner sc) {
		ArrayList<Racun> listaR = new ArrayList<Racun>();

		System.out.print("Unesite broj sobe: ");
		String brojSobe = Utility.unesiTekst(sc);
		boolean flag = false;
		for (Racun r : Main.listaRacuna) {
			if (r.getAktivnost()) {
				for (Iznajmljivanje i : Main.listaIznajmljivanja) {
					if (i.getAktivnost()) {
						if (i.getSoba().getBroj().equalsIgnoreCase(brojSobe)) {
							for (Osoba o : i.getGosti()) {
								if (o.getBrLicneKarte().equals(r.getPlatioc().getBrLicneKarte())) {
									//System.out.println(r.toString());
									listaR.add(r);
									flag = true;
								}
							}
						}
					}
				}
			}
		}
		if (flag == false) {
			System.out.println("Pretraga nema rezultata!!!");
		}
		try{
			sortiranje.sortiranjeRacuna.sortiranje(listaR);
		}catch(Exception e) {
			
		}		prikaz(listaR);
	}

	public static void pretragaPoOpseguCena(Scanner sc) {
		ArrayList<Racun> listaR = new ArrayList<Racun>();

		System.out.print("Unesite cenu : ");
		double cena1 = Utility.unesiDouble(sc);
		System.out.print("Unesite cenu 2: ");
		double cena2 = Utility.unesiDouble(sc);
		Boolean flag = false;
		for (Racun r : Main.listaRacuna) {
			if (r.getAktivnost()) {
				if (r.getUkupnaCena() >= cena1 && r.getUkupnaCena() <= cena2) {
					//System.out.print(r.toString());
					listaR.add(r);
					flag = true;
				}
			}
		}
		if (flag == false) {
			System.out.println("Pretraga nema rezultata!!");
		}
		try{
			sortiranje.sortiranjeRacuna.sortiranje(listaR);
		}catch(Exception e) {
			
		}		prikaz(listaR);
	}

	public static void pretragaPoTipuSobe(Scanner sc) {
		ArrayList<Racun> listaR = new ArrayList<Racun>();

		System.out.print("Unos tipa sobe ---> ");
		System.out.print("Unesite naziv : ");
		TipSobeEnum tpE = Utility.unesiEnum(sc);
		boolean flag = false;
		for (Racun r : Main.listaRacuna) {
			if (r.getAktivnost()) {
				for (Iznajmljivanje i : Main.listaIznajmljivanja) {
					if (i.getAktivnost()) {
						if (tpE.name().equalsIgnoreCase(i.getSoba().getTipSobe().getNazivSobe())) {
							for (Osoba o : i.getGosti()) {
								if (o.getBrLicneKarte().equals(r.getPlatioc().getBrLicneKarte())) {
									//System.out.println(r.toString());
									listaR.add(r);
									flag = true;
								}
							}
						}
					}
				}
			}
		}
		if (flag == false) {
			System.out.print("Pretraga nema rezultata!!!");
		}
		try{
			sortiranje.sortiranjeRacuna.sortiranje(listaR);
		}catch(Exception e) {
			
		}		prikaz(listaR);	}

	public static void pretragaPoImenuiPrezimenu(Scanner sc) {
		ArrayList<Racun> listaR = new ArrayList<Racun>();
		System.out.print("Unesite ime : ");
		String ime = Utility.unesiTekst(sc);
		System.out.print("Unesite prezime 2: ");
		String prezime = Utility.unesiTekst(sc);
		Boolean flag = false;
		for (Racun r : Main.listaRacuna) {
			if (r.getAktivnost()) {
				if (r.getPlatioc().getIme().contains(ime) && r.getPlatioc().getPrezime().contains(prezime)) {
					//System.out.print(r.toString());
					listaR.add(r);
					flag = true;
				}
			}
		}
		if (flag == false) {
			System.out.println("Pretraga nema rezultata!!");
		}
		try{
			sortiranje.sortiranjeRacuna.sortiranje(listaR);
		}catch(Exception e) {
			
		}		prikaz(listaR);
	}
}
