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

    private final List<Integer> numbers = new ArrayList<>();
    private final List<String> cities = new ArrayList<>();

    public DropdownData() {
        for (int i = 0; i < 136; ++i) {
            numbers.add(i);
        }

        cities.add("Vänersborg");
        cities.add("Göteborg");
        cities.add("Malmö");
        cities.add("Boxholm");
        cities.add("Luleå");

    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public List<String> getCities() {
        return cities;
    }
    
}
