package labor5.aufgabe1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        java.util.Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        String wahl = "-1";

       labor5.aufgabe1.Scanner scanner = new labor5.aufgabe1.Scanner();

        while( !wahl.equals("3")) {
            System.out.println();
            System.out.println("Wahle eine Option: \n1-NextChar \n2-RollBack \n3-Exit");
            System.out.print("Wahle Option: ");
            wahl = myScanner.nextLine();
            switch (wahl) {
                case "1":
                    System.out.println("NextChar() wird durchgefuhrt!");
                    System.out.println(scanner.nextText());
                    break;
                case "2":
                    System.out.println("RollBack() wird durchgefuhrt!");
                    System.out.println(scanner.rollText());
                    break;
                case "3":
                    System.out.println("App wurde geschlossen.");
                    break;
            }
        }
    }
}
