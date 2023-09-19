package uptc.programacion2.models;

import java.util.ArrayList;

public class World {
    private final ArrayList<Country> countries;

    public World() {
        this.countries = new ArrayList<Country>();
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public boolean createCountry(String countryName, int countryCode) {
        return this.getCountries().add(new Country(countryName, countryCode));
    }

    public String listCountries() {
        StringBuilder countriesData = new StringBuilder();
        if (this.getCountries().isEmpty()) {
            return "";
        }
        for (Country country : this.getCountries()) {
            countriesData.append(country.getCountryCode()).append(". ").append(country).append("\n");
        }
        return countriesData.toString();
    }

    public Country getCountry(int countryIndex) {
        return this.getCountries().get(countryIndex - 1);
    }

    public Country deleteCountry(int countryIndex) {
        return this.getCountries().remove(countryIndex - 1);
    }


    @Override
    public String toString() {
        return "Paises:" + this.getCountries();
    }
}
