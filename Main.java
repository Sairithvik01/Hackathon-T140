import java.util.*;

class Patient {
    private String name;
    private int age;
    private String ailment;

    public Patient(String name, int age, String ailment) {
        this.name = name;
        this.age = age;
        this.ailment = ailment;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getAilment() { return ailment; }
}

class OPDQueue {
    private Queue<Patient> queue = new LinkedList<>();

    public void registerPatient(Patient patient) {
        queue.offer(patient);
        System.out.println("Patient registered: " + patient.getName());
    }

    public void attendNextPatient() {
        if (!queue.isEmpty()) {
            Patient next = queue.poll();
            System.out.println("Attending to: " + next.getName());
        } else {
            System.out.println("No patients in queue.");
        }
    }

    public void showQueue() {
        if (queue.isEmpty()) {
            System.out.println("No patients in the queue.");
            return;
        }
        System.out.println("Patients in queue:");
        for (Patient p : queue) {
            System.out.println("- " + p.getName() + " | Ailment: " + p.getAilment());
        }
    }
}

class BedManager {
    private boolean[] beds;

    public BedManager(int totalBeds) {
        beds = new boolean[totalBeds];
    }

    public int admitPatient() {
        for (int i = 0; i < beds.length; i++) {
            if (!beds[i]) {
                beds[i] = true;
                System.out.println("Patient admitted to bed " + i);
                return i;
            }
        }
        System.out.println("No beds available.");
        return -1;
    }

    public void dischargePatient(int bedNumber) {
        if (bedNumber >= 0 && bedNumber < beds.length && beds[bedNumber]) {
            beds[bedNumber] = false;
            System.out.println("Patient discharged from bed " + bedNumber);
        } else {
            System.out.println("Invalid bed number or bed already empty.");
        }
    }

    public void showAvailableBeds() {
        int available = 0;
        for (boolean bed : beds) {
            if (!bed) available++;
        }
        System.out.println("Available beds: " + available + "/" + beds.length);
    }
}

class InventoryManager {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addItem(String item, int quantity) {
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
        System.out.println("Added " + quantity + " units of " + item);
    }

    public void dispenseItem(String item, int quantity) {
        if (inventory.containsKey(item) && inventory.get(item) >= quantity) {
            inventory.put(item, inventory.get(item) - quantity);
            System.out.println("Dispensed " + quantity + " units of " + item);
        } else {
            System.out.println("Insufficient stock or item not found: " + item);
        }
    }

    public void showInventory() {
        System.out.println("Inventory status:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " units");
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OPDQueue opdQueue = new OPDQueue();
        BedManager bedManager = new BedManager(5);
        InventoryManager inventoryManager = new InventoryManager();

        while (true) {
            System.out.println("\n--- Hospital Management Menu ---");
            System.out.println("1. Register OPD Patient");
            System.out.println("2. Attend Next OPD Patient");
            System.out.println("3. Show OPD Queue");
            System.out.println("4. Admit Patient to Bed");
            System.out.println("5. Discharge Patient from Bed");
            System.out.println("6. Show Available Beds");
            System.out.println("7. Add Inventory Item");
            System.out.println("8. Dispense Inventory Item");
            System.out.println("9. Show Inventory");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter patient name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter patient age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter ailment: ");
                    String ailment = scanner.nextLine();
                    opdQueue.registerPatient(new Patient(name, age, ailment));
                    break;

                case 2:
                    opdQueue.attendNextPatient();
                    break;

                case 3:
                    opdQueue.showQueue();
                    break;

                case 4:
                    bedManager.admitPatient();
                    break;

                case 5:
                    System.out.print("Enter bed number to discharge: ");
                    int bedNo = scanner.nextInt();
                    bedManager.dischargePatient(bedNo);
                    break;

                case 6:
                    bedManager.showAvailableBeds();
                    break;

                case 7:
                    System.out.print("Enter item name: ");
                    String item = scanner.nextLine();
                    System.out.print("Enter quantity to add: ");
                    int qtyAdd = scanner.nextInt();
                    inventoryManager.addItem(item, qtyAdd);
                    break;

                case 8:
                    System.out.print("Enter item name: ");
                    String itemDisp = scanner.nextLine();
                    System.out.print("Enter quantity to dispense: ");
                    int qtyDisp = scanner.nextInt();
                    inventoryManager.dispenseItem(itemDisp, qtyDisp);
                    break;

                case 9:
                    inventoryManager.showInventory();
                    break;

                case 0:
                    System.out.println("Exiting system. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}