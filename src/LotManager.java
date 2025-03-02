import java.util.HashMap;
import java.util.Map;

public class LotManager {
    private final Map<String, LotComponent> lots;

    public LotManager() {
        lots = new HashMap<>();
    }

    public String addLot(String lotDetails) {
        String lotId = "Lot" + (lots.size() + 1);
        // Parse lotDetails to create a Lot object
        // For simplicity, assume lotDetails is in the format "block,lotNumber,size,price"
        String[] details = lotDetails.split(",");
        Lot lot = new Lot(Integer.parseInt(details[0]), Integer.parseInt(details[1]), Double.parseDouble(details[2]), Double.parseDouble(details[3]));
        lots.put(lotId, lot);
        return "Lot added: " + lotId + " - " + lot.getDescription();
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
            lots.put(lotId, new ReservedLotDecorator(lot));
            return "Lot reserved: " + lotId + " - " + lots.get(lotId).getDescription();
        } else {
            return "Lot not found: " + lotId;
        }
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder("Lots Report:\n");
        for (Map.Entry<String, LotComponent> entry : lots.entrySet()) {
            report.append(entry.getKey()).append(" - ").append(entry.getValue().getDescription()).append("\n");
        }
        return report.toString();
    }
}
