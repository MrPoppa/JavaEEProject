package com.rappandpoppa.dropdowndata;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Benjamin
 */
@ManagedBean
public class DropdownData {

    private final List<Integer> ageNumbers = new ArrayList<>();
    private final List<Integer> maxStudentNumbers = new ArrayList<>();
    private final List<String> levels = new ArrayList<>();
    private final List<String> cities = new ArrayList<>();
    private final List<String> languages = new ArrayList<>();
    private final List<String> periods = new ArrayList<>();

    public DropdownData() {
        for (int i = 0; i < 136; ++i) {
            ageNumbers.add(i);
        }
        
        maxStudentNumbers.add(28);
        maxStudentNumbers.add(30);
        maxStudentNumbers.add(32);

        levels.add("Beginner");
        levels.add("Novice");
        levels.add("Advanced");
        levels.add("Expert");

        cities.add("Vänersborg");
        cities.add("Göteborg");
        cities.add("Malmö");
        cities.add("Boxholm");
        cities.add("Luleå");

        languages.add("English");
        languages.add("Swedish");
        languages.add("Finnish");

        periods.add("Spring");
        periods.add("Fall");
        
    }

    public List<Integer> getAgeNumbers() {
        return ageNumbers;
    }

    public List<Integer> getMaxStudentNumbers() {
        return maxStudentNumbers;
    }

    public List<String> getCities() {
        return cities;
    }

    public List<String> getLevels() {
        return levels;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getPeriods() {
        return periods;
    }

}
