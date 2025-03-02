public class Lot implements LotComponent {
    private int block;
    private int lotNumber;
    private double size;
    private double price;
    private String status;

    public Lot(int block, int lotNumber, double size, double price) {
        this.block = block;
        this.lotNumber = lotNumber;
        this.size = size;
        this.price = price;
        this.status = "Available";
    }

    // Getters and setters
    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getDescription() {
        return "Block: " + block + ", Lot: " + lotNumber + ", Size: " + size + " sqm, Price: $" + price + ", Status: " + status;
    }


    @Override
    public String toString() {
        return "Block: " + block + ", Lot: " + lotNumber + ", Size: " + size + " sqm, Price: $" + price + ", Status: " + status;
    }
}
