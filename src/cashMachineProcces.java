public class cashMachineProcces {

    int availBalance = 5000;
    int cashLimit = 2500;
    int cash;

    public void process() {

        System.out.println("1) Withdraw");
        System.out.println("2) Balance");
        System.out.println("3) Deposit");
        System.out.println("4) previous transactions");
        System.out.println("5) Transfer");
        System.out.println("4) Return Card");

    }

    public int withdrawAmount(int userWithdraw){

        int balance = 0;

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


    public int getAvailBalance(){

        System.out.println("Available Balance : " + availBalance);
        return  availBalance;
    }

    public double deposit(double userDeposit){

        double new_availableBalance = availBalance + userDeposit;
        System.out.println("Previous balance : " + availBalance);
        System.out.println("New available balance : " + new_availableBalance);

        return new_availableBalance;
    }



}
