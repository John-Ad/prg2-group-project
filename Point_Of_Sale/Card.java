package Point_Of_Sale;

import java.util.Scanner;

public class Card {
    private String pin;     //pin is stored as a string to allow pins to start with zero or multiple zeros

    public Card() {
        this.pin = "";
    }

    public Card(String pin) {
        try{
            Integer.parseInt(pin);
            this.pin=pin;
            System.out.println(this.pin);
        } catch (Exception ex) {
            this.pin = "";
        }
    }

    public void setPin() {
        Scanner scanner = ScannerAccess.getScanner();
        String res;
        while (true) {
            try {
                System.out.println("Enter new pin: ");
                res = scanner.nextLine(); // pin is read as string
                Integer.parseInt(res); //check if an int can be parsed from enterd pin
                                       //if it cant, an exception will be thrown. if it can then the pin is a valid number
                break; //end loop
            } catch (Exception err) {
                System.out.println("Invalid pin!");
            }
        }
        this.pin = res;
        //System.out.println(this.pin);     //debugging
    }

    public boolean checkPin() {
        Scanner scanner = ScannerAccess.getScanner();
        String res;
        int tries = 3;
        for (int i = 2; i >= 0; i--) {   
            try {
                System.out.println("Enter pin: ");
                res = scanner.nextLine();
                Integer.parseInt(res);
                if (res.compareTo(this.pin) == 0) {
                    return true;
                } else {
                    System.out.println("wrong pin: ");
                }
            } catch (Exception err) {
                System.out.println("Invalid pin!");
            }
            System.out.format("number of attempts: %s\n", i);
        }
        return false;
    }
}
