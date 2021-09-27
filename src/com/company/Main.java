package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        WaitingArea waitingArea = new WaitingArea();
        RiderGenerator riderGenerator = new RiderGenerator(waitingArea);
        SenateBusGenerator busGenerator = new SenateBusGenerator(waitingArea);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("\nPress 'X' key to exit.\n" );

        // initialize generators
        (new Thread(riderGenerator)).start();
        (new Thread(busGenerator)).start();

        // terminate the program
        while(true){
            userInput = scanner.nextLine();
            if((userInput.equals("X")) | (userInput.equals("x")))
                System.exit(0);
        }
    }
}
