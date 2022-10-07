package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Perfume extends Product {

    private final List<String> smells = new ArrayList<>();

    public Perfume(String name, double price) {
        super(name, price);
    }

    public void addSmell(String smell) {
        smells.add(smell);
    }

    public List<String> getSmells() {
        return Collections.unmodifiableList(smells);
    }

    public void removeSmell(String smell) {
        smells.remove(smell);
    }

}
