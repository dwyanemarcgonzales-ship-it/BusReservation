package BusReservation;

import java.util.Scanner;
import BusReservation.service.*;
import BusReservation.model.*;
import BusReservation.util.PasswordMask;

public class LoginMenu {

    static Scanner sc = new Scanner(System.in);
    static AdminService adminService = Main.adminService;
    static PassengerService passengerService = Main.passengerService;

    public static void start() {

        while (true) {
            System.out.println("\n======== BUS RESERVATION SYSTEM ========");
            System.out.println("1. Admin Login");
            System.out.println("2. Passenger Login");
            System.out.println("3. Passenger Registration");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int c;
            try {
                c = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (c) {
                case 1 -> adminLogin();
                case 2 -> passengerLogin();
                case 3 -> passengerRegister();
                default -> { return; }
            }
        }
    }

    static void adminLogin() {
        System.out.print("Username: ");
        String u = sc.nextLine();

        String p = PasswordMask.readMasked("Password: ");

        if (adminService.login(u, p)) {
            AdminMenu.start();
        } else {
            System.out.println("\nInvalid Username or Password!");
        }
    }

    static void passengerRegister() {
        System.out.print("Fullname: ");
        String f = sc.nextLine().trim();
        System.out.print("Username: ");
        String u = sc.nextLine().trim();

        String p = PasswordMask.readMasked("Password: ");

        if (f.isEmpty() || u.isEmpty() || p.isEmpty()) {
            System.out.println("All fields required. Registration failed.");
            return;
        }

        passengerService.register(u, p, f);
    }

    static void passengerLogin() {
        System.out.print("Username: ");
        String u = sc.nextLine();

        String p = PasswordMask.readMasked("Password: ");

        User user = passengerService.login(u, p);

        if (user != null) {
            PassengerMenu.start(user);
        } else {
            System.out.println("\nInvalid passenger login or not approved yet!");
        }
    }
}
