package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.Animal;
import model.Commands;

public class FileService {

    public String findValueById(String inputFile, int id) { // поиск значения в файле по id
        String value = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int currentId = Integer.parseInt(parts[0]);
                if (currentId == id) {
                    value = parts[1];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    public void rezultSearch(int id, String value) {

        if (value != null) {
            System.out.println("Значение с id " + id + ": " + value);
        } else {
            System.out.println("Значение с id " + id + " не найдено.");
        }
    }

    public void addNewValue(String outputFile, String newValue) { // запись значения в файл
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
            int maxId = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(outputFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    int currentId = Integer.parseInt(parts[0]);
                    if (currentId > maxId) {
                        maxId = currentId;
                    }
                }
            }

            bw.write((maxId + 1) + "," + newValue);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printList(String fileName) {

        try (
                BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println("ID: " + parts[0] + ",  " + parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAnimalToFile(Animal animal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("AnimalsAnimals.csv", true))) {
            writer.write(animal.getAnimalId() + "," +
                    animal.getType().getTypeId() + "," +
                    animal.getBirthDate() + "," +
                    animal.getNick() + "," +
                    animal.getTitle().getTitleId() + ",");

            if (animal.getCommands() != null && !animal.getCommands().isEmpty()) {
                for (Commands command : animal.getCommands()) {
                    writer.write(command.getCommId() + ",");
                }
            }

            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToAnimalsFile(Animal animal) {
        String filename = "AnimalsAnimals.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(animal.getAnimalId()).append(",");
            sb.append(animal.getType().getTypeId()).append(",");
            sb.append(animal.getBirthDate()).append(",");
            sb.append(animal.getNick()).append(",");
            sb.append(animal.getTitle().getTitleId());
            if (!animal.getCommands().isEmpty()) {
                sb.append(",");
                for (Commands command : animal.getCommands()) {
                    sb.append(command.getCommId()).append(",");
                }
                sb.deleteCharAt(sb.length() - 1); // удаить посл запятую
            }
            writer.println(sb.toString());
            System.out.println("Animal added successfully to Animals.csv");
        } catch (IOException e) {
            System.err.println("Error adding animal to Animals.csv: " + e.getMessage());
        }
    }


}
