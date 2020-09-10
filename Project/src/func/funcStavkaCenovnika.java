package func;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Osobe.Korisnik;
import Osobe.Menadzer;
import Utility.Utility;
import klase.Racun;
import klase.StavkaCenovnika;
import klase.TipSobe;
import klase.TipSobeEnum;
import mein.Main;

public class funcStavkaCenovnika {

	public static void prikaz(ArrayList<StavkaCenovnika> listaStavki) {
		System.out.printf("%-15s | %-15s | %-17s | %-18s | %s\n", "CENA D.B.", "CENA NOCENJA", "VIKEND POSKP.",
				"NAZIV SOBE", "BR. KREVETA");
		System.out.println("-----------------------------------------------------------------------------------------");
		for (StavkaCenovnika r : listaStavki) {
			if (r.getAktivnost()) {
				System.out.println(r.toString());
			}
		}
	}

	public static void ucitajStavke() {
		try {
			Main.bf = new BufferedReader(new FileReader("." + Main.separator + "stavkeCenovnika.txt"));
			String linija = null;
			while ((linija = Main.bf.readLine()) != null) {
				try {

					StavkaCenovnika sc = new StavkaCenovnika(linija);
					Main.listaStavki.add(sc);

				} catch (Exception e) {
					// do nothing
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void upisiStavke() {
		try {
			Main.pr = new PrintWriter(new FileWriter("." + Main.separator + "stavkeCenovnika.txt"));
			String linijaZaUpis = null;
			for (StavkaCenovnika sc : Main.listaStavki) {
				linijaZaUpis = String.format("%s|%s|%s|%s|%s|%s", sc.getDnevniBoravak(), sc.getNocenje(),
						sc.getVikendPoskupljenje(), sc.getTipSobe().getNazivSobe(), sc.getTipSobe().getBrojKreveta(),
						sc.getAktivnost());
				Main.pr.append(linijaZaUpis);
				Main.pr.append("\n");
				Main.pr.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void dodajStavku() {
		Main.sc = new Scanner(System.in);
		System.out.print("Unesite tip sobe: ");
		TipSobeEnum tpE = Utility.unesiEnum(Main.sc);
		TipSobe tp = new TipSobe(tpE.name(), tpE.ordinal() + 1);
		System.out.print("Unesite cenu dnevnog boravka: ");
		double cenaDnevni = Utility.unesiDouble(Main.sc);
		System.out.print("Unesite cenu nocenja: ");
		double cenaNocenje = Utility.unesiDouble(Main.sc);
		System.out.print("Unesite vikend poskupljenje u procentima: ");
		double vikendPoskupljenje = Utility.unesiDouble(Main.sc);
		StavkaCenovnika sc = new StavkaCenovnika(cenaDnevni, cenaNocenje, vikendPoskupljenje, tp, true);
		Main.listaStavki.add(sc);
		Main.sc.close();

		ArrayList<StavkaCenovnika> dodateStavke = new ArrayList<StavkaCenovnika>();
		if (((Menadzer) Main.prijavljenKornik).getDodateStavke() != null) {
			dodateStavke.addAll(((Menadzer) Main.prijavljenKornik).getDodateStavke());
			dodateStavke.add(sc);
			((Menadzer) Main.prijavljenKornik).setDodateStavke(dodateStavke);
		} else {
			dodateStavke.add(sc);
			((Menadzer) Main.prijavljenKornik).setDodateStavke(dodateStavke);
		}

	}

	public static void izmenaStavke() {
		Main.sc = new Scanner(System.in);
		System.out.print("Unesite naziv tipa sobe: ");
		TipSobeEnum tpE = Utility.unesiEnum(Main.sc);
		for (StavkaCenovnika sc : Main.listaStavki) {
			StavkaCenovnika staraStavka = sc;
			if (sc.getTipSobe().getNazivSobe().equalsIgnoreCase(tpE.name())) {
				System.out.print("Unesite novu cenu dnevnog boravka: ");
				double cenaDnevni = Utility.unesiDouble(Main.sc);
				System.out.print("Unesite novu cenu nocenja: ");
				double cenaNocenje = Utility.unesiDouble(Main.sc);
				System.out.print("Unesite vikend poskupljenje u procentima: ");
				double vikendPoskupljenje = Utility.unesiDouble(Main.sc);
				sc.setDnevniBoravak(cenaDnevni);
				sc.setNocenje(cenaNocenje);
				sc.setVikendPoskupljenje(vikendPoskupljenje);
				ArrayList<StavkaCenovnika> dodateStavke = new ArrayList<StavkaCenovnika>();
				if (((Menadzer) Main.prijavljenKornik).getDodateStavke() != null) {
					dodateStavke.addAll(((Menadzer) Main.prijavljenKornik).getDodateStavke());
					dodateStavke.add(sc);
					((Menadzer) Main.prijavljenKornik).setDodateStavke(dodateStavke);
				} else {
					dodateStavke.add(sc);
					((Menadzer) Main.prijavljenKornik).setDodateStavke(dodateStavke);
				}

				break;
			}
		}
	}
}
