import java.util.Scanner;

public class RealEstateApp {
    private static final LotManager lotManager = new LotManager();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Welcome to Real Estate Lot Management System");
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Add a new lot");
            System.out.println("2. Search for a lot");
            System.out.println("3. Mark lot as sold");
            System.out.println("4. Reserve a lot");
            System.out.println("5. Generate report");
            System.out.println("6. Exit");
            
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline
            
            switch (choice) {
                case 1:
                    addLot();
                    break;
                case 2:
                    searchLot();
                    break;
                case 3:
                    sellLot();
                    break;
                case 4:
                    reserveLot();
                    break;
                case 5:
                    generateReport();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using Real Estate Lot Management System.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void addLot() {
        System.out.println("Enter lot details (block,lotNumber,size,price):");
        String lotDetails = scanner.nextLine();
        String result = lotManager.addLot(lotDetails);
        System.out.println(result);
    }
    
    private static void searchLot() {
        System.out.print("Enter lot ID to search: ");
        String lotId = scanner.nextLine();
        String result = lotManager.searchLot(lotId);
        System.out.println(result);
    }
    
    private static void sellLot() {
        System.out.print("Enter lot ID to sell: ");
        String lotId = scanner.nextLine();
        String result = lotManager.sellLot(lotId);
        System.out.println(result);
    }
    
    private static void reserveLot() {
        System.out.print("Enter lot ID to reserve: ");
        String lotId = scanner.nextLine();
        String result = lotManager.reserveLot(lotId);
        System.out.println(result);
    }
    
    private static void generateReport() {
        String report = lotManager.generateReport();
        System.out.println(report);
    }
}
