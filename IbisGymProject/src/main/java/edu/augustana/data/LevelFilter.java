package edu.augustana.data;

import java.util.List;
import java.util.Objects;

public class LevelFilter implements CardFilter{

    private final String selectedLevel;

    public LevelFilter(String selectedOption) {
        this.selectedLevel = selectedOption;
    }

    @Override
    public boolean matches (Card potentialMatchCard) {
        return Objects.equals(potentialMatchCard.getLevel(), selectedLevel);
    }
}
