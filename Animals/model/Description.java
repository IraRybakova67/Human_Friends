package model;

public   abstract class Description {

    private String desccription;

    public String getDesccription() {
        return desccription;
    }

    public void setDesccription(String desccription) {
        this.desccription = desccription;
    }

    public Description(String desccription) {

        this.desccription = desccription;
    }

    /* public  Description createDesccription(String text) {
        return new Description(text);
    }
 */
}
