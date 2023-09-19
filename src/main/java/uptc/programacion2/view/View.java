package uptc.programacion2.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {
    private final BufferedReader console;

    public View() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String readAll(String message) throws IOException {
        this.showMessage(message);
        String input = "";
        input = console.readLine().trim();
        if (!input.isEmpty()) {
            return input;
        } else {
            throw new IOException();
        }
    }


    public boolean readBoolean(String message) throws IOException {
        this.showMessage(message);
        String input;
        input = console.readLine().trim();
        return !input.isEmpty() && input.matches("^[Yy]$");
    }

    public String readText(String message) throws IOException {
        this.showMessage(message);
        String input = "";
        input = console.readLine().trim();
        if (!input.isEmpty() && input.matches("^[0-9a-zA-Z.\\s]+$")) {
            return input;
        } else {
            throw new IOException();
        }
    }

    public int readInt(String message) throws IOException {
        this.showMessage(message);
        int number = 0;
        String input = console.readLine().trim();
        number = Integer.parseInt(input);
        if (number >= 0) {
            return number;
        } else {
            throw new IOException();
        }
    }
}
