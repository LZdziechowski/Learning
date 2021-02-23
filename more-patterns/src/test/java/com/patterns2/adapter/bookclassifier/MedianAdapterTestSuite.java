package com.patterns2.adapter.bookclassifier;

import com.patterns2.adapter.bookclassifier.librarya.Book;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MedianAdapterTestSuite {

    @Test
    void testPublicationYearMedian() {
        //Given
        MedianAdapter medianAdapter = new MedianAdapter();
        Set<Book> bookSet = new HashSet<>();
        Set<Book> emptySet = new HashSet<>();
        bookSet.add(new Book("author1", "title1", 2000, "12345"));
        bookSet.add(new Book("author2", "title2", 1998, "1dada1"));
        bookSet.add(new Book("author3", "title3", 2006, "11eda4"));
        bookSet.add(new Book("author4", "title4", 2014, "1brsa2"));
        //When
        int median = medianAdapter.publicationYearMedian(bookSet);
        int medianEmptySet = medianAdapter.publicationYearMedian(emptySet);
        //Then
        assertEquals(2003, median, 0);
        assertEquals(0, medianEmptySet, 0);
    }
}
