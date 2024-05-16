package model;
public class Types extends Description {

    private int typeId;

    public Types(int typeId, String desccription) {
        super(desccription);
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
    

    @Override
    public String toString() {
        return "Type [" + typeId + ", " + super.getDesccription() + "]";
    }

}
