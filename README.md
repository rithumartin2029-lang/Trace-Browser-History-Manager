# Trace — Browser History & Navigation Manager

A minimalist browser-navigation simulator for a Data Structures project. The web interface demonstrates multiple tabs, Back/Forward navigation, session history, searchable history, bookmarks, and unique-domain analytics.

## Files
- `index.html` — page structure
- `style.css` — responsive, minimal sage/white/charcoal theme
- `app.js` — interactive front-end demonstration
- `BrowserHistoryManager.java` — Java implementation of the core data-structure logic using `ArrayDeque`, `HashSet`, and `ArrayList`

## Run the website
Open `index.html` in a modern browser. The website is a local interactive demo: website pages are represented by local previews rather than loading real external websites. Demo state is held in memory and resets when the page is reloaded.

## Run the Java program
Install a JDK, open a terminal in this folder, then run:

```bash
javac BrowserHistoryManager.java
java BrowserHistoryManager
```

## Data structures demonstrated
- **Two `ArrayDeque` stacks:** `backStack` and `forwardStack` implement LIFO navigation.
- **`HashSet`:** stores unique domains without duplicates (average O(1) membership operations).
- **`ArrayList`:** stores recent browsing history and supports listing/reopening entries.

## Important note
The HTML demo and Java console implementation are separate demonstrations of the same project concept; the current front end does not call Java endpoints. A fully integrated Java-backed website would need a Java web server/API connecting the UI actions to the Java data structures.
