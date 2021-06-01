package com.bridgelabz.onlinebookstore.utils;

import com.bridgelabz.onlinebookstore.model.BookDetailsModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum  BookStoreEnum {
    LOW_TO_HIGH {
        @Override
        public List<BookDetailsModel> getSortedData(List<BookDetailsModel> sortedBookList) {
            return sortedBookList.stream()
                    .sorted(Comparator.comparing(bookDetails -> bookDetails.bookPrice))
                    .collect(Collectors.toList());
        }
    },

    HIGH_TO_LOW {
        @Override
        public List<BookDetailsModel> getSortedData(List<BookDetailsModel> sortedBookList) {
            List<BookDetailsModel> allBooks= sortedBookList.stream()
                    .sorted(Comparator.comparing(bookDetails -> bookDetails.bookPrice))
                    .collect(Collectors.toList());
            Collections.reverse(allBooks);
            return allBooks;
        }
    },

    NEWEST_ARRIVALS {
        @Override
        public List<BookDetailsModel> getSortedData(List<BookDetailsModel> sortedBookList) {
            List<BookDetailsModel> allBooks= sortedBookList.stream()
                    .sorted(Comparator.comparing(bookDetails -> bookDetails.publishingYear))
                    .collect(Collectors.toList());
            Collections.reverse(allBooks);
            return allBooks;
        }
    };

    public abstract List<BookDetailsModel> getSortedData(List<BookDetailsModel> sortedBookList);
}