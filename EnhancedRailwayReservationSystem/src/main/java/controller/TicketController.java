package controller;

import db.TicketDB;
import db.TrainDB;
import model.Ticket;
import model.Train;
import java.util.*;

public class TicketController {
    public static boolean bookTicket(int userId, String trainName) {
        Train train = TrainDB.getTrainByName(trainName);
        if (train != null && train.isAvailable()) {
            // Book the ticket if available
            train.bookSeat();
            return TicketDB.bookTicket(userId, trainName, "Confirmed", 500);
        }
        return false; // If the train is not available
    }

    public static List<Ticket> viewTickets(int userId) {
        return TicketDB.viewTickets(userId);
    }

    public static boolean cancelTicket(String pnr) {
        return TicketDB.cancelTicket(pnr);
    }

    public static List<Ticket> viewAllTickets() {
        return TicketDB.viewAllTickets();
    }

    public static int calculateRefund(int fare) {
        int cancellationCharge = 50;
        int refund = fare - cancellationCharge;
        return refund > 0 ? refund : 0;
    }

    public static List<Train> getAvailableTrains() {
        return TrainDB.getAvailableTrains();
    }
}
