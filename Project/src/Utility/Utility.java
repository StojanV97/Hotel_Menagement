package Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import klase.TipSobe;
import klase.TipSobeEnum;
import mein.Main;

public class Utility {
	static Scanner sc;

	public static String unesiTekst(Scanner sc) {
		boolean flag = false;
		String tekst = null;
		while (flag == false) {
			tekst = sc.nextLine();
			if (tekst.isEmpty()) {
				System.out.println("Unesite Ponovo: ");
				flag = false;
			} else {
				flag = true;
			}
		}
		return tekst;

	}

	public static Integer unesiInt(Scanner sc) {
		int unos = 0;
		boolean ocitan = false;
		while (ocitan != true) {
			try {
				unos = sc.nextInt();
				ocitan = true;
			} catch (Exception e) {
				System.out.print("Uneli ste pogresno\n --- Pokusajte ponovo: ");
			}
			sc.nextLine();
		}
		return unos;

	}

	public static Double unesiDouble(Scanner sc) {
		double unos = 0;
		boolean ocitan = false;
		while (ocitan != true) {
			try {
				unos = sc.nextDouble();
				ocitan = true;

			} catch (Exception e) {
				System.err.print("Uneli ste pogresno --- Pokusajte ponovo:\n");

			}
			sc.nextLine();
		}
		return unos;
	}

	public static float unesiFloat(Scanner sc) {
		float unos = 0;
		boolean ocitan = false;
		while (ocitan != true) {
			try {
				unos = sc.nextInt();
				ocitan = true;
			} catch (Exception e) {
				System.err.print("Uneli ste pogresno --- Pokusajte ponovo:\n");
			}
			sc.nextLine();
		}
		return unos;

	}

	public static Date unesiDatumVK(Scanner sc) {
		boolean ocitan = false;
		Date date = null;
		Date trenutni = new Date();
		while (ocitan != true) {
			try {
				date = Main.sdf.parse(unesiTekst(sc));
				if(date.before(trenutni)) {
					System.out.println("Nevalidan datum");
				}else {
					ocitan = true;
				}
			} catch (ParseException e) {
				// e.printStackTrace();
				System.err.println("Unesite ponovo: ");
			}

		}
		return date;
	}

	public static Date unesiVreme(Scanner sc) {
		boolean ocitan = false;
		Date date = null;
		while (ocitan != true) {
			try {
				date = Main.sdf.parse(unesiTekst(sc));
				ocitan = true;
			} catch (ParseException e) {
				// e.printStackTrace();
				System.err.println("Unesite ponovo: ");
			}

		}
		return date;
	}

	public static boolean unesiOdluku(Scanner scan) {
		String odluka = "ne";
		boolean notRead = true;
		boolean flag = false;
		do {
			odluka = Utility.unesiTekst(scan);
			if (odluka.equalsIgnoreCase("da") || odluka.equalsIgnoreCase("ne")) {
				notRead = false;
			}else {
				System.out.print("Unesite ponovo!!! : ");
			}
		} while (notRead);

		if (odluka.equalsIgnoreCase("da")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	
	public static boolean unosStanjaSobe(Scanner scan) {
		String odluka = "ne";
		boolean notRead = true;
		boolean flag = false;
		do {
			odluka = Utility.unesiTekst(scan);
			if (odluka.equalsIgnoreCase("slobodna") || odluka.equalsIgnoreCase("zauzeta")) {
				notRead = false;
			}else {
				System.out.print("Unesite ponovo!!! : ");
			}
		} while (notRead);

		if (odluka.equalsIgnoreCase("slobodna")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	public static boolean unosTVBAR(Scanner scan) {
		String odluka = "ne";
		boolean notRead = true;
		boolean flag = false;
		do {
			odluka = Utility.unesiTekst(scan);
			if (odluka.equalsIgnoreCase("sadrzi") || odluka.equalsIgnoreCase("ne sadrzi")) {
				notRead = false;
			}else {
				System.out.print("Unesite ponovo!!! : ");
			}
		} while (notRead);

		if (odluka.equalsIgnoreCase("sadrzi")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	public static TipSobeEnum unesiEnum(Scanner sc) {
		String enum1;
		boolean nadjen = false;
		TipSobeEnum tipSobe = null;
		while (nadjen == false) {
			try {
				System.out.println("Mogucnosti - SOBA,APARTMAN,PORODICNA_SOBA,PORODICNI_APARTMAN,SUITE : ");
				enum1 = sc.nextLine();
				tipSobe = TipSobeEnum.valueOf(enum1.toUpperCase());
				nadjen = true;
			} catch (IllegalArgumentException e) {
				System.err.print("Nepostojeci Enum\n Pokusajte ponovo: ");
			}

		}
		return tipSobe;
	}

}
