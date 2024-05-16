package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Animal {
    private int animalId;
    private String nick;
    private LocalDate birthDate;
    private Title title;
    private Types type;
    private List<Commands> commands;

    public Animal(int animalId, Types type, LocalDate birthDate, String nick,Title title,  List<Commands> commands) {
        this.animalId = animalId;
        this.nick = nick;
        this.birthDate = birthDate;
        this.title = title;
        this.type = type;
        this.commands = commands;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthDateForm() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy");
        return dtf.format(birthDate);
    }

    public void setBirthDate(LocalDate birthDate) {

        this.birthDate = birthDate;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public List<Commands> getCommands() {
        return commands;
    }

    public void setCommands(List<Commands> commands) {
        this.commands = commands;
    }

    public String printCommandsA(List<Commands> commandsList) {
        StringBuilder sb = new StringBuilder();
        for (Commands command : commandsList) {
            sb.append(command.getDesccription()).append(", ");
        }
        String result = sb.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 2); // убираем последнюю запятую и пробел
        }
        return result;
    }

    
    @Override
    public String toString() {
        return "Animal [" + animalId + ", тип: " + type.getDesccription() + ", дата рождения: "
                + getBirthDateForm() + ", nick: " + nick + ", название: " + title.getDesccription() + ", команды: "
                + printCommandsA(commands) + "]";
    }
}
