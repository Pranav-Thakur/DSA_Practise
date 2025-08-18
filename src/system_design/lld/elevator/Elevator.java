package system_design.lld.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Elevator implements Runnable {
    private int id;
    private String display;
    private Direction direction;
    private Floor currentFloor;
    private TreeSet<Integer> upRequests, downRequests;
    private List<Observer> observers;
    

    private volatile boolean isRunning;

    public Elevator(int id, Floor currentFloor) {
        this.id = id;
        this.isRunning = false;
        this.currentFloor = currentFloor;
        this.direction = Direction.IDLE;
        this.observers = new ArrayList<>();
        upRequests = new TreeSet<>();
        downRequests = new TreeSet<>((a, b) -> b - a);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public synchronized void addObserver(Observer observer) {
       if (!observers.contains(observer)) {
            observers.add(observer);
       }
    }

    public synchronized void addObservers(List<Observer> observerr) {
        observerr.forEach(observer -> {
            if (!observers.contains(observer)) observers.add(observer);
        });
    }

    public void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    public void start() {
        isRunning = true;
    }

    public void stopElevator() {
        this.isRunning = false;
        direction = Direction.IDLE;
    }

    public synchronized void addRequest(Request request) {
        
        if (direction == Direction.UP) {
            if (request.getSource() == RequestSource.EXTERNAL) {
                upRequests.add(request.getSourceFloor().getId());
            } else if (request.getSource() == RequestSource.INTERNAL) {
                upRequests.add(request.getTargetFloor().getId());
            } else {
                System.out.println("Invalid input in request");
            }
        } else if (direction == Direction.DOWN) {
            if (request.getSource() == RequestSource.EXTERNAL) {
                downRequests.add(request.getSourceFloor().getId());
            } else if (request.getSource() == RequestSource.INTERNAL) {
                downRequests.add(request.getTargetFloor().getId());
            } else {
                System.out.println("Invalid input in request");
            }
        }
            
    }

    private void move() {
        if (upRequests.isEmpty() )
        if (currentFloor.getId() == upRequests.first() 
            || currentFloor.getId() == downRequests.first()) {
                return;
        }

        if (direction == Direction.IDLE) {
            if (!upRequests.isEmpty()) {
               // upRequests.
            }
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            move();
            try {
                Thread.sleep(1000); // Simulate movement time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                stopElevator();
            }
        }
    }
}
