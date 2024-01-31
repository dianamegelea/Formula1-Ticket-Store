package domain;

import java.util.*;

public class F1Calendar {
    private Map<String, F1Race> races;

    public F1Calendar() {
        races = new HashMap<>();
        races.put("Bahrain", new F1Race(1, "Bahrain", "Bahrain", new Date(2024, Calendar.MARCH, 2), 50000));
        races.put("Jeddah", new F1Race(2, "Jeddah", "Saudi Arabia", new Date(2024, Calendar.MARCH, 9), 70000));
        races.put("Australia", new F1Race(3, "Australia", "Australia", new Date(2024, Calendar.MARCH, 24), 90000));
        races.put("Suzuka", new F1Race(4, "Suzuka", "Japan", new Date(2024, Calendar.APRIL, 7), 100000));
        races.put("China", new F1Race(5, "China", "China", new Date(2024, Calendar.APRIL, 21), 70000));
        races.put("Miami", new F1Race(6, "Miami", "USA", new Date(2024, Calendar.MAY, 5), 70000));
        races.put("Imola", new F1Race(7, "Imola", "Italy", new Date(2024, Calendar.MAY, 19), 90000));
        races.put("Monaco", new F1Race(8, "Monaco", "Monaco", new Date(2024, Calendar.MAY, 26), 60000));
        races.put("Canada", new F1Race(9, "Canada", "Canada", new Date(2024, Calendar.JUNE, 9), 75000));
        races.put("Spain", new F1Race(10, "Spain", "Spain", new Date(2024, Calendar.JUNE, 23), 80000));
        races.put("Austria", new F1Race(11, "Austria", "Austria", new Date(2024, Calendar.JUNE, 30), 70000));
        races.put("Silverstone", new F1Race(12, "Silverstone", "UK", new Date(2024, Calendar.JULY, 7), 90000));
        races.put("Hungary", new F1Race(13, "Hungary", "Hungary", new Date(2024, Calendar.JULY, 21), 80000));
        races.put("Spa", new F1Race(14, "Spa", "Belgium", new Date(2024, Calendar.JULY, 28), 100000));
        races.put("Zandvoort", new F1Race(15, "Zandvoort", "Netherlands", new Date(2024, Calendar.AUGUST, 25), 70000));
        races.put("Monza", new F1Race(16, "Monza", "Italy", new Date(2024, Calendar.SEPTEMBER, 1), 100000));
        races.put("Baku", new F1Race(17, "Baku", "Azerbaijan", new Date(2024, Calendar.SEPTEMBER, 15), 80000));
        races.put("Singapore", new F1Race(18, "Singapore", "Singapore", new Date(2024, Calendar.SEPTEMBER, 22), 70000));
        races.put("Austin", new F1Race(19, "Austin", "USA", new Date(2024, Calendar.OCTOBER, 20), 90000));
        races.put("Mexico", new F1Race(20, "Mexico", "Mexico", new Date(2024, Calendar.OCTOBER, 27), 80000));
        races.put("Brazil", new F1Race(21, "Brazil", "Brazil", new Date(2024, Calendar.NOVEMBER, 3), 80000));
        races.put("Las Vegas", new F1Race(22, "Las Vegas", "USA", new Date(2024, Calendar.NOVEMBER, 23), 70000));
        races.put("Qatar", new F1Race(23, "Qatar", "Qatar", new Date(2024, Calendar.DECEMBER, 1), 70000));
        races.put("Abu Dhabi", new F1Race(24, "Abu Dhabi", "UAE", new Date(2024, Calendar.MAY, 20), 70000));
    }

    public F1Race getRace(String raceName) {
        return races.get(raceName);
    }

    public List<F1Race> getAllRaces() {
        return new ArrayList<>(races.values());
    }
}
