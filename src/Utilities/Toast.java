package Utilities;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Magic_Buddha on 4/13/2016.
 */
public class Toast extends Observable {
    //holds toasts queue
    private static LinkedList<TimedToast> messageQueue = new LinkedList<>();

    private static int milliToSec = 1000;
    private static String currentMessage = "";
    private static boolean active = false;
    private static Timer timer = new Timer();

    public static void createToast(String message, int duration) {
        System.out.println("Added new Toast");
        TimedToast tt = new TimedToast(message,duration);
        messageQueue.addLast(tt);
        if (!active) {
            activateToasts();
        }
    }

    public static boolean isActive() {
        return active;
    }

    public static String getCurrentMessage() {
        return currentMessage;
    }

    private static void activateToasts() {
        if ( messageQueue.size() > 0 ) {
            TimedToast tt = messageQueue.pollFirst();
            currentMessage = tt.getMessage();

            System.out.println("Starting... " + currentMessage);
            active = true;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Done... " + currentMessage);
                    activateToasts();
                }
            }, tt.getDuration() * milliToSec);

        } else {
            active = false;
        }
    }


    //used like a struct in c++
    private static class TimedToast {

        private String message;
        private int duration;
        public TimedToast(String message, int duration) {
            this.message = message;
            this.duration = duration;
        }

        public String getMessage() {
            return this.message;
        }

        public int getDuration() {
            return this.duration;
        }
    }


    public static void main(String args[]) {
        Toast.createToast("pirmas", 1);
        Toast.createToast("antras", 2);
        Toast.createToast("tretcias", 3);
        Toast.createToast("paskutinis", 1);

    }
}
