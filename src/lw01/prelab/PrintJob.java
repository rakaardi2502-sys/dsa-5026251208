package lw01.prelab;

public abstract class PrintJob implements Chargeable{
    private String id;
    private int pages;

    protected PrintJob (String id, int pages){
        this.id = id;
        this.pages = pages;
    }

    String getId(){
        return this.id;
    }
    int getPages(){
        return this.pages;
    }

    public abstract int calculateCharge();

    int calculateCharge(int copies) {
        if (copies < 0){
            throw new IllegalArgumentException("Number of copies cannot be negative");
        }
        return copies * calculateCharge();
    }

    String label(){
        return "Print";
    }

    String summary(){
        return getId() + " | " + label() + " | " + calculateCharge();
    }


}
