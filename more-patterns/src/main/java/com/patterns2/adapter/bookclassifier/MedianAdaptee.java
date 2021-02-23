package com.patterns2.adapter.bookclassifier;

import com.patterns2.adapter.bookclassifier.libraryb.Book;
import com.patterns2.adapter.bookclassifier.libraryb.BookSignature;
import com.patterns2.adapter.bookclassifier.libraryb.BookStatistic;
import com.patterns2.adapter.bookclassifier.libraryb.Statistic;

import java.util.Map;

public class MedianAdaptee implements BookStatistic {

    private final Statistic statistic = new Statistic();

    @Override
    public int averagePublicationYear(Map<BookSignature, Book> books) {
        return statistic.averagePublicationYear(books);
    }

    @Override
    public int medianPublicationYear(Map<BookSignature, Book> books) {
        return statistic.medianPublicationYear(books);
    }
}
