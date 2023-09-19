package uptc.programacion2.exceptions;

public class DepartmentNotFoundException extends Exception {
    public DepartmentNotFoundException() {
        super("El departamento no existe!\n");
    }
}
