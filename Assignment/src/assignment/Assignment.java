
package assignment;


import java.util.Scanner;

public class Assignment {

    public static void main(String[] args) {
        // Initialize repository with capacity 100 (array-based storage)
        Series.initializeRepository(100);

        // Seed some data (optional, helps demo)
        Series.addSeries(new Series(101, "Extreme Sports", 18, 10));
        Series.addSeries(new Series(102, "Bargain Hunters", 13, 8));
        Series.addSeries(new Series(103, "Home Cooking", 16, 12));

        runMenu();
    }
       private static void runMenu() {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String sc = input.nextLine().trim();

            switch (sc) {
                case "1" -> Series.CaptureSeries(input);
                case "2" -> Series.SearchSeries(input);
                case "3" -> Series.UpdateSeries(input);
                case "4" -> Series.DeleteSeries(input);
                case "5" -> {
                    Series.ExitSeriesApplication();
                    running = false;
                }
                case "6" -> Series.ReportSeries();
                default -> System.out.println("Invalid option. Please try again.");
            }  
          if (running) {
                System.out.print("Enter 1 to launch menu or any other key to exit: ");
                String next = input.nextLine().trim();
                if (!"1".equals(next)) {
                    Series.ExitSeriesApplication();
                    running = false;
                }
            }
        }
     
    }
        private static void printMenu() {
        System.out.println();
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new series");
        System.out.println("(2) Search for a series");
        System.out.println("(3) Update series age restriction");
        System.out.println("(4) Delete a series");
        System.out.println("(5) Exit Series Application");
        System.out.println("(6) Print Series Report");
        System.out.println(); //(Deepseek, 2025)

       
    }
    
    
}
//Reference List
//Farrell, J.2023.Java Programming.Boston: Cengage.
//Deepseek, 2024. Response to query about coding. [online] Available at: https://chat.deepseek.com/ [Accessed 2 September 2025]
