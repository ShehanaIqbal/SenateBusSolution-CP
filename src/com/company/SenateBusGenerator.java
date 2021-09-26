package com.company;

import java.util.Random;

class SenateBusGenerator implements Runnable {

    private float arrivalMeanTime;
    private WaitingArea waitingArea;
    private static Random random;

    public SenateBusGenerator(float arrivalMeanTime, WaitingArea waitingArea) {
        this.arrivalMeanTime = arrivalMeanTime;
        this.waitingArea = waitingArea;
        random = new Random();
    }
    @Override
    public void run() {
        int SenateBusId = 1;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Bus "+ SenateBusId+" is about to arrive...");
                SenateBus SenateBus = new SenateBus( SenateBusId, waitingArea, WaitingArea.getRiderBoards(), WaitingArea.getBusDeparts(), WaitingArea.getMutex());
                (new Thread(SenateBus)).start();
                SenateBusId++;
                Thread.sleep(getExponentiallyDistributedSenateBusInterArrivalTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public long getExponentiallyDistributedSenateBusInterArrivalTime() {
        float lambda = 1 / arrivalMeanTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}