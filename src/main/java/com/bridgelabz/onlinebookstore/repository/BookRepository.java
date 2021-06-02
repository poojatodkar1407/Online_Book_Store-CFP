package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookDetailsModel,UUID> {
    Optional<BookDetailsModel> findByBookName(String bookName);
    Optional<BookDetailsModel> findByAuthorName(String authorName);
    //Optional<BookDetailsModel> findBookDetailsModelBy(String bookName,String authorName);
}
