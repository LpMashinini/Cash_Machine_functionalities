import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


        pinAuth auth = new pinAuth();

        String pin = "2026";

        // hashing user pin
        String storedHashedPin = auth.hashPin(pin);

        Scanner userPin = new Scanner(System.in);
        System.out.println("Enter pin: ");

        try{

            //Verify user pin by comparing the stored hashed pin with provided user pin
            boolean verifyPin = auth.verifyPin(userPin.nextLine(),storedHashedPin);

            // checks if the provided pin is correct
            if (verifyPin){
                System.out.println("Pin correct, welcome.");
            } else {
                System.out.println("incorrect pin. try again!");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }





    }
}