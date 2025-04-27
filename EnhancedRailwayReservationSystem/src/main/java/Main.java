import model.User;
import model.Ticket;
import controller.UserController;
import controller.TicketController;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User currentUser = null;

        while (true) {
            if (currentUser == null) {
                System.out.println("\n--- Railway Ticket Booking ---");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Admin Login");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Enter Username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    if (UserController.register(username, password)) {
                        System.out.println("Registration Successful!");
                    } else {
                        System.out.println("Registration Failed!");
                    }
                } else if (choice == 2) {
                    System.out.print("Enter Username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    currentUser = UserController.login(username, password);
                    if (currentUser != null) {
                        System.out.println("Login Successful! Welcome, " + currentUser.getUsername());
                    } else {
                        System.out.println("Login Failed!");
                    }
                } else if (choice == 3) {
                    System.out.print("Enter Admin Username: ");
                    String adminUser = sc.nextLine();
                    System.out.print("Enter Admin Password: ");
                    String adminPass = sc.nextLine();
                    if (adminUser.equals("admin") && adminPass.equals("admin123")) {
                        adminMenu();
                    } else {
                        System.out.println("Invalid Admin Credentials!");
                    }
                } else if (choice == 4) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice!");
                }
            } else {
                System.out.println("\n--- User Menu ---");
                System.out.println("1. Book Ticket");
                System.out.println("2. View My Tickets");
                System.out.println("3. Cancel Ticket");
                System.out.println("4. Logout");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Enter Train Name: ");
                    String trainName = sc.nextLine();
                    if (TicketController.bookTicket(currentUser.getId(), trainName)) {
                        System.out.println("Ticket Booked Successfully!");
                    } else {
                        System.out.println("Ticket Booking Failed!");
                    }
                } else if (choice == 2) {
                    List<Ticket> tickets = TicketController.viewTickets(currentUser.getId());
                    if (tickets.isEmpty()) {
                        System.out.println("No tickets booked.");
                    } else {
                        for (Ticket ticket : tickets) {
                            System.out.println("PNR: " + ticket.getPnr() + " | Train: " + ticket.getTrainName() + " | Status: " + ticket.getStatus() + " | Fare: ₹" + ticket.getFare());
                        }
                    }
                } else if (choice == 3) {
                    System.out.print("Enter PNR to Cancel: ");
                    String pnr = sc.nextLine();
                    List<Ticket> tickets = TicketController.viewTickets(currentUser.getId());
                    boolean found = false;
                    int fare = 0;
                    for (Ticket ticket : tickets) {
                        if (ticket.getPnr().equals(pnr)) {
                            found = true;
                            fare = ticket.getFare();
                            break;
                        }
                    }
                    if (found) {
                        if (TicketController.cancelTicket(pnr)) {
                            int refund = TicketController.calculateRefund(fare);
                            System.out.println("Ticket Cancelled Successfully! Refund Amount: ₹" + refund);
                        } else {
                            System.out.println("Cancellation Failed!");
                        }
                    } else {
                        System.out.println("PNR not found in your bookings!");
                    }
                } else if (choice == 4) {
                    currentUser = null;
                    System.out.println("Logged Out Successfully.");
                } else {
                    System.out.println("Invalid Choice!");
                }
            }
        }
        sc.close();
    }

    public static void adminMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Bookings");
            System.out.println("2. Exit Admin Mode");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                List<Ticket> tickets = TicketController.viewAllTickets();
                if (tickets.isEmpty()) {
                    System.out.println("No tickets found.");
                } else {
                    for (Ticket ticket : tickets) {
                        System.out.println("PNR: " + ticket.getPnr() + " | UserID: " + ticket.getUserId() + " | Train: " + ticket.getTrainName() + " | Status: " + ticket.getStatus() + " | Fare: ₹" + ticket.getFare());
                    }
                }
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}
