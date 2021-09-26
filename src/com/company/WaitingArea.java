package com.company;

import java.util.concurrent.Semaphore;

public class WaitingArea {

    private static int waitingRiders=0;

    private final static Semaphore riderArrives = new Semaphore(50);
    private final static Semaphore riderBoards = new Semaphore(0);
    private static Semaphore busDeparts = new Semaphore(0);
    private static Semaphore mutex = new Semaphore(1);

    public int getWaitingRiders() {
        return waitingRiders;
    }

    public void incrementWaitingRiders() {
        waitingRiders++;
        System.out.println("new rider added, number of riders at the waiting area is "+waitingRiders);
    }

    public void decrementWaitingRiders() {
        System.out.println("rider boarded, number of riders at the waiting area is "+waitingRiders);

        waitingRiders--;
    }

    public static Semaphore getRiderArrives() {
        return riderArrives;
    }

    public static Semaphore getRiderBoards() {
        return riderBoards;
    }

    public static Semaphore getBusDeparts() {
        return busDeparts;
    }

    public static Semaphore getMutex() {
        return mutex;
    }
}
