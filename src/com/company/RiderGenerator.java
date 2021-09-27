package com.company;

import java.util.Random;

class RiderGenerator implements Runnable {

    private final float arrivalMeanTime = 30f*1000;
    private WaitingArea waitingArea;

    public RiderGenerator(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    @Override
    public void run() {
        int riderIndex = 1;
        Random rand = new Random();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Rider "+ riderIndex+ " created.");
                Rider rider = new Rider(riderIndex, waitingArea, WaitingArea.getRiderArrives(), WaitingArea.getRiderBoards(), WaitingArea.getBusDeparts(), WaitingArea.getMutex());

                (new Thread(rider)).start();
                riderIndex++;

                float lambda = 1 / arrivalMeanTime;
                Thread.sleep(Math.round(-Math.log(1 - rand.nextFloat()) / lambda));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
