package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookDetailsModel,UUID> {
    Optional<BookDetailsModel> findByBookName(String bookName);
}
