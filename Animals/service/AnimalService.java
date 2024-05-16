package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import model.Animal;
import model.Types;
import model.Commands;
import model.Title;

public class AnimalService {
    DataService dataService = new DataService();
    FileService fileService = new FileService();

    private List<Animal> animals = new ArrayList<>();

    public Animal newAnimal(int animalId, Types type, LocalDate birthDate, String nick, Title title,
            List<Commands> commands) {
        Animal newAnimal = new Animal(animalId, type, birthDate, nick, title, commands);

        animals.add(newAnimal);
        assignId(newAnimal);

        return newAnimal;

    }

    public void printAnimal(Animal animal) {
        System.out.println(animal.toString());

    }

    public List<Animal> viewAnimals() {

        List<Animal> animals = new ArrayList<>();
        {
            try (BufferedReader br = new BufferedReader(new FileReader("AnimalsAnimals.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    int id = Integer.parseInt(data[0]);
                    int idType = Integer.parseInt(data[1]);
                    Types type = (Types) convertStringToClass(idType,
                            fileService.findValueById("AnimalsTypes.csv", idType),
                            TypeEnum.Type);

                    LocalDate birthDate = getLocalDatePars(data[2].substring(0, 10));
                    String nick = data[3];
                    int idTitle = Integer.parseInt(data[4]);
                    Title title = (Title) convertStringToClass(idTitle,
                            fileService.findValueById("AnimalsTitles.csv", idTitle), TypeEnum.Title);

                    List<Commands> commands = new ArrayList<>();

                    for (int i = 5; i < data.length; i++) {
                        int idCom = Integer.parseInt(data[i]);
                        Commands com = (Commands) convertStringToClass(idCom,
                                fileService.findValueById("AnimalsCommands.csv", idCom), TypeEnum.Command);

                        commands.add(com);

                    }
                    Animal animal = new Animal(id, type, birthDate, nick, title, commands);
                    animals.add(animal);
                }
            } catch (IOException | DateTimeParseException e) {
                e.printStackTrace();

            }
        }

        return animals;
    }

    public void sendOnConsole(List<Animal> list) {

        for (Animal anim : list) {
            System.out.println(anim);

        }

    }

    public Object convertStringToClass(int id, String value, TypeEnum type) {
        switch (type) {
            case Title:
                return new Title(id, value);
            case Type:
                return new Types(id, value);
            case Command:
                return new Commands(id, value);
            default:
                return null;
        }
    }

    public Integer idAnimal() { // присвоить id животному

        int id = animals.size() + 1;
        return id;
    }

    private void assignId(Animal animal) { // присвоить id животному
        animal.setAnimalId(viewAnimals().size() + 2);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public LocalDate getLocalDate(String input) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate date = LocalDate.parse(input, formatter);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты. Попробуйте еще раз.");
            return null;
        }

    }

    public LocalDate getLocalDatePars(String input) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(input, formatter);
        return date;
    }
}
