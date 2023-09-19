package uptc.programacion2.models;

public class City {
    private final String cityName;
    private final String cityCode;
    private final Boolean isDepartmentCapital;
    private final Boolean isCountryCapital;

    public City(String cityName, String cityCode, Boolean isDepartmentCapital, Boolean isCountryCapital) {
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.isDepartmentCapital = isDepartmentCapital;
        this.isCountryCapital = isCountryCapital;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public Boolean getDepartmentCapital() {
        return isDepartmentCapital;
    }

    public Boolean getCountryCapital() {
        return isCountryCapital;
    }

    @Override
    public String toString() {
        return this.cityName;
    }
}
