package domain;

import java.util.List;
import java.util.Optional;

public class F1Calendar {
    private List<F1Race> races;

    public F1Calendar(List<F1Race> races) {
        this.races = races;
    }

    public Optional<F1Race> getRace(String raceName) {
        return races.stream().filter(race -> race.getRaceName().equals(raceName)).findFirst();
    }
}
