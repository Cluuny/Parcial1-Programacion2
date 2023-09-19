package uptc.programacion2.exceptions;

public class CityNotFoundException extends Exception {
    public CityNotFoundException() {
        super("La Cuidad no existe!\n");
    }
}
