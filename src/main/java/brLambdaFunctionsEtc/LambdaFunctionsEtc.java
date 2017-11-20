package brLambdaFunctionsEtc;

import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

public class LambdaFunctionsEtc {

    public static double calcSomething(DoubleUnaryOperator func, double x1, double x2) {

        double y1 = func.applyAsDouble(x1);
        double y2 = func.applyAsDouble(x2);

        return (y2 - y1) / (x2 - x1);
    }

    public static double calcSomething2(DoubleFunction func, double x1, double x2) {

        double y1, y2;
        y1 = (Double) func.apply(x1);
        y2 = (Double) func.apply(x2);

        return (y2 - y1) / (x2 - x1);
    }

    public static DoubleUnaryOperator myFunction = Math::sin;
    public static DoubleFunction myFunction2 = (double x) -> Math.sin(x * x) * Math.cos(x * x);
    
    

    // Type: (f, x) -> F(f, x)
    public static double recursiveFunction(DoubleUnaryOperator f, double x) {

        if (x < 1) {
            return 0;
        } else {
            return x * x + f.applyAsDouble(x - 1);
        }

    }

    // Type: x -> f(x) 
    public static double someRekursion(double x) {

        if (x < 1) {
            return 0;
        } else {
            return x + someRekursion(x - 1);

        }

    }

    

}
