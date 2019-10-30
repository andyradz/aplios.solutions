package com.codigo.aplios.calculator.core;

/**
 * @author Michal Nowinski
 * @sience 2016-11-16
 */
public class Model {

    /**
     * Oblicza dwie liczby
     *
     * @param number1 pierwsza przekzana liczba
     * @param number2 druga przekzana liczba
     * @param operator operator matematyczny
     * @return zwraca wynik rownania
     */
    public double calculate(final double number1, final double number2, final String operator) {

        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                if (number2 == 0) {
                    return 0;
                }
                return number1 * number2;
            case "/":
                if (number2 == 0) {
                    return 0;
                }
                return number1 / number2;
            case "%":
                return number1 % number2;
        }

        System.out.println("Nieznany operator - " + operator);
        return 0;
    }

    /**
     * Zmienia wartosc jednej liczby
     *
     * @param number1 liczba do przeksztalcenia
     * @param operator operator matematyczny
     * @return zwraca wynik
     * @throws CalculationError blad obliczen matematycznych
     */
    public double calculate(final double number1, final String operator) throws CalculationError {

        switch (operator) {
            case "%":
                return number1 / 100;
            case "+/-":
                return -number1;
            case "sqrt":
                if (number1 < 0) {
                    throw (new CalculationError(
                            "Nie mozna wyciagac pierwiastka kwadratowego z liczb ujemnych"));
                }
                return Math.sqrt(number1);
            case "1/x":
                return 1 / number1;
        }

        System.out.println("Nieznany operator - " + operator);
        return 0;
    }

}

class CalculationError extends Exception {

    private static final long serialVersionUID = -4476403173017746776L;

    public CalculationError() {

        super();
    }

    public CalculationError(final String s) {

        super(
                s);
    }
}
