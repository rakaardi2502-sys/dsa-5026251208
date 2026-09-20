package lw01.prelab;

public class ColourPrint extends PrintJob{
    
    ColourPrint (String id, int pages){
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        // TODO Auto-generated method stub
        int firstTier = Math.min(getPages(), 10);
        int remain = Math.max(getPages() - 10, 0);
        return firstTier * 1500 + remain * 1000 + 2000;
    }

    @Override
    String label() {
        // TODO Auto-generated method stub
        return "Colour";
    }
}
