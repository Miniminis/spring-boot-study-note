public class Calculator {

    private ICalculator iCalculator;

    public Calculator(ICalculator iCalculator) {
        this.iCalculator = iCalculator;
    }

    public int plus (int x, int y) {
        return iCalculator.plus(x, y);
    }

    public int minus(int x, int y) {
        return iCalculator.minus(x, y);
    }

}
