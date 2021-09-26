package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //parameters to generate buses and riders
//        float riderArrivalMeanTime = 30f * 1000;
//        float busArrivalMeanTime = 20 * 60f * 1000;
        float riderArrivalMeanTime = 5f * 1000;
        float busArrivalMeanTime= 30f * 1000 ;

        //taking an input to terminate the program
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("\n*******  Press any key to exit.  *******\n" );

        WaitingArea waitingArea = new WaitingArea();

        //start rider generator
        RiderGenerator riderGenerator = new RiderGenerator(riderArrivalMeanTime, waitingArea);
        (new Thread(riderGenerator)).start();

        //start bus genrator
        SenateBusGenerator busGenerator = new SenateBusGenerator(busArrivalMeanTime,waitingArea);
        (new Thread(busGenerator)).start();

        // Program Termination with a user input
        while(true){
            userInput = scanner.nextLine();
            if(userInput != null)
                System.exit(0);
        }
    }
}
