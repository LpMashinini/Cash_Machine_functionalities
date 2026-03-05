import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        //Object creation
        pinAuth auth = new pinAuth();
        cashMachineProcces machineProcces = new cashMachineProcces();

        String pin = "2026"; // user pin

        // hashing user pin
        String storedHashedPin = auth.hashPin(pin);

        Scanner userPin = new Scanner(System.in);
        System.out.println("Enter pin: ");

        try {

            //Verify user pin by comparing the stored hashed pin with provided user pin
            boolean verifyPin = auth.verifyPin(userPin.nextLine(), storedHashedPin);

            // checks if the provided pin is correct
            if (verifyPin) {

                machineProcces.process(); // calling the process method to display options

                Scanner userSelectInput = new Scanner(System.in);
                System.out.println("Select your option : ");

                int userSelect = userSelectInput.nextInt(); // stores user selected option

                if (userSelect == 1) {
                    //checks if user selected the withdrawal option

                    Scanner userAmount = new Scanner(System.in);

                    System.out.println("Enter an amount to withdraw: ");
                    int amount = userAmount.nextInt(); // stores user inserted amount to withdraw

                    machineProcces.withdrawAmount(amount); // pass the amount as an argument to the method

                } else if (userSelect == 2) {
                    //checks if user selected the Balance option

                    machineProcces.getAvailBalance(); // calls the method to get available balance
                } else if (userSelect == 3) {
                    //checks if user selected the deposit option

                    Scanner userDeposit = new Scanner(System.in);

                    System.out.println("Enter amount to deposit : ");
                    double deposit = userDeposit.nextDouble(); // stores the user deposit amount

                    machineProcces.deposit(deposit); // Calls the deposit method and pass the deposit amount as a parameter

                } else if (userSelect == 4) {

                    Scanner userTransfer = new Scanner(System.in);

                    System.out.println("Enter amount to transfer : ");
                    double transfer = userTransfer.nextDouble();

                    machineProcces.transfer(transfer);
                } else if (userSelect == 5) {
                    machineProcces.returnCard();
                }

            } else {
                System.out.println("incorrect pin. try again!");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}