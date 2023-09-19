package uptc.programacion2.models;

import uptc.programacion2.exceptions.CityNotFoundException;

import java.util.ArrayList;

public class Department {
    private final String departmentName;
    private final ArrayList<City> departmentCities;
    private final String departmentCode;

    public Department(String departmentName, String departmentCode) {
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.departmentCities = new ArrayList<City>();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public ArrayList<City> getDepartmentCities() {
        return departmentCities;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public boolean createCity(City city) {
        return this.getDepartmentCities().add(city);
    }

    public String listCities() {
        StringBuilder citiesData = new StringBuilder();
        if (this.getDepartmentCities().isEmpty()) {
            return "";
        }
        for (City city : this.getDepartmentCities()) {
            citiesData.append(city.getCityCode()).append(" -> ").append(city).append("\n");
        }
        return citiesData.toString();
    }

    public City deleteCity(String cityCode) throws Exception {
        for (int i = 0; i < this.getDepartmentCities().size(); i++) {
            City city = this.getDepartmentCities().get(i);
            if (city.getCityCode().equals(cityCode)) {
                return this.getDepartmentCities().remove(i);
            }
        }
        throw new CityNotFoundException();
    }

    @Override
    public String toString() {
        return departmentName;
    }
}
