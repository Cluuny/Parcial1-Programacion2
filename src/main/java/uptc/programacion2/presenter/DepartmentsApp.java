package uptc.programacion2.presenter;

import uptc.programacion2.helpers.Context;
import uptc.programacion2.helpers.FileAPI;
import uptc.programacion2.models.Country;
import uptc.programacion2.models.Department;
import uptc.programacion2.view.Menu;
import uptc.programacion2.view.View;

public class DepartmentsApp {
    private final Context context;
    private final View view;
    private final Menu menu;
    private final FileAPI fileAPI;

    public DepartmentsApp(Context context) {
        this.context = context;
        this.view = new View();
        this.menu = new Menu();
        this.fileAPI = new FileAPI();
    }

    public void displayDepartmentsMenu() {
        Country contextCountry = this.context.getContextCountry();
        boolean exit = false;
        String departmentsData;
        String departmentCode;
        try {
            do {
                view.showMessage("Pais seleccionado -> " + contextCountry.getCountryName());
                if (contextCountry.getCountryDepartments().isEmpty()) {
                    view.showMessage("ATENCIÓN: No se encontráron departamentos registrados\nEs necesario crear un departamento para continuar.\n");
                }
                int opt = menu.departmentsMenu();
                switch (opt) {
                    case 1 -> {
                        String departmentName = view.readText("Ingrese el nombre del departamento a crear: \n");
                        departmentCode = contextCountry.generateCountryReference() + (contextCountry.getCountryDepartments().size() + 1);
                        boolean departmentWasCreated = contextCountry.createDepartment(new Department(departmentName, departmentCode));
                        if (departmentWasCreated) {
                            fileAPI.writeFile(context.getContextWorld());
                            view.showMessage("Departamento exitosamente creado!\n");
                        }
                    }
                    case 2 -> {
                        departmentsData = contextCountry.listDepartments();
                        if (departmentsData.isEmpty()) {
                            view.showMessage(contextCountry.getCountryName() + " no cuenta con departamentos registrados!\n");
                            break;
                        }
                        view.showMessage("DEPARTAMENTOS REGISTRADOS PARA: " + contextCountry.getCountryName());
                        view.showMessage(departmentsData);
                    }
                    case 3 -> {
                        departmentsData = contextCountry.listDepartments();
                        view.showMessage("Selecciona alguno de los siguientes departamentos para continuar: \n");
                        if (departmentsData.isEmpty()) {
                            view.showMessage(contextCountry.getCountryName() + " no cuenta con departamentos registrados!\n");
                            break;
                        }
                        departmentCode = view.readText(departmentsData);
                        context.setContextDepartment(contextCountry.getDepartment(departmentCode));
                        new CitiesApp(context).displayCitiesMenu();
                    }
                    case 4 -> {
                        departmentsData = contextCountry.listDepartments();
                        if (departmentsData.isEmpty()) {
                            view.showMessage(contextCountry.getCountryName() + " no cuenta con departamentos registrados!\n");
                            break;
                        }
                        departmentCode = view.readText("Seleccione el Codigo de referencia del departamento que desea eliminar: \n" + departmentsData);
                        Department deletedDepartment = contextCountry.deleteDepartment(departmentCode);
                        fileAPI.writeFile(context.getContextWorld());
                        view.showMessage(deletedDepartment.getDepartmentName() + " fue exitosamente eliminado!\n");
                    }
                    case 5 -> {
                        fileAPI.writeFile(context.getContextWorld());
                        exit = true;
                    }
                }
            } while (!exit);
        } catch (Exception e) {
            view.showMessage(e.getMessage());
            this.displayDepartmentsMenu();
        }
    }
}
