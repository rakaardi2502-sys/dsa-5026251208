package lw01.unguided;

public abstract class Rental {
    private String id;
    private int days;
    private int units;

    protected Rental(String id, int days, int units){
        this.id = id;
        this.days = days;
        this.units = units;
    }

    String getId(){
        return this.id;
    }

    int getDays(){
        return this.days;
    }

    abstract int calculateCharge();

    int calculateTotalCharge(){
        return calculateCharge() * units;
    }

    String label(){
        return "Rental";
    }

    String summary(){
        return getId() + " | " + label() + " | " + calculateTotalCharge();
    }
}
