package util;

import java.util.Scanner;

public class InputUtil {
    private final Scanner sc;

    // Constructor met dependency injection van Scanner
    public InputUtil(Scanner sc) {
        this.sc = sc;
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer, geef een geldig getal.");
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer, geef een geldig getal.");
            }
        }
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
