package com.company;

import java.util.concurrent.Semaphore;

class Rider implements Runnable {

    private final int id;
    private final WaitingArea waitingArea;
    private final Semaphore riderArrives;
    private final Semaphore riderBoards;
    private final Semaphore busDeparts;
    private final Semaphore mutex;

    public Rider(int id, WaitingArea waitingArea, Semaphore riderArrives, Semaphore riderBoards, Semaphore busDeparts, Semaphore mutex) {
        this.id = id;
        this.waitingArea = waitingArea;
        this.riderArrives = riderArrives;
        this.riderBoards = riderBoards;
        this.busDeparts = busDeparts;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            riderArrives.acquire();
            mutex.acquire();
            enterBoardingArea();
            waitingArea.incrementWaitingRiders();
            mutex.release();
            riderBoards.acquire();
            Board();
            riderArrives.release();
            waitingArea.decrementWaitingRiders();
            if (waitingArea.getWaitingRiders() == 0)
                busDeparts.release();
            else
                riderBoards.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enterBoardingArea() {
        System.out.println("Rider :" + id + " entered the waiting area");
    }

    private void Board(){
        System.out.println("Passenger "+ id +" boarded to the bus.");
    }
}
