public class ReservedLotDecorator extends LotDecorator {
    public ReservedLotDecorator(LotComponent decoratedLot) {
        super(decoratedLot);
    }

    @Override
    public String getDescription() {
        return decoratedLot.getDescription().replace("Status: Available", "Status: RESERVED");
    }

    @Override
    public double getPrice() {
        // Add a 10% reservation fee to the price
        return decoratedLot.getPrice() * 1.1;
    }
}
