public class cashMachineProcces {

    int balance = 50000;
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

        int cash  = userWithdraw - balance;

        System.out.println("Available balance: " + balance);

        return cash;
    }

}
