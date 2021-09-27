package com.company;

import java.util.concurrent.Semaphore;

public class SenateBus implements Runnable {

    private final int id;
    private final WaitingArea waitingArea;
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
        System.out.println("Senate bus " + id + " arrived");
        System.out.println("No of waiting riders when senate bus "+id+" arrived : " + waitingArea.getWaitingRiders());
    }

    public void depart() {
        System.out.println("Senate bus : " + id + " departed");
    }
}
