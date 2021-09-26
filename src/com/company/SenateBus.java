package com.company;

import java.util.concurrent.Semaphore;

public class SenateBus implements Runnable {

    private final int id;
    private WaitingArea waitingArea;
    private final Semaphore riderBoards;
    private final Semaphore busDeparts;
    private final Semaphore mutex;

    public SenateBus(int id, WaitingArea waitingArea, Semaphore riderBoards, Semaphore busDeparts, Semaphore mutex) {
        this.id = id;
        this.waitingArea = waitingArea;
        this.riderBoards = riderBoards;
        this.busDeparts = busDeparts;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
            arrive();
            if (waitingArea.getWaitingRiders() > 0) {
                riderBoards.release();
                busDeparts.acquire();
            }
            mutex.release();
            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void arrive() {
        System.out.println("Bus " + id + " arrived");
        System.out.println("No of waiting riders when bus arrived : " + waitingArea.getWaitingRiders());
    }

    public void depart() {
        System.out.println("Bus : " + id + " departed");
    }
}
