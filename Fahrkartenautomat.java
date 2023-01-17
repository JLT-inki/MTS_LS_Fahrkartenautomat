import java.util.Scanner;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;


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
		boolean ersterDurchlauf = true;

		// Aufgabe 4.7 - Menu zur Auswahl von Fahrkartentypen um wiederholte Eingabe erweitert

		double preisAllerTickets = 0.0;
		boolean auswahlBeenden = false;
		
		
		HashMap<String, Double> Fahrkarten = new LinkedHashMap<String, Double>();
		Fahrkarten.put("Einzelfahrschein Berlin AB", 2.90);
		Fahrkarten.put("Einzelfahrschein Berlin BC[3,30€]", 3.30);
		Fahrkarten.put("Einzelfahrschein Berlin ABC [3,60€]", 3.60);
		Fahrkarten.put("Kurzstrecke [1,90€]", 1.90);
		Fahrkarten.put("Tageskarte Berlin AB [8,60€]", 8.60);
		Fahrkarten.put("Tageskarte Berlin BC [9,00€]", 9.00);
		Fahrkarten.put("Tageskarte Berlin ABC [9,60€]", 9.60);
		Fahrkarten.put("Kleingruppen-Tageskarte AB [23,50€]", 23.50);
		Fahrkarten.put("Kleingruppen-Tageskarte BC [24,30€]", 24.30);
		Fahrkarten.put("Kleingruppen-Tageskarte ABC [24,90€]", 24.90);
		
		
		HashMap<Integer, String> Tickets = new LinkedHashMap<Integer, String>(); 
		Tickets.put(1, "Einzelfahrschein Berlin AB");
		Tickets.put(2, "Einzelfahrschein Berlin BC[3,30€]");
		Tickets.put(3, "Einzelfahrschein Berlin ABC [3,60€]");
		Tickets.put(4, "Kurzstrecke [1,90€]");
		Tickets.put(5, "Tageskarte Berlin AB [8,60€]");
		Tickets.put(6, "Tageskarte Berlin BC [9,00€]");
		Tickets.put(7, "Tageskarte Berlin ABC [9,60€]");
		Tickets.put(8, "Kleingruppen-Tageskarte AB [23,50€]");
		Tickets.put(9, "Kleingruppen-Tageskarte BC [24,30€]");
		Tickets.put(10, "Kleingruppen-Tageskarte ABC [24,90€]");
		
		while (true) {

			int auswahlTickets = 0;
			boolean korrekteEingabe = false;
			
			// Menü mit den vefügbaren Fahrkarten
			if(ersterDurchlauf == true)
			{
				LocalDate date = LocalDate.now();
				DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
				System.out.println("Herzlich Willkommen\t\t" + date.format(df)+ "\n\nFahrkartenbestellvorgang:");
				ersterDurchlauf = false;
			}
			System.out.println("=========================");
				
			for(Entry<String, Double> e :Fahrkarten.entrySet())
			{
				System.out.println("\t" + e.getKey() + " = " + e.getValue());
			}
			System.out.println("\tAuswahl beenden (11)");
			

			while (korrekteEingabe == false) {
				System.out.print("Ihre Wahl: ");
				auswahlTickets = tastatur.nextInt();
				if (auswahlTickets >= 1 && auswahlTickets < 11) {
					korrekteEingabe = true;
				} 
				else if (auswahlTickets == 11) {
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
				ticketPreis = Fahrkarten.get(Tickets.get(auswahlTickets));
			}

			korrekteEingabe = false;
			anzahlTickets = 0;

			while (korrekteEingabe == false) {
				System.out.println("Sie haben sich entschieden für: " + Tickets.get(auswahlTickets));
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