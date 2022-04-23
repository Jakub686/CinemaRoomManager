package cinema;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        int ticket = 0;
        int evenSecondHalf = 0;
        int seatNumber, rowNumber, choice;
        int ticketPurchasedNumber = 0;
        int ticketPurchased = 0;
        char[][] table = getTable(row, seats);
        NumberFormat formatter = new DecimalFormat("0.00");

        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            System.out.println();
            choice = sc.nextInt();


            if (choice == 1) {
                show(row, seats, table);
            }
            boolean choice2EndCondition = false;
             do {
                 choice:
                 if (choice == 2) {
                     System.out.println("Enter a row number:");
                     rowNumber = sc.nextInt();
                     System.out.println("Enter a seat number in that row:");
                     seatNumber = sc.nextInt();

                     if (rowNumber > row | seatNumber > seats) {
                         System.out.println();
                         System.out.println("Wrong input!");
                         System.out.println();
                         choice2EndCondition = true;
                         break choice;
                     }

                     if (table[rowNumber][seatNumber] == 'B') {
                         System.out.println("That ticket has already been purchased!");
                         choice2EndCondition = true;
                         break choice;
                     }else {choice2EndCondition = false;}

                     if(table[rowNumber][seatNumber] == 'S'){
                         ticketPurchased = ticketPurchased + buyTicket(row, ticket, seats, evenSecondHalf, rowNumber);
                         soldPlace(table, rowNumber, seatNumber);
                         ticketPurchasedNumber++;
                     }
                 }
             }while (choice2EndCondition);

            if (choice == 3) {
                System.out.print("Number of purchased tickets: ");
                System.out.println(ticketPurchasedNumber);
                System.out.print("Percentage: ");
                double a = ((double) ticketPurchasedNumber * 100 / (row * seats));
                System.out.println(formatter.format(a) + "%");
                System.out.print("Current income: $");
                System.out.println(ticketPurchased);
                System.out.print("Total income: $");
                System.out.print(TotalIncome(row, seats));
                System.out.println();
            }
        } while (!endCondition(choice));
    }

    private static void soldPlace(char[][] table, int rowNumber, int seatNumber) {
        table[rowNumber][seatNumber] = 'B';
    }

    private static int buyTicket(int row, int ticket, int seats, int evenSecondHalf, int rowNumber) {
        if (row % 2 != 0) {
            evenSecondHalf = row - (row / 2);
        }
        if (row * seats < 61) {
            ticket = 10;

        }
        if (row * seats > 60 & row % 2 == 0) {
            if (rowNumber < row / 2 + 1) {
                ticket = 10;

            }
        }
        if (row * seats > 60 & row % 2 != 0) {
            if (rowNumber >= evenSecondHalf) {
                ticket = 8;

            } else {
                ticket = 10;
            }
        }
        System.out.println();
        System.out.println("Ticket price: $" + ticket);
        return ticket;
    }

    private static void show(int row, int seats, char[][] table) {

        System.out.println("Cinema:");
        for (int i = 0; i < row + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static char[][] getTable(int row, int seats) {
        char[][] table = new char[row + 1][seats + 1];
        table[0][0] = ' ';
        for (int i = 1; i < seats + 1; i++) {
            table[0][i] = Character.forDigit(i, 10);
        }
        for (int i = 1; i < row + 1; i++) {
            table[i][0] = Character.forDigit(i, 10);
        }
        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < seats + 1; j++) {
                table[i][j] = 'S';
            }
        }
        return table;
    }

    private static int TotalIncome(int row, int seats) {
        int evenSecondHalf = 0;
        int totalIncome = 0;



        if (row % 2 != 0) {
            evenSecondHalf = row - (row / 2);
        }

        if (row * seats < 61) {
            totalIncome = 10 * row * seats;

        }

        if (row * seats > 60 & row % 2 == 0) {
            totalIncome = row / 2 * 10 * seats + row / 2 * 8 * seats;
        }

        if (row * seats > 60 & row % 2 != 0) {
            totalIncome = row / 2 * 10 * seats + evenSecondHalf * 8 * seats;

        }
        return totalIncome;
    }

    private static boolean endCondition(int choice) {
        boolean result;
        result = choice == 0;
        return result;
    }
}
