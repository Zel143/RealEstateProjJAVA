public class ReservedLotDecorator extends LotDecorator {
    public ReservedLotDecorator(LotComponent decoratedLot) {
        super(decoratedLot);
    }

    @Override
    public String getDescription() {
        return decoratedLot.getDescription() + " (Reserved)";
    }

    @Override
    public double getPrice() {
        return decoratedLot.getPrice();
    }
}
