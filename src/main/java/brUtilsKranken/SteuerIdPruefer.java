package brUtilsKranken;

public class SteuerIdPruefer {
	
		public static Integer pruefeSteuerid(String steuerid) {

		//In den ersten zehn Ziffern der Identifikationsnummer sind eine Ziffer genau zweifach und eine andere Ziffer gar nicht enthalten 
		//(ab 2016 ist auch eine dreifache vorkommende Ziffer möglich, entsprechend dann zwei Ziffern gar nicht), 
		//die anderen acht (ab 2016: auch sieben) Ziffern jeweils genau einfach. Die erste Ziffer darf nicht die 0 sein
		// ok-Nummern (return) 0: 12345678000, 12345670005
		// nok (return 1 - falsche Länge): 123456780002
		// nok (return 2 - falsche Prüfziffer): 12345671000
		// nok (return 3 - zuviele doppelte oder fehlende Ziiffern): 12345670001 (1 und 0 doppelt)
		if (steuerid.length() != 11) {
			return 1; // falsche Länge
		}

		char checksum = checksum(steuerid);
		if (steuerid.charAt(10) != checksum) {
			return 2; // falsche Prüfziffer
		}
		if (!doubleMissing(steuerid)) {
			return 3; // zuviele doppelte oder fehlende Ziiffern
		}
		return 0; // ok

	}

	private static char checksum(final String tin) {
		int sum;
		int product = 10;
		for (int i = 0; i < 10; i++) {
			char c = tin.charAt(i);
			sum = ((c - '0') + product) % 10;
			if (sum == 0) {
				sum = 10;
			}
			product = (sum * 2) % 11;
		}
		int check = 11 - product;
		if (check == 10) {
			check = 0;
		}
		return (char) ('0' + check);
	}

	private static boolean doubleMissing(final String tin) {
		int array[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

		for (int i = 0; i < 10; i++) {
			char c = tin.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
			array[c - '0']++;
		}
		int anzahlFehlendeZiffern = 0;
		int anzahlDoppelterZiffern = 0;
		int anzahlDreifacherZiffern = 0;
		for (int i = 0; i < 10; i++) {
			switch (array[i]) {
				case 1:
					break;
				case 0:
					anzahlFehlendeZiffern++;
					if (anzahlFehlendeZiffern > 2) {
						return false;
					}
					break;
				case 2:
					anzahlDoppelterZiffern++;
					if (anzahlDoppelterZiffern > 1) {
						return false;
					}
					break;
				case 3:
					anzahlDreifacherZiffern++;
					break;
				default:
					return false;
			}
		}

		if (anzahlFehlendeZiffern == 2 && anzahlDreifacherZiffern != 1) {
			return false;
		}
		if (anzahlFehlendeZiffern == 1 && anzahlDoppelterZiffern != 1) {
			return false;
		}
		// if (!missing || !twice) return false;
		return true;
	}
	
}
