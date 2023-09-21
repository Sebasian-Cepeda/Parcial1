package edu.eci.arsw.math;

public class ParcialThread extends Thread {
    private int a,b;
    private Long startTime;
    private Object lock = new Object();
    private PiDigits piDigits;
    private byte[] digits;

    public ParcialThread(PiDigits piDigits) {
        super();
        this.a = a;
        this.b = b;
        this.piDigits = piDigits;
    }

    @Override
    public void run() {
        while (true) {
            this.startTime = System.currentTimeMillis();
            long targetPauseTime = startTime + 1000;

            synchronized (lock) {
                digits  = piDigits.getDigits(a, b);
            }

            if (System.currentTimeMillis() >= targetPauseTime) {
                try {
                    System.out.println("");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public PiDigits gePiDigits(){
        return this.piDigits;
    }
    
}
