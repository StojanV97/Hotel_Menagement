package func;

import java.util.Scanner;

import Utility.Utility;
import mein.Main;

public class opcijeRecepcioner {



	public static void interakcijaRecep(Scanner sc) {


		boolean zavrsi = false;
		while (zavrsi == false) {
			System.out.println(
					"1----> Unos Iznajmljivanja" + "\n2----> Izmena Iznajmljivanja" + "\n3----> Pretraga Iznajmljivanja"
							+ "\n4----> Brisanje Iznajmljivanja" + "\n5----> Izdavanja racuna "
							+ "\n6----> Brisanje racuna " + "\n7----> Pregled racuna" + "\n0----> Izlaz");

			System.out.print("Unesite komandu: ");
			int komanda = Utility.unesiInt(sc);
			switch (komanda) {
			case 1:
				func.funcIznajmljivanje.unosIznajmljivanja();
				func.funcIznajmljivanje.upisIznajmljivanje();
				break;
			case 2:
				func.funcIznajmljivanje.izmenaIznajmljivanja();
				func.funcIznajmljivanje.upisIznajmljivanje();
				break;
			case 3:
				func.funcIznajmljivanje.pretragaIznajmljivanja();
				func.funcIznajmljivanje.upisIznajmljivanje();
				break;
			case 4:
				func.funcIznajmljivanje.brisanjeIznajmljivanja();
				func.funcIznajmljivanje.upisIznajmljivanje();
				break;
			case 5:
				func.funRacun.izdajRacun();
				func.funRacun.upisiRacun();
				break;
			case 6:
				func.funRacun.obrisiRacun();
				func.funRacun.upisiRacun();
				break;
			case 7:
				func.funRacun.pregledRacuna();
				zavrsi = true;
				break;
			case 0:
				zavrsi = true;
			
				break;
			}

		}
	
		
	}
}
