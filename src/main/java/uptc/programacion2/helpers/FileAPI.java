package uptc.programacion2.helpers;

import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import uptc.programacion2.models.Department;
import uptc.programacion2.models.World;
import uptc.programacion2.view.View;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;


public class FileAPI {

    public World initWorld(String pathDirectory) {
        try {
            if (this.isFileEmpty(pathDirectory)){
                World world = new World();
                this.writeFile(world);
                return  world;
            }
            return (World) this.readFile(pathDirectory, World.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public NodeList readXML(String path) {
        try {
            File xmlFile = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            try (FileInputStream inputStream = new FileInputStream(xmlFile)) {
                Document doc = builder.parse(inputStream);
                return doc.getElementsByTagName("option");
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            new View().showMessage(e.getMessage());
        }
        return null;
    }

    public void writeFile(World worldObject) throws IOException {
        new FileWriter(System.getProperty("WORLD_PATH"), false).close();
        Gson gson = new Gson();
        String json = gson.toJson(worldObject);
        try (FileWriter writer = new FileWriter(System.getProperty("WORLD_PATH"))) {
            writer.write(json);
        }catch (Exception e){
            new View().showMessage(e.getMessage());
        }
    }

    public void writeCitiesTxt(Department department) throws IOException {
        new FileWriter(System.getProperty("CITIES_PATH"), false).close();
        Gson gson = new Gson();
        String formatedText = gson.toJson(department).formatted();
        try (FileWriter writer = new FileWriter(System.getProperty("CITIES_PATH"))) {
            writer.write("----------------------------------------------------\n");
            writer.write(formatedText);
        }catch (Exception e){
            new View().showMessage(e.getMessage());
        }
    }

    public Object readFile(String path, Class<World> worldClassType) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(new FileReader(path), worldClassType);
        } catch (FileNotFoundException e) {
            new View().showMessage(e.getMessage());
        }
        return new Object();
    }

    public boolean isFileEmpty(String path){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.readLine() == null;
        } catch (IOException e) {
            new View().showMessage(e.getMessage());
            return false;
        }
    }
}
