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
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    static void searchingByRequest(List<Company> companies) {
        try (Scanner scanner = new Scanner(new File("Requests.txt"))) {
            int requestIndex = 1;
            FileWriter logFile =
                    new FileWriter(new File("Logfile.txt"), true);
            FileWriter outputFile =
                    new FileWriter(new File("Request" + requestIndex + ".csv"));
            List<Company> result = new ArrayList<>();
            String category;
            RequestsManager manager = new RequestsManager(companies);
//                logWriting(logFile, request, result.size());
//                fileWriting(outputFile, result);
//                requestIndex++;
            logFile.close();
            outputFile.close();
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    static void logWriting(FileWriter logFile, String request,
                           int amountFound) throws IOException {
        SimpleDateFormat formatNow = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date now = new Date();
        logFile.write("Date and time of running: " + formatNow.format(now) + "\n");
        logFile.write("Request: " + request + "\n");
        logFile.write("Amount of matching notes: " + amountFound + "\n");
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
