import java.util.Scanner;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;

import java.util.HashMap;
import java.util.LinkedHashMap;


class Fahrkartenautomat {

	/**
	 * Erfasst die Bestellung
	 * 
	 * @return Gesamter Ticketpreis
	 */
	public static double fahrkartenbestellungErfassen() {
		int anzahlTickets;
		double ticketPreis = 0.0;
		Scanner tastatur = new Scanner(System.in);

		// Aufgabe 4.7 - Menu zur Auswahl von Fahrkartentypen um wiederholte Eingabe erweitert

		double preisAllerTickets = 0.0;
		boolean auswahlBeenden = false;
		
		double[] fahrkartenPreise = new double[] {2.9, 3.3, 3.6, 1.9, 8.6, 9, 9.6, 23.5, 24.3, 24.9};
		String[] fahrkartenBezeichnung = new String[] {"Einzelfahrschein Berlin AB [2,90€]", "Einzelfahrschein Berlin BC[3,30€]",
														"Einzelfahrschein Berlin ABC [3,60€]", "Kurzstrecke [1,90€]",
														"Tageskarte Berlin AB [8,60€]", "Tageskarte Berlin BC [9,00€]",
														"Tageskarte Berlin ABC [9,60€]", "Kleingruppen-Tageskarte AB [23,50€]",
														"Kleingruppen-Tageskarte BC [24,30€]", "Kleingruppen-Tageskarte ABC [24,90€]"};
		while (true) {

			int auswahlTickets = 0;
			boolean korrekteEingabe = false;
			
			// Menü mit den vefügbaren Fahrkarten
			LocalDate date = LocalDate.now();
			DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
			System.out.println("Herzlich Willkommen\t\t" + date.format(df)+ "\n\nFahrkartenbestellvorgang:\n=========================");
			for(int i = 0; i < fahrkartenBezeichnung.length; i++) {
				System.out.println("\t" + fahrkartenBezeichnung[i] + "(" + (i+1) + ")"); 
						 
			}
			System.out.println("\tAuswahl beenden (" + (fahrkartenBezeichnung.length + 1) + ")");
			

			while (korrekteEingabe == false) {
				System.out.print("Ihre Wahl: ");
				auswahlTickets = tastatur.nextInt();
				if (auswahlTickets >= 1 && auswahlTickets <= fahrkartenBezeichnung.length) {
					korrekteEingabe = true;
				} 
				else if (auswahlTickets == fahrkartenBezeichnung.length + 1) {
					korrekteEingabe = true;
					auswahlBeenden = true;
				} 
				else {
					System.out.println(" >>falsche Eingabe<< ");
				}
			}

			if (auswahlBeenden) {
				break;
			}
			else {
				ticketPreis = fahrkartenPreise[auswahlTickets - 1];
			}

			korrekteEingabe = false;
			anzahlTickets = 0;

			while (korrekteEingabe == false) {
				System.out.println("Sie haben sich entschieden für: " + fahrkartenBezeichnung[auswahlTickets -1]);
				System.out.print("Anzahl der Tickets: ");
				anzahlTickets = tastatur.nextInt();

				if (anzahlTickets >= 1 && anzahlTickets <= 10) { // Nur Eingaben von 1 bis 10 sind erlaubt
					korrekteEingabe = true;
				} else {
					System.out.println(" >> Wählen Sie bitte eine Anzahl von 1 bis 10 Tickets aus.\n");
				}

			}

			preisAllerTickets = preisAllerTickets + ticketPreis * anzahlTickets;

			System.out.format("%nZwischensumme: %4.2f € %n%n", preisAllerTickets);

		}

		return preisAllerTickets;
	}

	/**
	 * Erhält den zu zahlenden Betrag und fragt den User ab, bis er den Betrag
	 * bezahlt hat
	 * 
	 * @param zuZahlenderBetrag, Betrag der zuzahlen ist
	 * @return Rückgeld, wie viel überbezahlt wurde
	 */
	public static double fahrkartenBezahlen(double zuZahlenderBetrag) {
		double eingeworfenemuenze;
		double eingezahlterGesamtbetrag = 0.0;
		Scanner tastatur = new Scanner(System.in);

		while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
			System.out.format("Noch zu zahlen: %4.2f € %n", (zuZahlenderBetrag - eingezahlterGesamtbetrag));
			System.out.print("Eingabe (mind. 5Ct, höchstens 2 Euro): ");
			eingeworfenemuenze = tastatur.nextDouble();
			eingezahlterGesamtbetrag += eingeworfenemuenze;
		}
		return eingezahlterGesamtbetrag - zuZahlenderBetrag;
	}

	/**
	 * Gibt animiert die Meldung aus, das bezahlt wurde
	 */
	public static void fahrkartenAusgeben() {
		System.out.println("\nFahrschein wird ausgegeben");
		for (int i = 0; i < 8; i++) {
			System.out.print("=");
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("\n\n");
	}

	/**
	 * Erhält dem Rückgabebetrag und zahlt diesen in passenden Münzen aus
	 */
	public static void rueckgeldAusgeben(double rueckgabebetrag) {

		if (rueckgabebetrag > 0.0) {
			System.out.format("Der Rückgabebetrag in Höhe von %4.2f € %n", rueckgabebetrag);
			System.out.println("wird in folgenden Münzen ausgezahlt:");

			while (rueckgabebetrag >= 2.0) {// 2 EURO-muenzen
				System.out.println("   ***\n *     *\n*   2   *\n*  Euro *\n *     *\n   ***");
				rueckgabebetrag -= 2.0;
			}
			while (rueckgabebetrag >= 1.0) {// 1 EURO-muenzen
				System.out.println("   ***\n *     *\n*   1   *\n*  Euro *\n *     *\n   ***");
				rueckgabebetrag -= 1.0;
			}
			while (rueckgabebetrag >= 0.5) // 50 CENT-muenzen
			{
				System.out.println("   ***\n *     *\n*  50   *\n*  Cent *\n *     *\n   ***");
				rueckgabebetrag -= 0.5;
			}
			while (rueckgabebetrag >= 0.2) // 20 CENT-muenzen
			{
				System.out.println("   ***\n *     *\n*  20   *\n*  Cent *\n *     *\n   ***");
				rueckgabebetrag -= 0.2;
			}
			while (rueckgabebetrag >= 0.1) // 10 CENT-muenzen
			{
				System.out.println("   ***\n *     *\n*  10   *\n*  Cent *\n *     *\n   ***");
				rueckgabebetrag -= 0.1;
			}
			while (rueckgabebetrag >= 0.05)// 5 CENT-muenzen
			{
				System.out.println("   ***\n *     *\n*   5   *\n*  Cent *\n *     *\n   ***");
				rueckgabebetrag -= 0.05;
			}
		}
	}

	/**
	 * Main Methode, die alles aufruft
	 * 
	 * @param args, Startargumente, werden ignoriert
	 */
	public static void main(String[] args) {

		double zuZahlenderBetrag;
		double rueckgabebetrag;

		zuZahlenderBetrag = fahrkartenbestellungErfassen();
		rueckgabebetrag = fahrkartenBezahlen(zuZahlenderBetrag);
		fahrkartenAusgeben();
		rueckgeldAusgeben(rueckgabebetrag);

		System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
				+ "Wir wünschen Ihnen eine gute Fahrt.");
	}

}