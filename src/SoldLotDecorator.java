public class SoldLotDecorator extends LotDecorator {
    public SoldLotDecorator(LotComponent decoratedLot) {
        super(decoratedLot);
    }

    @Override
    public String getDescription() {
        return decoratedLot.getDescription().replace("Status: Available", "Status: SOLD")
                           .replace("Status: RESERVED", "Status: SOLD");
    }

    @Override
    public double getPrice() {
        // If there were any price changes related to sold status, we would implement them here
        return decoratedLot.getPrice();
    }
}
