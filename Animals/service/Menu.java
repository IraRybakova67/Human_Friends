package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Commands;

import model.Title;
import model.Types;

public class Menu {
   
    DataService dataService = new DataService();
    AnimalService animalService = new AnimalService();
    FileService fileService = new FileService();

    public void menuStart() {
        try (Scanner scanner = new Scanner(System.in)) {

            int choice;

            do {
                System.out.println("Меню:");
                System.out.println("1. Создать название животного");
                System.out.println("2. Создать тип");
                System.out.println("3. Создать команду");
                System.out.println("4. Создать животное");
                System.out.println("5. Выучить команду");
                System.out.println("6. Вывести список животных");
                System.out.println("7. Вывести названия животных");
                System.out.println("8. Вывести типы животных");
                System.out.println("9. Вывести список команд");
                System.out.println("10. Вывести список вьючных животных");
                System.out.println("11. Вывести список домашних животных");
                System.out.println("12. Вывести список  животных по дате рождения");
                System.out.println("0. выход");
                System.out.print("Сделайте свой выбор: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Введите название: ");
                        String title = scanner.next();
                        dataService.create(title, TypeEnum.Title);
                        fileService.addNewValue("AnimalsTitles.csv", title);

                        break;

                    case 2:
                        System.out.print("Введите тип: ");
                        String type = scanner.next();
                        dataService.create(type, TypeEnum.Type);
                        fileService.addNewValue("AnimalsTypes.csv", type);
                        break;
                    case 3:
                        System.out.print("Введите команду: ");
                        String comm = scanner.next();
                        dataService.create(comm, TypeEnum.Command);
                        fileService.addNewValue("AnimalsCommands.csv", comm);

                        break;
                    case 4:
                        System.out.print("Введите кличку животного: ");
                        String nick = scanner.next();
                        // ========================================================================================================
                        LocalDate birthDate = null;

                        while (birthDate == null) {
                            System.out.println("Введите дату рождения в формате YYYY-MM-DD: ");
                            String input = scanner.nextLine();
                            birthDate = animalService.getLocalDate(input);
                        }

                        // ===========================================================================================================

                        System.out.print("Введите  id названия животного: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Некорректный ввод. Введите число!");
                            System.out.print("Введите ID: ");
                            scanner.next();
                        }
                        int titleId = scanner.nextInt();
                        // Title titleAnimal = controller.titleAnimal(titleId);
                        String titleNewAnimal = fileService.findValueById("AnimalsTitles.csv", titleId);

                        System.out.print("Введите  id типа животного: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Некорректный ввод. Введите число!");
                            System.out.print("Введите ID: ");
                            scanner.next();
                        }
                        int typeId = scanner.nextInt();
                        // Types typeAnimal = controller.typeAnimal(typeId);
                        String typeNewAnimal = fileService.findValueById("AnimalsTypes.csv", typeId);

                        int idAnimal = animalService.idAnimal();

                        List<Commands> commandForAnimal = new ArrayList<>();
                        System.out.print("Введите  id команды животного, для завершения введите 0 ");
                        while (true) {
                            System.out.print("Введите ID команды: ");
                            if (scanner.hasNextInt()) {
                                int comId = scanner.nextInt();

                                if (comId == 0) {
                                    break;
                                }

                                String commandNewAnimal = fileService.findValueById("AnimalsCommands.csv", comId);
                                Commands com = (Commands) animalService.convertStringToClass(comId, commandNewAnimal,
                                        TypeEnum.Command);

                                commandForAnimal.add(com);
                            }

                            else {
                                System.out.println("Некорректный ввод. Введите число!");
                                scanner.next();

                            }
                        }
                        Title titleStr = (Title) animalService.convertStringToClass(titleId, titleNewAnimal,
                                TypeEnum.Title);
                        Types typeStr = (Types) animalService.convertStringToClass(idAnimal, typeNewAnimal,
                                TypeEnum.Type);

                        animalService.newAnimal(idAnimal, typeStr, birthDate, nick, titleStr, commandForAnimal);
                        animalService.printAnimal( animalService.newAnimal(idAnimal, typeStr, birthDate, nick, titleStr, commandForAnimal));
                        fileService.addAnimalToFile( animalService.newAnimal(idAnimal, typeStr, birthDate, nick, titleStr, commandForAnimal));
                        //fileService.addToAnimalsFile(animalService.newAnimal(idAnimal, typeStr, birthDate, nick, titleStr, commandForAnimal));

                      //  fileService.addNewValue(titleNewAnimal, typeNewAnimal);

                        break;
                    case 5:
                        System.out.print("Введите  id названия животного: "); // поиск по id названия
                        while (!scanner.hasNextInt()) {
                            System.out.println("Некорректный ввод. Введите число!");
                            System.out.print("Введите ID: ");
                            scanner.next();
                        }
                        int Id = scanner.nextInt();

                        String foundTitle = fileService.findValueById("AnimalsTitles.csv", Id);
                        fileService.rezultSearch(Id, foundTitle);


                        /*
                         * if (foundTitle != null) {
                         * System.out.println("Значение с id " + Id + ": " + foundTitle);
                         * } else {
                         * System.out.println("Значение с id " + Id + " не найдено.");
                         * }
                         */

                        // animalService.printOnConsoleAnimal(newAnimal(idAnimal,nick, birthDate,
                        // titleAnimal, typeAnimal, commandForAnimal));

                        break;
                        case 11: 
                        animalService.sendOnConsole(animalService.viewAnimals());
                        break;



                    case 7:
                        
                        fileService.printList("AnimalsTitles.csv");
                        break;
                    case 8:
                       
                        fileService.printList("AnimalsTypes.csv");
                        break;
                    case 9:
                        fileService.printList("AnimalsCommands.csv");

                        
                        break;

                    case 10:
                        System.out.print("Введите  id для поиска: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Некорректный ввод. Введите число!");
                            System.out.print("Введите ID: ");
                            scanner.next();
                        }
                        int id = scanner.nextInt();

                        System.out.print(dataService.findById(id, (dataService.getList(TypeEnum.Title))));
                        break;

                    case 0:
                        System.out.println("До свидания!");
                        break;
                    default:
                        System.out.println("Неверный ввод, попробуйте еще раз!");
                        break;
                }

                System.out.println();
            } while (choice != 0);
        }
    }
}
