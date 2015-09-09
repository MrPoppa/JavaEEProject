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
    private final List<Integer> levelNumbers = new ArrayList<>();
    private final List<String> cities = new ArrayList<>();

    public DropdownData() {
        for (int i = 0; i < 136; ++i) {
            ageNumbers.add(i);
        }
        for (int i = 0; i < 5; ++i) {
            levelNumbers.add(i);
        }

        cities.add("Vänersborg");
        cities.add("Göteborg");
        cities.add("Malmö");
        cities.add("Boxholm");
        cities.add("Luleå");

    }

    public List<Integer> getAgeNumbers() {
        return ageNumbers;
    }

    public List<String> getCities() {
        return cities;
    }

    public List<Integer> getLevelNumbers() {
        return levelNumbers;
    }
    
}
