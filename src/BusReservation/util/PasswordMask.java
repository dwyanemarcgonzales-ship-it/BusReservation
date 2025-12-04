package BusReservation.util;

import java.io.Console;
import javax.swing.*;

public class PasswordMask {

    public static String readMasked(String prompt) {
        Console console = System.console();

        // If running in CMD/Terminal
        if (console != null) {
            char[] pw = console.readPassword(prompt);
            return (pw == null) ? "" : new String(pw);
        }

        // If running in NetBeans (no console)
        final JPasswordField pf = new JPasswordField();
        Object[] message = { prompt, pf };

        int option = JOptionPane.showConfirmDialog(
                null,
                message,
                "Enter Password",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            return new String(pf.getPassword());
        }

        return "";
    }
}
