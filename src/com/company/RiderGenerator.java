package com.company;

import java.util.Random;

class RiderGenerator implements Runnable {

    private float arrivalMeanTime;
    private WaitingArea waitingArea;
    private static Random random;

    public RiderGenerator(float arrivalMeanTime, WaitingArea waitingArea) {
        this.arrivalMeanTime = arrivalMeanTime;
        this.waitingArea = waitingArea;
        random = new Random();
    }

    @Override
    public void run() {
        int riderIndex = 1;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Rider "+ riderIndex+ " is about to arrive...");
                Rider rider = new Rider(riderIndex, waitingArea, WaitingArea.getRiderBoards(), WaitingArea.getRiderArrives(), WaitingArea.getBusDeparts(), WaitingArea.getMutex());
                (new Thread(rider)).start();
                riderIndex++;
                Thread.sleep(getExponentiallyDistributedRiderInterArrivalTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getExponentiallyDistributedRiderInterArrivalTime() {
        float lambda = 1 / arrivalMeanTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }

}
