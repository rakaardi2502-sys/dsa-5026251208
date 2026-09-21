package lw01.unguided;

public class ProjectorRental extends Rental {
    
    ProjectorRental(String id, int days, int units){
        super(id, days, units);
    }

    @Override
    int calculateCharge() {
        int firstLayer = Math.min(getDays(), 3);
        int secondLayer = Math.max(getDays() - 3, 0);
        return firstLayer * 60000 + secondLayer * 45000 + 20000;
    }

    @Override
    String label() {
        return "Projector";
    }
}
