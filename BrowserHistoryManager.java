import java.net.URI;
import java.util.*;

/** Core Data Structures implementation for the Browser History Manager project. */
public class BrowserHistoryManager {
    private final Deque<String> backStack = new ArrayDeque<>();
    private final Deque<String> forwardStack = new ArrayDeque<>();
    private final Set<String> uniqueDomains = new HashSet<>();
    private final List<String> history = new ArrayList<>();
    private String currentPage = "";

    public void visit(String input) {
        String url = normalise(input);
        if (url.isEmpty()) {
            System.out.println("Please enter a valid website URL.");
            return;
        }
        if (!currentPage.isEmpty()) backStack.push(currentPage);
        currentPage = url;
        forwardStack.clear();
        history.add(0, url);
        uniqueDomains.add(domainOf(url));
        System.out.println("Opened: " + currentPage);
    }

    public void back() {
        if (backStack.isEmpty()) { System.out.println("No previous page."); return; }
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        System.out.println("Back to: " + currentPage);
    }

    public void forward() {
        if (forwardStack.isEmpty()) { System.out.println("No next page."); return; }
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        System.out.println("Forward to: " + currentPage);
    }

    public void refresh() {
        if (currentPage.isEmpty()) System.out.println("No page to refresh.");
        else System.out.println("Refreshed: " + currentPage);
    }

    public void showState() {
        System.out.println("\nCurrent page: " + (currentPage.isEmpty() ? "None" : currentPage));
        System.out.println("Back stack (top first): " + backStack);
        System.out.println("Forward stack (top first): " + forwardStack);
        System.out.println("Unique domains (HashSet): " + uniqueDomains);
        System.out.println("Recent history: " + history);
    }

    public void clearHistory() {
        history.clear();
        uniqueDomains.clear();
        backStack.clear();
        forwardStack.clear();
        currentPage = "";
        System.out.println("Browsing history cleared.");
    }

    private String normalise(String input) {
        String value = input.trim();
        if (value.isEmpty()) return "";
        if (!value.matches("(?i)^https?://.*")) value = "https://" + value;
        try {
            URI uri = URI.create(value);
            if (uri.getHost() == null || !uri.getHost().contains(".")) return "";
            return uri.toString();
        } catch (IllegalArgumentException e) { return ""; }
    }

    private String domainOf(String url) {
        try { return URI.create(url).getHost().replaceFirst("(?i)^www\\.", "").toLowerCase(); }
        catch (Exception e) { return url; }
    }

    public static void main(String[] args) {
        BrowserHistoryManager browser = new BrowserHistoryManager();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== BROWSER HISTORY MANAGER ===");
            System.out.println("1. Visit URL  2. Back  3. Forward  4. Refresh");
            System.out.println("5. Show state  6. Clear history  0. Exit");
            System.out.print("Choose: ");
            while (!sc.hasNextInt()) { sc.next(); System.out.print("Enter a number: "); }
            choice = sc.nextInt(); sc.nextLine();
            switch (choice) {
                case 1: System.out.print("Enter URL: "); browser.visit(sc.nextLine()); break;
                case 2: browser.back(); break;
                case 3: browser.forward(); break;
                case 4: browser.refresh(); break;
                case 5: browser.showState(); break;
                case 6: browser.clearHistory(); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
        sc.close();
    }
}
