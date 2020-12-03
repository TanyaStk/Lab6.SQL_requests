package com.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Company> companies = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("Companies.csv"))) {
            while (scanner.hasNext()) {
                Company company = new Company(scanner.nextLine());
                companies.add(company);
            }
            searchingByRequest(companies);
            System.out.println("Completed\n");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    static void searchingByRequest(List<Company> companies) {
        int requestIndex = 1;
        try (Scanner scanner = new Scanner(new File("Requests.txt"));
             FileWriter logFile =
                     new FileWriter(new File("Logfile.txt"), true)) {
            List<Company> result = new ArrayList<>();
            RequestsManager manager = new RequestsManager(companies);
            while (scanner.hasNext()) {
                String request = scanner.nextLine().toLowerCase();
                final String substring = request.substring(request.indexOf("= "), request.indexOf('\n') - 1);
                if (request.contains((KeyWords.ABBREVIATION).toLowerCase())) {
                    result = manager.chooseCompaniesByAbbreviation(substring);
                } else if (request.contains((KeyWords.TYPE_OF_BUSINESS).toLowerCase())) {
                    result = manager.chooseCompaniesByTypeOfBusiness(substring);
                } else if (request.contains((KeyWords.EMPLOYEES_AMOUNT).toLowerCase())) {
                    result = manager.chooseCompaniesByEmployeesAmount(
                            Integer.parseInt(request.substring(
                                    (request.indexOf("from: ") + 6), request.indexOf("to:") - 1)),
                            Integer.parseInt(request.substring(request.indexOf("to: ") + 4)));
                }
                logWriting(logFile, request);
                FileWriter outputFile =
                        new FileWriter(new File("Request" + requestIndex + ".csv"));
                fileWriting(outputFile, result);
                outputFile.close();
                requestIndex++;
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    static void logWriting(FileWriter logFile, String request) throws IOException {
        SimpleDateFormat formatNow = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date now = new Date();
        logFile.write("Date and time of running: " + formatNow.format(now) + "\n");
        logFile.write("Request: " + request + "\n");
    }

    static void fileWriting(FileWriter outputFile,
                            List<Company> inf) throws IOException {
        for (Company company : inf) {
            outputFile.append(company.toString());
            outputFile.append(",");
            outputFile.append(System.lineSeparator());
        }
    }
}
