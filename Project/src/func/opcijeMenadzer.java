package func;

import java.util.Scanner;

import Utility.Utility;
import mein.Main;

public class opcijeMenadzer {

	public static void interakcijaSaKorisnikomMenadzer(Scanner sc1) {
		boolean zavrsi = false;
		while (zavrsi == false) {
			
			System.out.println("1----> Unos korisnika" + "\n2----> Izmena korisnika" + "\n3----> Brisanje korisnika"
					+ "\n4----> Pretraga korisnika" + "\n5----> Unos sobe" + "\n6----> Izmena sobe "
					+ "\n7----> Brisanje sobe" + "\n8----> Pretraga sobe" + "\n9----> Unos stavke cenovnika"
					+ "\n10---> Izmena stavke cenovnika" + "\n11---> Pregled stanja" + "\n12---> Aktuelna iznajmljivanja" + "\n0-----> Izlaz");

			System.out.print("Unesite komandu: ");
			int komanda = Utility.unesiInt(sc1);
			switch (komanda) {
			case 1:
				func.funcKorisnik.unosKorisnika();
				func.funcKorisnik.upisKorisnika();
				break;
			case 2:
				func.funcKorisnik.izmenaKorisnika();
				func.funcKorisnik.upisKorisnika();
				break;
			case 3:
				func.funcKorisnik.brisanjeKorisnika();
				func.funcKorisnik.upisKorisnika();
				break;
			case 4:
				func.funcKorisnik.pregledIzvestaja();
				break;
			case 5:
				func.funcSoba.unosSobe();
				func.funcSoba.upisiSobe();
				break;
			case 6:
				func.funcSoba.izmenaSobe();
				func.funcSoba.upisiSobe();
				break;
			case 7:
				func.funcSoba.brisanjeSobe();
				func.funcSoba.upisiSobe();
				break;
			case 8 :
				func.funcSoba.pregledSoba();
				break;
			case 9:
				func.funcStavkaCenovnika.dodajStavku();
				func.funcStavkaCenovnika.upisiStavke();
				break;
			case 10:
				func.funcStavkaCenovnika.izmenaStavke();
				func.funcStavkaCenovnika.upisiStavke();
				break;
			case 11:
				func.funcKorisnik.pregledIzvestaja();
				break;
			case 12:
				func.funcIznajmljivanje.prikazAktuelnih(Main.listaIznajmljivanja);
				func.funcIznajmljivanje.prikazAktuelnih(Main.listaIznajmljivanja);
				break;
			case 0:
				sc1.close();
				zavrsi = true;
				break;
			}

		}
	}

}
