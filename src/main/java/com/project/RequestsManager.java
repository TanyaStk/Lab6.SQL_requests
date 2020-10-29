package com.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestsManager {
    List<Company> companies;

    public RequestsManager(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> chooseCompaniesByAbbreviation(String request) {
        List<Company> requestedComp = new ArrayList<>();
        for (Company company : companies) {
            if (company.getAbbreviation().equalsIgnoreCase(request)) {
                requestedComp.add(company);
            }
        }
        return requestedComp;
    }

    public List<Company> chooseCompaniesByTypeOfBusiness(String request) {
        List<Company> requestedComp = new ArrayList<>();
        for (Company company : companies) {
            if (company.getTypeOfBusiness().equalsIgnoreCase(request)) {
                requestedComp.add(company);
            }
        }
        return requestedComp;
    }

    public List<Company> chooseCompaniesByNumberOfEmployees(
            int from, int to) {
        List<Company> requestedComp = new ArrayList<>();
        for (Company company : companies) {
            if ((company.getNumberOfEmployees() >= from) &&
                    (company.getNumberOfEmployees() <= to)) {
                requestedComp.add(company);
            }
        }
        return requestedComp;
    }
}
