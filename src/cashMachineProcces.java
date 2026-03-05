public class cashMachineProcces {

    double availBalance = 5000;
    int cashLimit = 2500;
    int cash;

    public void process() {

        System.out.println("1) Withdraw");
        System.out.println("2) Balance");
        System.out.println("3) Deposit");
        System.out.println("4) Transfer");
        System.out.println("5) Return Card");

    }

    public double withdrawAmount(double userWithdraw){

        double balance = 0;

        if(userWithdraw > availBalance){
            System.out.println("Amount exceed available amount");
        } else if (userWithdraw > cashLimit) {
            System.out.println("Amount exceed limit");
        } else {
            balance  =  availBalance - userWithdraw;
            System.out.println("Available balance: " + balance);

        }

        return balance;
    }


    public double getAvailBalance(){

        System.out.println("Available Balance : " + availBalance);
        return  availBalance;
    }

    public double deposit(double userDeposit){

        double new_availableBalance = availBalance + userDeposit;
        System.out.println("Previous balance : " + availBalance);
        System.out.println("New available balance : " + new_availableBalance);

        return new_availableBalance;
    }

    public double transfer(double amount) {

        System.out.println("Transfer made successfully");
        availBalance = availBalance + amount;
        System.out.println("Available balance : " + availBalance);

        return 0;
    }



}
