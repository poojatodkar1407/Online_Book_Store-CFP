package com.bridgelabz.onlinebookstore.repository;


import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookCartRepository extends JpaRepository<BookCartDetails, UUID> {

   @Query(value = "select * from book_cart_details where cart_details_id = :bookId ", nativeQuery = true)
   List<BookCartDetails> getCartItems(@Param("bookId") UUID bookId);

   List<BookCartDetails> findByBookDetailsModel(UUID id);
   Optional<BookCartDetails> findByBookDetailsModel(BookDetailsModel bookDetailsModel);

}

