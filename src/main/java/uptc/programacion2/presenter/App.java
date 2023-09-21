package uptc.programacion2.presenter;

import uptc.programacion2.helpers.FileAPI;
import uptc.programacion2.models.Context;
import uptc.programacion2.models.Country;
import uptc.programacion2.models.World;
import uptc.programacion2.view.View;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    private final View view;
    private final Context context;
    private final FileAPI fileAPI;

    public App() {
        view = new View();
        context = new Context();
        fileAPI = new FileAPI();
    }

    public static void main(String[] args) {
        App app = new App();
        app.loadENV();
        app.displayMainMenu();
    }

    public void displayMainMenu() {
        boolean exit = false;
        String countriesData;
        int countryIndex;
        try {
            do {
                context.setContextWorld(fileAPI.initWorld(System.getProperty("WORLD_PATH")));
                World contextWorld = context.getContextWorld();
                if (contextWorld.getCountries().isEmpty()) {
                    view.showMessage("ATENCIÓN: No se encontráron paises registrados\nEs necesario crear un pais para continuar.\n");
                }
                int opt = view.showMenu(System.getProperty("MAIN_MENU_PATH"));
                switch (opt) {
                    case 1 -> {
                        String countryName = view.readText("Ingresa el nombre del pais que quieres crear: ");
                        boolean wasCreated = contextWorld.createCountry(countryName, contextWorld.getCountries().size() + 1);
                        if (wasCreated) {
                            fileAPI.writeFile(contextWorld);
                            view.showMessage("País correctamente creado!\n");
                        }
                    }
                    case 2 -> {
                        countriesData = contextWorld.listCountries();
                        view.showMessage("PAISES REGISTRADOS: \n");
                        if (countriesData.isEmpty()) {
                            view.showMessage("No se encontraron paises registrados!\n");
                            break;
                        }
                        view.showMessage(countriesData);
                    }
                    case 3 -> {
                        countriesData = contextWorld.listCountries();
                        view.showMessage("Selecciona alguno de los siguiente paises para continuar:");
                        if (countriesData.isEmpty()) {
                            view.showMessage("No se encontraron paises registrados!\n");
                            break;
                        }
                        countryIndex = view.readInt(countriesData);
                        context.setContextCountry(contextWorld.getCountry(countryIndex));
                        new DepartmentsApp(context).displayDepartmentsMenu();
                    }
                    case 4 -> {
                        countriesData = contextWorld.listCountries();
                        view.showMessage("Selecciona el pais que deseas eliminar:");
                        if (countriesData.isEmpty()) {
                            view.showMessage("No se encontraron paises registrados!\n");
                            break;
                        }
                        countryIndex = view.readInt(countriesData);
                        Country deletedCountry = contextWorld.deleteCountry(countryIndex);
                        fileAPI.writeFile(contextWorld);
                        view.showMessage(deletedCountry.getCountryName() + " ha sido correctamente eliminado!");
                    }
                    case 0 -> {
                        fileAPI.writeFile(contextWorld);
                        view.showMessage("Saliendo...");
                        exit = true;
                    }
                    default -> throw new Exception("Opción no valida");
                }
            } while (!exit);
        } catch (Exception e) {
            e.getMessage();
            view.showMessage("- Recuerde ingresar caracteres numericos\n- Recuerde ingresar unicamente opciones validas");
            this.displayMainMenu();
        }
    }

    public void loadENV(){
        String workingDirectory = System.getProperty("user.dir");
        Path worldJSON = Paths.get(workingDirectory + File.separator + "src/main/java/uptc/programacion2/data/World.json");
        Path citiesTXT = Paths.get(workingDirectory + File.separator + "src/main/java/uptc/programacion2/data/Cities.txt");
        Path mainMenuApp = Paths.get(workingDirectory + File.separator + "src/main/java/uptc/programacion2/data/MenuApp.xml");
        Path departmentsMenuApp = Paths.get(workingDirectory + File.separator + "src/main/java/uptc/programacion2/data/MenuDepartmentsApp.xml");
        Path citiesMenuApp = Paths.get(workingDirectory + File.separator + "src/main/java/uptc/programacion2/data/MenuCitiesApp.xml");
        System.setProperty("WORLD_PATH", String.valueOf(worldJSON));
        System.setProperty("CITIES_PATH", String.valueOf(citiesTXT));
        System.setProperty("MAIN_MENU_PATH", String.valueOf(mainMenuApp));
        System.setProperty("DEPARTMENTS_MENU_PATH", String.valueOf(departmentsMenuApp));
        System.setProperty("CITIES_MENU_PATH", String.valueOf(citiesMenuApp));
    }
}