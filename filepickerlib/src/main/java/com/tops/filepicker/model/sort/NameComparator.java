package com.tops.filepicker.model.sort;


import com.tops.filepicker.model.Document;

import java.util.Comparator;


/**
 * Created by chirag on 01/10/19.
 */

public class NameComparator implements Comparator<Document> {

    protected NameComparator() {
    }

    @Override
    public int compare(Document o1, Document o2) {
        return o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase());
    }
}
