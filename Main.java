import java.util.Random;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        // JAVA SLOT MACHINE

        int balance;
        int bet;
        int payout;
        String[] row;
        String playAgain;
        Scanner scanner = new Scanner(System.in);

        System.out.println("****************************");
        System.out.println("Welcome to java slot machine");
        System.out.println("Symbols :   🍇 🍉 🍎 📺 ⭐");
        System.out.println("****************************");
        System.out.print("Enter the balance you wnt to play with : ");
        balance = scanner.nextInt();
        scanner.nextLine();
        while(balance <= 0){
                System.out.println("betting money should be greater than zero");
                System.out.println("Enter your balance again : ");
                balance = scanner.nextInt();
                scanner.nextLine();
        }


        while(balance > 0) {
            System.out.println("Current balance : ₹ " + balance);
            System.out.print("Place your bet amount : ");
            bet = scanner.nextInt();
            scanner.nextLine();
            if (bet > balance) {
                System.out.println("INSUFFICIENT FUNDS");
                continue;
            } else if (bet <= 0) {
                System.out.println("Bet amount should be greater than zero");
                continue;
            } else {
                balance -= bet;
            }
            System.out.println("Spinning....");
            row = spinRow();
            printRow(row);
            payout = getPayout(row, bet);

            if(payout > 0){
                System.out.println("You Won!₹" + payout);
                balance += payout;
            }
            else{
                System.out.println("You lose this round");
            }
            System.out.print("Do you want to play again(yes/no) : ");
            playAgain = scanner.nextLine().toLowerCase();
            if(!playAgain.equals("yes")){
                break;
            }
        }
        System.out.println("Thank you for playing your total balance is ₹ " + balance);
        scanner.close();
    }
    static String[] spinRow(){
        String[] symbols = {"🍇", "🍉", "🍎", "📺", "⭐"};
        String[] row = new String[3];
        Random random  = new Random();
       for(int i = 0; i < 3; i++){
           row[i] = symbols[random.nextInt(symbols.length)];
       }
        return row;
    }
    static void printRow(String[] row){
        System.out.println("**************");
        System.out.println(" " + String.join(" | ", row));
        System.out.println("**************");
    }
    static int getPayout(String[] row, int bet){
        if(row[0].equals(row[1]) && row[1].equals(row[2])){
            return switch(row[0]){
              case"🍇" -> bet * 3;
              case"🍉" -> bet * 5;
              case"🍎" -> bet * 4;
              case"📺" -> bet * 10;
              case"⭐" -> bet * 20;
              default -> 0;
            };
        }
        else if(row[0].equals(row[1])){
            return switch(row[0]){
                case"🍇" -> bet * 2;
                case"🍉" -> bet * 3;
                case"🍎" -> bet * 4;
                case"📺" -> bet * 5;
                case"⭐" -> bet * 10;
                default -> 0;
            };
        }
        else if(row[1].equals(row[2])){
            return switch(row[1]){
                case"🍇" -> bet * 2;
                case"🍉" -> bet * 3;
                case"🍎" -> bet * 4;
                case"📺" -> bet * 5;
                case"⭐" -> bet * 10;
                default -> 0;
            };
        }
        return 0;
    }
}