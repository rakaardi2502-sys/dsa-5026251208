package lw01.prelab;

public class MonoPrint extends PrintJob{

    MonoPrint (String id, int pages){
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        // T2ODO Auto-generated method stub
        return getPages() * 500;
    }

    @Override
    String label() {
        // TODO Auto-generated method stub
        return "Mono";
    }
}
