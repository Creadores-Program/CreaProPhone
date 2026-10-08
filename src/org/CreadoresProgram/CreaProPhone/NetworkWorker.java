package org.CreadoresProgram.CreaProPhone;

import java.util.Vector;

public class NetworkWorker implements Runnable {
    private final Vector taskQueue = new Vector();
    private boolean running = true;

    public NetworkWorker() {
        Thread t = new Thread(this);
        t.start();
    }

    public synchronized void enqueueTask(Runnable task) {
        taskQueue.addElement(task);
        notify();
    }

    public void run() {
        while (running) {
            Runnable task = null;

            synchronized (this) {
                while (taskQueue.isEmpty() && running) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }

                if (!taskQueue.isEmpty()) {
                    task = (Runnable) taskQueue.firstElement();
                    taskQueue.removeElementAt(0);
                }
            }

            if (task != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stop() {
        running = false;
        synchronized (this) {
            notify();
        }
    }
}