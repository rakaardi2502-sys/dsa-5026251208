package lw01.unguided;

public class LaptopRental extends Rental {
    
    LaptopRental (String id, int days, int units){
        super(id, days, units);
    }

    @Override
    int calculateCharge() {
        return getDays() * 40000 + 10000;
    }

    @Override
    String label() {
        return "Laptop";
    }
}
