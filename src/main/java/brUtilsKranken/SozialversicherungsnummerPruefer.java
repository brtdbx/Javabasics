package brUtilsKranken;

import java.util.Calendar;
import org.apache.commons.lang3.StringUtils;

public class SozialversicherungsnummerPruefer {

	public static Integer pruefeSozialversicherungsnummer(String nummer, Calendar datum) {
		if (nummer == null || datum == null) {
			return 1;  // fehlende Daten: sv-nummer und/ oder geburtsdatum fehlen
		}
		if (!(nummer.length() == 12) || !nummer.matches("[0-9]{8}[A-Z][0-9]{3}")) {
			return 2;  // sv-nummer hat falsche länge oder falsche reg-ex-Syntax
		}
		String svnr = nummer.substring(2, 8);
		String gdat = CalendarUtil.getFormattetStringOhneTrennzeichen(datum, CalendarUtil.TTMMJJJJ);
		gdat = gdat.substring(0, 4) + gdat.substring(6, 8);
		if (!svnr.equals(gdat)) {
			return 2;  // sv-nummer enthält nicht das Geburtsdatum an entsprechender Steller als Subststring
		}
		if (Integer.parseInt(StringUtils.right(nummer, 1)) != checkPzSozialversNr(nummer)) {
			return 3;  // die Prüfziffer stimmt nicht
//			System.out.println("Prüfziffer sollte sein: " + tmp2);
		}
		return 0;  // alles korrekt
	}

	private static int checkPzSozialversNr(String pIn) {
		String overlay = String.valueOf(pIn.charAt(8) - 64);
		overlay = StringUtils.leftPad(overlay, 2, "0"); // man0020425
		overlay = StringUtils.overlay(StringUtils.left(pIn, 11), overlay, 8, 9);
		int suangebotrossSum = 0;
		char[] chars = overlay.toCharArray();
		int[] FACTORS_CROSS_SUM = {2, 1, 2, 5, 7, 1, 2, 1, 2, 1, 2, 1};
		for (int i = 0; i < FACTORS_CROSS_SUM.length; i++) {
			suangebotrossSum += calcCrossSum(Character.getNumericValue(chars[i]) * FACTORS_CROSS_SUM[i]);
		}
		return suangebotrossSum % 10;
	}

	private static int calcCrossSum(int pInt) {
		int ret = 0;
		char[] charArray = String.valueOf(pInt).toCharArray();
		for (char aCharArray : charArray) {
			ret += Character.getNumericValue(aCharArray);
		}
		return ret;
	}

}
