package model;

public class Ticket {
    private String pnr;
    private int userId;
    private String trainName;
    private String status;
    private int fare;

    public Ticket(String pnr, int userId, String trainName, String status, int fare) {
        this.pnr = pnr;
        this.userId = userId;
        this.trainName = trainName;
        this.status = status;
        this.fare = fare;
    }

    public String getPnr() {
        return pnr;
    }

    public int getUserId() {
        return userId;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getStatus() {
        return status;
    }

    public int getFare() {
        return fare;
    }
}
