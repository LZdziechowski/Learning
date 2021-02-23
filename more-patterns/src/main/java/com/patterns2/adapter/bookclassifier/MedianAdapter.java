package com.patterns2.adapter.bookclassifier;

import com.patterns2.adapter.bookclassifier.librarya.Book;
import com.patterns2.adapter.bookclassifier.librarya.Classifier;
import com.patterns2.adapter.bookclassifier.libraryb.BookSignature;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MedianAdapter extends MedianAdaptee implements Classifier {

    @Override
    public int publicationYearMedian(Set<Book> bookSet) {
        Map<BookSignature, com.patterns2.adapter.bookclassifier.libraryb.Book> bookMap = new HashMap<>();
        for (Book book : bookSet) {
            BookSignature bookSignature = new BookSignature(book.getSignature());
            com.patterns2.adapter.bookclassifier.libraryb.Book bookB = new com.patterns2.adapter.bookclassifier.libraryb.Book(
                    book.getAuthor(), book.getTitle(), book.getPublicationYear());
            bookMap.put(bookSignature, bookB);
        }
        return medianPublicationYear(bookMap);
    }
}
