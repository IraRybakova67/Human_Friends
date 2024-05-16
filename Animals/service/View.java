package service;
import java.sql.Types;
import model.Animal;
import model.Commands;
import model.Title;


public class View {
    public void printOnConsoleType(Types type){
        System.out.println(type.toString());

    }
    public void printOnConsoleTitle(Title title){
        System.out.println(title.toString());

    }

    public void printOnConsoleCommands(Commands commands){
        System.out.println(commands.toString());

    }
    public void printOnConsoleAnimal(Animal animal){
        System.out.println(animal.toString());

    }
    


}
