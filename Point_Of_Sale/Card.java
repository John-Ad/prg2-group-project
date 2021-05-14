package Point_Of_Sale;

import java.io.Serializable;
import java.util.Scanner;

public class Card implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String pin;     //pin is stored as a string to allow pins to start with zero or multiple zeros

    public Card() {
        this.pin = "";
    }

    public Card(String pin) {
        if(NumberConversion.toInt(pin) != NumberConversion.ERROR){
            this.pin = pin;
        } else {
            this.pin = "";
        }
    }

    public void setPin() {
        Scanner scanner = ScannerAccess.getScanner();
        String res;
        while (true) {
            System.out.println("Enter new pin: ");
            res = scanner.nextLine(); // pin is read as string
            if (NumberConversion.toInt(res) != NumberConversion.ERROR) {
                break;  //end loop
            } else {
                System.out.println("Invalid pin!");
            }
        }
        this.pin = res;
        //System.out.println(this.pin);     //debugging
    }

    public boolean checkPin() {
        Scanner scanner = ScannerAccess.getScanner();
        String res;
        for (int i = 2; i >= 0; i--) {   
            System.out.println("Enter pin: ");
            res = scanner.nextLine();
            if (NumberConversion.toInt(res) != NumberConversion.ERROR) {
                if (res.compareTo(this.pin) == 0) {
                    return true;
                } else {
                    System.out.println("wrong pin: ");
                }
            } else {
                System.out.println("Invalid pin entered!");
            }
            System.out.format("number of attempts: %s\n", i);
        }
        return false;
    }
}
