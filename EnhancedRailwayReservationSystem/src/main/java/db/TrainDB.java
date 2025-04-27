package db;

import model.Train;
import java.util.*;

public class TrainDB {
    private static List<Train> trains = new ArrayList<>();

    static {
        trains.add(new Train("Express 101", 100));  // 100 seats
        trains.add(new Train("Superfast 202", 50)); // 50 seats
        trains.add(new Train("Shatabdi 303", 150)); // 150 seats
    }

    public static List<Train> getAvailableTrains() {
        List<Train> availableTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.isAvailable()) {
                availableTrains.add(train);
            }
        }
        return availableTrains;
    }

    public static Train getTrainByName(String trainName) {
        for (Train train : trains) {
            if (train.getTrainName().equalsIgnoreCase(trainName)) {
                return train;
            }
        }
        return null;
    }
}
