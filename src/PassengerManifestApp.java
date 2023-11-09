import java.util.Scanner;
import java.util.InputMismatchException;

public class PassengerManifestApp {
    Passenger[] passengers = new Passenger[12];

    public static void main(String[] args) {
        PassengerManifestApp manifest = new PassengerManifestApp(); // create an instance of the PassengerManifestApp class
        Scanner myScanner = new Scanner(System.in); // create a Scanner object called myScanner to read user input

        try {
            while (true) { 
                System.out.println();
                System.out.println("What would you like to do?");
                System.out.println("1. Insert passenger");
                System.out.println("2. Remove passenger");
                System.out.println("3. Switch seats");
                System.out.println("4. Rename passenger");
                System.out.println("5. Count passengers");
                System.out.println("6. Print passenger manifest");
                System.out.println("7. Exit");
                System.out.println("Enter the number of your choice: ");
                System.out.println();

                int choice = myScanner.nextInt();
                myScanner.nextLine(); // consume the newline character left by nextInt()

                switch (choice) {
                    case 1:
                    System.out.println("Enter the passenger's name: ");
                    String name = myScanner.next();
                    myScanner.nextLine(); // consume the newline character left by next()
                    System.out.println("Enter the passenger's age: ");
                    int age = myScanner.nextInt();
                    manifest.insertPassenger(name, age); // use the insertPassenger method of the manifest object
                    break;

                    case 2:
                    System.out.println("Enter the seat number of the passenger to remove: ");
                    int seatToRemove = myScanner.nextInt() - 1 ; // subtract 1 from the seat number to match the i + 1 in the printed passenger manifest
                    manifest.removePassenger(seatToRemove);
                    break;
                    
                    case 3:
                    System.out.println("Enter seat number 1: ");
                    int seatNumber1 = myScanner.nextInt();
                    System.out.println("Enter seat number 2: ");
                    int seatNumber2 = myScanner.nextInt();
                    manifest.switchSeats(seatNumber1, seatNumber2);
                    break;

                    case 4:
                    System.out.println("Enter the seat number of the passenger to rename: ");
                    int seatToRename = myScanner.nextInt();
                    System.out.println("Enter the new name: ");
                    String newName = myScanner.next();
                    manifest.renamePassenger(seatToRename, newName);
                    break;

                    case 5:
                    myScanner.nextLine(); // consume the newline character left by nextInt() 
                    int passengerCount = manifest.countPassengers();
                    System.out.println("There are " + passengerCount + " passengers on the plane."); 
                    System.out.println();
                    break;

                    case 6:
                    manifest.printPassengerManifest();
                    break;

                    case 7:
                    myScanner.close(); // close the Scanner object to prevent leaks
                    System.out.println("Program terminated");
                    System.exit(0);
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
            myScanner.nextLine(); // consume any remaining input to prevent an infinite loop
        } finally {
            myScanner.close(); // close the Scanner object to prevent leaks
        }
    }

    public void insertPassenger(String name, int age) {
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] == null) {
                passengers[i] = new Passenger(name, age);
                System.out.println("Passenger " + name + " has been added to seat " + (i + 1) + ".");
                System.out.println();
                return;
            }
        }
        System.out.println("Manifest is full. Passenger not added.");
    }

    public void removePassenger(int seatNumber) {
        if (seatNumber >= 0 && seatNumber < passengers.length) {
            if (passengers[seatNumber] != null) {
                passengers[seatNumber] = null;
                System.out.println("Passenger removed.");
            } else {
                System.out.println("Seat is empty. Passenger not removed.");
            }
        } else {
            System.out.println("Invalid seat number");
        }
    }

    public void switchSeats(int seatNumber1, int seatNumber2) {
        int index1 = seatNumber1 - 1;
        int index2 = seatNumber2 - 1;

        if (index1 >= 0 && index1 < passengers.length && index2 >= 0 && index2 < passengers.length) {
            Passenger temp = passengers[index1];
            passengers[index1] = passengers[index2];
            passengers[index2] = temp;
            System.out.println("Seats switched.");
        } else {
            System.out.println("Invalid seat number.");
        }
    }

    public void renamePassenger(int seatNumber, String newName) {
        int index = seatNumber - 1;
        if (index >= 0 && index < passengers.length) {
            if (passengers[index] != null) {
                passengers[index].setName(newName);
                System.out.println("Passenger in seat " + seatNumber + " renamed to " + newName + ".");
            } else {
                System.out.println("Seat is empty. Passenger not renamed.");
            }
        } else {
            System.out.println("Invalid seat number.");
        }
    }

    public int countPassengers() {
        int passengerCount = 0;
        for (Passenger passenger : passengers) {
            if (passenger != null) {
                passengerCount++;
            }
        }
        return passengerCount;
        }
    

    public void printPassengerManifest() {
        System.out.println("##### PASSENGER MANIFEST #####");
        System.out.println();
        System.out.println("SkyBox Ltd.");
        System.out.println("Seat\tName\tAge"); // print the column headers
        System.out.println();

        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] != null) {
                int seatNumber = i + 1;
                System.out.printf("%d\t%s\t%d\n", seatNumber, passengers[i].getName(), passengers[i].getAge());  
            } else {
                int seatNumber = i + 1;
                System.out.println("Seat " + seatNumber + ": empty");
            }
        }
    }



}

