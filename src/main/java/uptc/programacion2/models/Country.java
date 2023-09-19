package uptc.programacion2.models;

import uptc.programacion2.exceptions.DepartmentNotFoundException;

import java.util.ArrayList;

public class Country {
    private final ArrayList<Department> countryDepartments;
    private final String countryName;
    private final int countryCode;

    public Country(String countryName, int countryCode) {
        this.countryName = countryName;
        this.countryDepartments = new ArrayList<>();
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public ArrayList<Department> getCountryDepartments() {
        return countryDepartments;
    }

    public Department getDepartment(String departmentCode) throws DepartmentNotFoundException {
        for (Department department : this.getCountryDepartments()) {
            if (department.getDepartmentCode().equals(departmentCode)) {
                return department;
            }
        }
        throw new DepartmentNotFoundException();
    }

    public boolean createDepartment(Department department) {
        return this.getCountryDepartments().add(department);
    }

    public int getCountryCode() {
        return countryCode;
    }

    public String listDepartments() {
        StringBuilder countriesData = new StringBuilder();
        if (this.getCountryDepartments().isEmpty()) {
            return "";
        }
        for (Department department : this.getCountryDepartments()) {
            countriesData.append(department.getDepartmentCode()).append(" -> ").append(department).append("\n");
        }
        return countriesData.toString();
    }

    public Department deleteDepartment(String departmentCode) throws Exception {
        for (int i = 0; i < this.getCountryDepartments().size(); i++) {
            Department department = this.getCountryDepartments().get(i);
            if (department.getDepartmentCode().equals(departmentCode)) {
                return this.getCountryDepartments().remove(i);
            }
        }
        throw new DepartmentNotFoundException();
    }

    public String generateCountryReference() {
        return this.getCountryName().substring(0, 3);
    }

    @Override
    public String toString() {
        return countryName;
    }
}
