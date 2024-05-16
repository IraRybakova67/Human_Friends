package service;

import java.util.ArrayList;
import java.util.List;
import model.Commands;
import model.Description;
import model.Title;
import model.Types;

public class DataService {

    private List<Description> list = new ArrayList<>();

    public void create(String discript, TypeEnum typeEnum) { // создание списка
        int id = getIdNext(typeEnum);

        if (typeEnum == TypeEnum.Title) {
            list.add(new Title(id, discript));
        }
        if (typeEnum == TypeEnum.Type) {
            list.add(new Types(id, discript));

        }
        if (typeEnum == TypeEnum.Command) {
            list.add(new Commands(id, discript));
        }

    }
    // public void addDescription(Description description){ // добавление элемента в
    // список
    // list.add(description);
    /*
     * }
     * public List<Description> gDescriptions(TypeEnum typeEnum){
     * List<Description> result = new ArrayList<>();
     * for (Description obj : list) {
     * if (obj.typeEnum == typeEnum) {
     * result.add(obj);
     * }
     * }
     * return result;
     * 
     * }
     */

    

    public List<Description> getList(TypeEnum type) { // передача списка
        List<Description> resultList = new ArrayList<>();

        for (Description description : list) {
            if (type == TypeEnum.Title) {
                resultList.add(description);
            }
            if (type == TypeEnum.Type) {
                resultList.add(description);
            }
            if (type == TypeEnum.Command) {
                resultList.add(description);
            }

        }
        return resultList;

    }

    public int getIdNext(TypeEnum type) {
        List<Description> result = getList(type); // присвоение id
        return result.size() + 1;

    }

    public void sendOnConsole(List<Description> list) { // вывод списка на консоль

        for (Description description : list) {
            System.out.println(description);

        }

    }

    public Description getById(TypeEnum typeEnum, int id) { // поиск по id

        boolean itsType = typeEnum == TypeEnum.Type;

        for (Description value : list) {

            if (value instanceof Types && itsType && ((Types) value).getTypeId() == id) {
                return value;
            }
            if (value instanceof Title && !itsType && ((Title) value).getTitleId() == id) {
                return value;

            }
            if (value instanceof Commands && !itsType && ((Commands) value).getCommId() == id) {
                return value;

            }

        }
        return null;

    }

    public Description findById(int id, List<Description> list) { // поиск по id
        for (Description obj : list) {
            if (obj instanceof Title && ((Title) obj).getTitleId() == id) {
                return obj;
            } else if (obj instanceof Commands && ((Commands) obj).getCommId() == id) {
                return obj;
            } else if (obj instanceof Types && ((Types) obj).getTypeId() == id) {
                return obj;
            }
        }
        return null;
    }

}