package uptc.programacion2.view;

import java.io.IOException;

public class Menu {
    private final View view;

    public Menu() {
        view = new View();
    }

    public int mainMenu() throws IOException {
        String mainMenu = "Seleccione alguna de las siguientes opciones:\n1. Crear Pais\n2. Listar Pais\n3. Seleccionar Pais\n4. Eliminar Pais\n5. Salir";
        return view.readInt(mainMenu);
    }

    public int departmentsMenu() throws IOException {
        String departmentsMenu = "Seleccione alguna de las siguientes opciones para continuar:\n1. Crear Departamento\n2. Listar departamentos\n3. Seleccionar departamento\n4. Eliminar departamento\n5. Volver";
        return view.readInt(departmentsMenu);
    }

    public int citiesMenu() throws IOException {
        String departmentsMenu = "Seleccione alguna de las siguientes opciones para continuar:\n1. Crear cuidad\n2. Listar cuidades\n3. Eliminar cuidad\n4. Volver";
        return view.readInt(departmentsMenu);
    }
}
