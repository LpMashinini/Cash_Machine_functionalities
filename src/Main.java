import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


        pinAuth auth = new pinAuth();
        cashMachineProcces machineProcces = new cashMachineProcces();

        String pin = "2026";

        // hashing user pin
        String storedHashedPin = auth.hashPin(pin);

        Scanner userPin = new Scanner(System.in);
        System.out.println("Enter pin: ");

        try {

            //Verify user pin by comparing the stored hashed pin with provided user pin
            boolean verifyPin = auth.verifyPin(userPin.nextLine(), storedHashedPin);

            // checks if the provided pin is correct
            if (verifyPin) {

                machineProcces.process();

                Scanner userSelectInput = new Scanner(System.in);
                System.out.println("Select your option : ");

                int userSelect = userSelectInput.nextInt();

                if (userSelect == 1){

                    System.out.println("Enter an amount to withdraw: ");

                    Scanner userAmount = new Scanner(System.in);
                    int amount  = userAmount.nextInt(); // user inserted amount to withdraw

                    machineProcces.withdrawAmount(amount);
                } else if (userSelect == 2) {
                    machineProcces.getAvailBalance();
                } else if (userSelect == 3) {
                    //deposit
                    Scanner userDeposit = new Scanner(System.in);
                    System.out.println("Enter amount to deposit : ");
                    double deposit = userDeposit.nextDouble();

                    machineProcces.deposit(deposit);
                }

            } else {
                System.out.println("incorrect pin. try again!");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}