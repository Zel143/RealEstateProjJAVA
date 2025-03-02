public class SoldLotDecorator extends LotDecorator {
    public SoldLotDecorator(LotComponent decoratedLot) {
        super(decoratedLot);
    }

    @Override
    public String getDescription() {
        return decoratedLot.getDescription() + " (Sold)";
    }

    @Override
    public double getPrice() {
        return decoratedLot.getPrice();
    }
}
