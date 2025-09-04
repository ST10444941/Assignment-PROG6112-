
package assignment;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Series extends MediaItem {
     // Information hiding
    private int numberOfEpisodes;

    // Repository (array) + counters (advanced arrays via sorting, search, manual delete, resize logic)
    private static Series[] repo;
    private static int count;

    // Constructors
    public Series() {}

    public Series(int id, String name, int ageRestriction, int numberOfEpisodes) {
        super(id, name, ageRestriction);
        this.numberOfEpisodes = numberOfEpisodes;
    }
     // Getter/Setter
    public int getNumberOfEpisodes() { return numberOfEpisodes; }
    public void setNumberOfEpisodes(int numberOfEpisodes) { this.numberOfEpisodes = numberOfEpisodes; }

    // Initialization
    public static void initializeRepository(int capacity) {
        repo = new Series[Math.max(1, capacity)];
        count = 0;
    }

    // ----- Core logic (non-interactive, unit-testable) -----

    public static boolean addSeries(Series s) {
        if (s == null) return false;
        if (findIndexById(s.getId()) != -1) return false; // no duplicates

        ensureCapacity();
        repo[count++] = s;
        return true;
    }
     public static Series findById(int id) {
        int idx = findIndexById(id);
        return idx == -1 ? null : repo[idx];
    }

    public static boolean updateSeries(int id, String newName, Integer newAgeRestriction, Integer newEpisodes) {
        int idx = findIndexById(id);
        if (idx == -1) return false;

        Series s = repo[idx];
        if (newName != null && !newName.isBlank()) s.setName(newName);
        if (newAgeRestriction != null && newAgeRestriction >= 0) s.setAgeRestriction(newAgeRestriction);
        if (newEpisodes != null && newEpisodes >= 0) s.setNumberOfEpisodes(newEpisodes);
        return true;
    }
     public static boolean deleteById(int id) {
        int idx = findIndexById(id);
        if (idx == -1) return false;

        // Shift-left delete from array
        for (int i = idx; i < count - 1; i++) {
            repo[i] = repo[i + 1];
        }
        repo[count - 1] = null;
        count--;
        return true;
    }

    public static Series[] getAllSortedById() {
        Series[] copy = Arrays.copyOf(repo, count);
        Arrays.sort(copy, Comparator.comparingInt(Series::getId));
        return copy;
    }

    public static int getCount() {
        return count;
    }
     private static void ensureCapacity() {
        if (count >= repo.length) {
            // Resize array (advanced arrays: manual capacity growth)
            repo = Arrays.copyOf(repo, repo.length * 2);
        }
    }

    private static int findIndexById(int id) {
        // Linear search (arrays + loops)
        for (int i = 0; i < count; i++) {
            if (repo[i].getId() == id) return i;
        }
        return -1;
    }
     // ----- Reporting -----

    public static void ReportSeries() {
        Series[] all = getAllSortedById();
        if (all.length == 0) {
            System.out.println("No series to report.");
            return;
        }
        for (int i = 0; i < all.length; i++) {
            System.out.println("===========================================");
            System.out.println("Series " + (i + 1));
            System.out.println("===========================================");
            System.out.print(all[i].toReportString());
            System.out.println();
        }
        System.out.println("===========================================");
        System.out.println("Total number of series: " + all.length);
    }

    private String toReportString() {
        StringBuilder sb = new StringBuilder();
        sb.append(baseReport());
        sb.append("NUMBER OF EPISODES: ").append(numberOfEpisodes).append("\n");
        return sb.toString();
    }
    // ----- REQUIRED interactive methods (console I/O) -----

    // 1) Capture a new series
    public static void CaptureSeries(Scanner input) {
        try {
            System.out.print("Enter series id: ");
            int id = Integer.parseInt(input.nextLine().trim());

            if (findIndexById(id) != -1) {
                System.out.println("Series with Series Id: " + id + " already exists!");
                return;
            }

            System.out.print("Enter series name: ");
            String name = input.nextLine();

            System.out.print("Enter age restriction: ");
            int age = Integer.parseInt(input.nextLine().trim());

            System.out.print("Enter number of episodes: ");
            int eps = Integer.parseInt(input.nextLine().trim());
             boolean ok = addSeries(new Series(id, name, age, eps));
            if (ok) System.out.println("Series captured successfully.");
            else System.out.println("Failed to capture series.");
        } catch (NumberFormatException | InputMismatchException ex) {
            System.out.println("Invalid input. Capture aborted.");
        }
    }

    // 2) Search for a series
    public static void SearchSeries(Scanner input) {
        System.out.print("Enter the series id to search: ");
        String raw = input.nextLine().trim();
        try {
            int id = Integer.parseInt(raw);
            Series s = findById(id);
            if (s == null) {
                System.out.println("Series with Series Id: " + id + " was not found!");
            } else {
                
            System.out.println("SERIES NAME: " + s.getName());
                System.out.println("SERIES AGE RESTRICTION: " + s.getAgeRestriction());
                System.out.println("SERIES NUMBER OF EPISODES: " + s.getNumberOfEpisodes());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid series id.");
        }
    }

    // 3) Update series (name, age restriction, number of episodes)
    public static void UpdateSeries(Scanner input) {
        System.out.print("Enter the series id to update: ");
        String raw = input.nextLine().trim();
        try {
            int id = Integer.parseInt(raw);
            Series s = findById(id);
            if (s == null) {
                System.out.println("Series with Series Id: " + id + " was not found!");
                return;
            }
            System.out.println("Series Name: " + s.getName());

            System.out.print("Enter the new series name (leave blank to keep): ");
            String newName = input.nextLine();
            System.out.print("Enter the new age restriction (leave blank to keep): ");
            String newAgeRaw = input.nextLine().trim();
            Integer newAge = newAgeRaw.isBlank() ? null : Integer.parseInt(newAgeRaw);

            System.out.print("Enter the number of episodes (leave blank to keep): ");
            String newEpsRaw = input.nextLine().trim();
            Integer newEps = newEpsRaw.isBlank() ? null : Integer.parseInt(newEpsRaw);

            boolean ok = updateSeries(id, newName, newAge, newEps);
            System.out.println(ok ? "Series updated successfully." : "Update failed.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Update aborted.");
        }
    }
    // 4) Delete a series
    public static void DeleteSeries(Scanner sc) {
        System.out.print("Enter the series id to delete: ");
        String raw = sc.nextLine().trim();
        try {
            int id = Integer.parseInt(raw);
            Series s = findById(id);
            if (s == null) {
                System.out.println("Series with Series Id: " + id + " was not found!");
                return;
            }
            System.out.print("Are you sure you want to delete this series from the system? Yes (y) to delete: ");
            String confirm = sc.nextLine().trim().toLowerCase();
            if (confirm.equals("y") || confirm.equals("yes")) {
                boolean ok = deleteById(id);
                System.out.println(ok
                        ? "Series with Series Id: " + id + " WAS deleted!"
                        : "Delete failed.");
            } else {
                System.out.println("Delete canceled.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid series id.");
        }
    }
    // 5) Exit application
    public static void ExitSeriesApplication() {
        System.out.println("Exiting Series Application. Goodbye!");
    }
    
}       
    
    
