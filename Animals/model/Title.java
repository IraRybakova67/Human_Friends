package model;

public class Title extends Description {

    private int titleId;

    public Title(int titleId, String desccription) {
        super(desccription);
        this.titleId = titleId;
    }

    public int getTitleId() {
        return titleId;
    }

    @Override
    public String toString() {
        return "Title [" + titleId + ", " + super.getDesccription() + "]";
    }
    

}
