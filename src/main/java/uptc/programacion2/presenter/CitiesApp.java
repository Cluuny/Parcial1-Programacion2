package uptc.programacion2.presenter;

import uptc.programacion2.helpers.Context;
import uptc.programacion2.helpers.FileAPI;
import uptc.programacion2.models.City;
import uptc.programacion2.models.Department;
import uptc.programacion2.view.Menu;
import uptc.programacion2.view.View;

public class CitiesApp {

    private final Context context;
    private final View view;
    private final Menu menu;
    private final FileAPI fileAPI;

    public CitiesApp(Context context) {
        this.context = context;
        this.view = new View();
        this.menu = new Menu();
        this.fileAPI = new FileAPI();
    }

    public void displayCitiesMenu() {
        Department selectedDepartment = context.getContextDepartment();
        boolean exit = false;
        String citiesData;
        String cityCode;
        try {
            do {
                view.showMessage("Departamento seleccionado -> " + selectedDepartment.getDepartmentName());
                if (selectedDepartment.getDepartmentCities().isEmpty()) {
                    view.showMessage("ATENCIÓN: No se encontráron cuidades registradas\nEs necesario crear una Ciudad para continuar.\n");
                }
                int opt = menu.citiesMenu();
                switch (opt) {
                    case 1 -> {
                        String cityName = view.readText("Escribe el nombre de la cuidad:");
                        cityCode = selectedDepartment.getDepartmentCode() + "-" + selectedDepartment.getDepartmentCities().size() + 1;
                        boolean isDepartmentCapital = view.readBoolean("La cuidad es capital de " + selectedDepartment.getDepartmentName() + " [y/n]");
                        boolean isCountryCapital = view.readBoolean("La cuidad es capital de " + context.getContextCountry().getCountryName() + " [y/n]");
                        boolean cityWasCreated = selectedDepartment.createCity(new City(cityName, cityCode, isDepartmentCapital, isCountryCapital));
                        if (cityWasCreated) {
                            fileAPI.writeFile(context.getContextWorld());
                            fileAPI.writeCitiesTxt(context.getContextDepartment());
                            view.showMessage("Cuidad exitosamente creada!\n");
                        }
                    }
                    case 2 -> {
                        citiesData = selectedDepartment.listCities();
                        if (citiesData.isEmpty()) {
                            view.showMessage(selectedDepartment.getDepartmentName() + " no cuenta con cuidades registradas!\n");
                            break;
                        }
                        view.showMessage("CUIDADES REGISTRADAS PARA: " + selectedDepartment.getDepartmentName());
                        view.showMessage(citiesData);
                    }
                    case 3 -> {
                        citiesData = selectedDepartment.listCities();
                        cityCode = view.readText("Seleccione el Codigo de referencia de la cuidad que desea eliminar: \n" + citiesData);
                        if (citiesData.isEmpty()) {
                            view.showMessage(selectedDepartment.getDepartmentName() + " no cuenta con cuidades registradas!\n");
                            break;
                        }
                        City deletedCity = selectedDepartment.deleteCity(cityCode);
                        fileAPI.writeFile(context.getContextWorld());
                        view.showMessage(deletedCity.getCityName() + " fue exitosamente eliminado!\n");
                    }
                    case 4 -> {
                        fileAPI.writeFile(context.getContextWorld());
                        exit = true;
                    }
                }
            } while (!exit);
        } catch (Exception e) {
            view.showMessage(e.getMessage());
        }
    }
}
