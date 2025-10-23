```markdown
# Java CLI Calendar

This is a small command-line Java calendar application (single-file) that prints a month or a full year calendar using java.time (Java 8+).

Files:
- src/CalendarApp.java

Build and run
1. From the repository root:

Compile:
```
javac -d out src/CalendarApp.java
```

Run (current month):
```
java -cp out CalendarApp
```

Run (specific month in current year):
```
java -cp out CalendarApp 6
```

Run (specific month and year):
```
java -cp out CalendarApp 6 2025
```

Run (full year):
```
java -cp out CalendarApp 2025
```

Notes
- Requires Java 8 or newer.
- The calendar starts weeks on Sunday.
- The program is intentionally simple and easy to extend (add locale, first-day-of-week, events, etc.).
```
