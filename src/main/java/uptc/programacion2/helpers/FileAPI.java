package uptc.programacion2.helpers;

import com.google.gson.Gson;
import uptc.programacion2.models.Department;
import uptc.programacion2.models.World;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileAPI {

    public World initWorld(String pathDirectory) {
        try {
            return (World) this.readFile(pathDirectory, World.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(World worldObject) throws IOException {
        new FileWriter(System.getProperty("WORLD_PATH"), false).close();
        Gson gson = new Gson();
        String json = gson.toJson(worldObject);
        try (FileWriter writer = new FileWriter(System.getProperty("WORLD_PATH"))) {
            writer.write(json);
        }
    }

    public void writeCitiesTxt(Department department) throws IOException {
        new FileWriter(System.getProperty("CITIES_PATH"), false).close();
        Gson gson = new Gson();
        String formatedText = gson.toJson(department);
        try (FileWriter writer = new FileWriter(System.getProperty("CITIES_PATH"))) {
            writer.write(formatedText);
        }
    }

    public Object readFile(String path, Class<World> worldClassType) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(new FileReader(path), worldClassType);
        } catch (FileNotFoundException e) {
            System.out.println("ZD");
        }
        return new Object();
    }
}
