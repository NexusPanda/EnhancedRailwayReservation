package model;

public class Train {
    private String trainName;
    private int capacity;
    private int bookedSeats;

    public Train(String trainName, int capacity) {
        this.trainName = trainName;
        this.capacity = capacity;
        this.bookedSeats = 0;
    }

    public String getTrainName() {
        return trainName;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public boolean isAvailable() {
        return bookedSeats < capacity;
    }

    public void bookSeat() {
        if (isAvailable()) {
            bookedSeats++;
        }
    }
}
