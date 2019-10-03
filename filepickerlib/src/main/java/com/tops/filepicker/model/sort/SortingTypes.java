package com.tops.filepicker.model.sort;

import com.tops.filepicker.model.Document;

import java.util.Comparator;


/**
 * Created by chirag on 01/10/19.
 */

public enum SortingTypes {
    name(new NameComparator()), none(null);

    final private Comparator<Document> comparator;

    SortingTypes(Comparator<Document> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Document> getComparator() {
        return comparator;
    }
}
