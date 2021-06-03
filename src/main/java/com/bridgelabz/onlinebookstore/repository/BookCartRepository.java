package com.bridgelabz.onlinebookstore.repository;


import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.UUID;

@Repository
public interface BookCartRepository extends JpaRepository<BookCartDetails, UUID> {

   @Query(value = "select * from book_cart_details where cart_details_id = :cartId  ", nativeQuery = true)
   List<BookCartDetails> getCartItems(@Param("cartId") UUID cartId);

   List<BookCartDetails> findByBookDetailsModel(BookCartDetails bookCartDetails);
}

