package Utilities;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Magic_Buddha on 4/14/2016.
 */
public class Toast extends Observable {
    private static Toast ourInstance = new Toast();

    public static Toast getInstance() {
        return ourInstance;
    }

    private Toast() {}


    //holds toasts queue
    private  LinkedList<TimedToast> messageQueue = new LinkedList<>();

    private  int milliToSec = 1000;
    private  String currentMessage = "";
    private  boolean active = false;
    private  Timer timer = new Timer();

    public  void createToast(String message, int duration) {
//        System.out.println("Added new Toast");
        TimedToast tt = new TimedToast(message,duration);
        messageQueue.addLast(tt);
        if (!active) {
            activateToasts();
        }
    }

    public  boolean isActive() {
        return active;
    }

    public  String getCurrentMessage() {
        return currentMessage;
    }

    private  void activateToasts() {
        if ( messageQueue.size() > 0 ) {
            TimedToast tt = messageQueue.pollFirst();
            currentMessage = tt.getMessage();
//            System.out.println("Starting... " + currentMessage);
            active = true;
//            setChanged();
//            notifyObservers();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
//                    System.out.println("Done... " + currentMessage);
                    active = false;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            setChanged();
                            notifyObservers();
                        }
                    }, 1 * milliToSec);
                    activateToasts();
                }
            }, tt.getDuration() * milliToSec);

        } else {
            active = false;
//            setChanged();
//            notifyObservers();
        }
        setChanged();
        notifyObservers();
    }


    //used like a struct in c++
    private class TimedToast {

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
}
