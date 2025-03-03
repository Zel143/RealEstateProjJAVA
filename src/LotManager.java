import java.util.HashMap;
import java.util.Map;

public class LotManager {
    private final Map<String, LotComponent> lots;

    public LotManager() {
        lots = new HashMap<>();
    }

    public String addLot(String lotDetails) {
        try {
            String lotId = "Lot" + (lots.size() + 1);
            // Parse lotDetails to create a Lot object
            String[] details = lotDetails.split(",");
            if (details.length < 4) {
                return "Error: Not enough information provided. Format should be: block,lotNumber,size,price";
            }
            
            int block = Integer.parseInt(details[0]);
            int lotNumber = Integer.parseInt(details[1]);
            double size = Double.parseDouble(details[2]);
            double price = Double.parseDouble(details[3]);
            
            Lot lot = new Lot(block, lotNumber, size, price);
            lots.put(lotId, lot);
            return "Lot added: " + lotId + " - " + lot.getDescription();
        } catch (NumberFormatException e) {
            return "Error: Invalid number format. Please check your inputs.";
        } catch (Exception e) {
            return "Error adding lot: " + e.getMessage();
        }
    }

    public String searchLot(String lotId) {
        if (lots.containsKey(lotId)) {
            return "Lot found: " + lotId + " - " + lots.get(lotId).getDescription();
        } else {
            return "Lot not found: " + lotId;
        }
    }

    public String sellLot(String lotId) {
        if (lots.containsKey(lotId)) {
            LotComponent lot = lots.get(lotId);
            lots.put(lotId, new SoldLotDecorator(lot));
            return "Lot sold: " + lotId + " - " + lots.get(lotId).getDescription();
        } else {
            return "Lot not found: " + lotId;
        }
    }

    public String reserveLot(String lotId) {
        if (lots.containsKey(lotId)) {
            LotComponent lot = lots.get(lotId);
            // Don't allow reserving a sold lot
            if (lot.getDescription().contains("Status: SOLD")) {
                return "Cannot reserve lot: " + lotId + " - This lot is already sold";
            }
            lots.put(lotId, new ReservedLotDecorator(lot));
            return "Lot reserved: " + lotId + " - " + lots.get(lotId).getDescription();
        } else {
            return "Lot not found: " + lotId;
        }
    }

    public String generateReport() {
        if (lots.isEmpty()) {
            return "No lots available in the system.";
        }
        
        StringBuilder report = new StringBuilder("===== REAL ESTATE LOTS REPORT =====\n\n");
        for (Map.Entry<String, LotComponent> entry : lots.entrySet()) {
            report.append(entry.getKey()).append(" - ").append(entry.getValue().getDescription()).append("\n");
        }
        return report.toString();
    }
}
