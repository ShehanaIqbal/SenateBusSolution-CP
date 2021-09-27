package com.company;

import java.util.Random;

class SenateBusGenerator implements Runnable {

    private static final float arrivalMeanTime = 20*60f*1000;
    private WaitingArea waitingArea;

    public SenateBusGenerator(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }
    @Override
    public void run() {
        int SenateBusId = 1;
        Random rand = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Bus "+ SenateBusId+" created");
                SenateBus SenateBus = new SenateBus( SenateBusId, waitingArea, WaitingArea.getRiderBoards(), WaitingArea.getBusDeparts(), WaitingArea.getMutex());

                (new Thread(SenateBus)).start();
                SenateBusId++;

                float lambda = 1 / arrivalMeanTime;
                Thread.sleep(Math.round(-Math.log(1 - rand.nextFloat()) / lambda));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}