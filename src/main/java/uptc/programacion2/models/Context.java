package uptc.programacion2.models;

public class Context {
    private World contextWorld;
    private Country contextCountry;
    private Department contextDepartment;
    private City contextCity;

    public Context() {
    }

    public World getContextWorld() {
        return contextWorld;
    }

    public void setContextWorld(World contextWorld) {
        this.contextWorld = contextWorld;
    }

    public Country getContextCountry() {
        return contextCountry;
    }

    public void setContextCountry(Country contextCountry) {
        this.contextCountry = contextCountry;
    }

    public Department getContextDepartment() {
        return contextDepartment;
    }

    public void setContextDepartment(Department contextDepartment) {
        this.contextDepartment = contextDepartment;
    }

    public City getContextCity() {
        return contextCity;
    }

    public void setContextCity(City contextCity) {
        this.contextCity = contextCity;
    }
}
