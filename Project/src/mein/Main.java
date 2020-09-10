package mein;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Osobe.Korisnik;
import Osobe.Menadzer;
import Osobe.Recepcioner;
import Utility.Utility;
import func.funRacun;
import func.funcIznajmljivanje;
import func.funcKorisnik;
import func.funcSoba;
import func.opcijeMenadzer;
import func.opcijeRecepcioner;
import klase.Iznajmljivanje;
import klase.Racun;
import klase.Soba;
import klase.StavkaCenovnika;
import klase.TipSobe;
import klase.TipSobeEnum;

public class Main {

	public static BufferedReader bf;
	public static Scanner sc;
	public static PrintWriter pr;
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH.mm");
	public static Korisnik prijavljenKornik;
	public static final String separator = System.getProperty("file.separator");
	public static ArrayList<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
	public static ArrayList<Soba> listaSoba = new ArrayList<Soba>();
	public static ArrayList<Iznajmljivanje> listaIznajmljivanja = new ArrayList<Iznajmljivanje>();
	public static ArrayList<Racun> listaRacuna = new ArrayList<Racun>();
	public static ArrayList<StavkaCenovnika> listaStavki = new ArrayList<StavkaCenovnika>();

	public static void main(String[] args) {
		func.funcSoba.ucitajSobe();
		func.funcIznajmljivanje.ucitajIznajmljivanje();
		func.funcKorisnik.ucitajKorisnika();
		func.funcStavkaCenovnika.ucitajStavke();

		boolean zavrsio = false;
		sc = new Scanner(System.in);
		while (zavrsio == false) {		
				System.out.println("Prijava korisnika....");
				func.funcKorisnik.prijava();
				if (prijavljenKornik instanceof Menadzer) {
					opcijeMenadzer.interakcijaSaKorisnikomMenadzer(sc);
					System.out.println("Izlazak iz programa(da/ne)");
					boolean odluka = Utility.unesiOdluku(sc);
					if (odluka) {
						zavrsio = true;
					}

				} else if (prijavljenKornik instanceof Recepcioner) {
					opcijeRecepcioner.interakcijaRecep(sc);
					System.out.println("Izlazak iz programa(da/ne)");
					boolean odluka2 = Utility.unesiOdluku(sc);
					if (odluka2) {
						sc.close();
						zavrsio = true;
					}
				}

			
			
		}

	}

}
