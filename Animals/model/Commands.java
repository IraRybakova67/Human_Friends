package model;
public class Commands extends Description{
    
    

    public int getCommId() {
        return commId;
    }

    private int commId;

    public Commands(int commId, String desccription) {
        super(desccription);
        this.commId = commId;
    }

    @Override
    public String toString() {
        return "Commands [" + commId + ", " + super.getDesccription() + "]";
    }

    
}
    