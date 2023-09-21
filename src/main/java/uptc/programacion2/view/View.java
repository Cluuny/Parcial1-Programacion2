package uptc.programacion2.view;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uptc.programacion2.helpers.FileAPI;

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

    public int showMenu(String path) throws IOException {
        FileAPI fileAPI = new FileAPI();
        NodeList mainMenu = fileAPI.readXML(path);
        StringBuilder builder = new StringBuilder();
        builder.append("Bienvenido! Porfavor seleccione alguna de las siguientes opciones!:\n");
        for (int i = 0; i < mainMenu.getLength(); i++) {
            Node node = mainMenu.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                builder.append(element.getAttribute("id")).append(". ").append(element.getTextContent()).append("\n");
            }
        }
        return this.readInt(builder.toString());
    }
}
