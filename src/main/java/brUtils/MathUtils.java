package brUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.pmw.tinylog.Logger;

public class MathUtils {
    

    public static BigDecimal saveDivision(BigDecimal bd1, BigDecimal bd2, int scale, RoundingMode rm) {
        Logger.info("saveDivision {} by {}", bd1, bd2);
        return bd1.divide(bd2, scale, rm);
    }

    public static boolean isZeroWithRespectToScale(BigDecimal number, int scale) {
        // cuts off all digits of number beginning with position scale and compare it then with ZERO
        return number.setScale(scale, RoundingMode.DOWN).compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isAbsoluteValueLessThan(BigDecimal delta, BigDecimal eps) {
        return delta.abs().compareTo(eps) == -1;
    }

    public static boolean isWholeNumber(BigDecimal bd) {
        return (bd.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO)) == 0;
    }

}
