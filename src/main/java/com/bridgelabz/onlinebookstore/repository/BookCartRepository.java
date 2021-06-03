package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.dto.CartDto;
import com.bridgelabz.onlinebookstore.model.BookCartDetails;
import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.model.OderDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookCartRepository extends JpaRepository<BookCartDetails, UUID> {
    @Query(value = "select * from book_cart_details where cart_details_id = :cartId  ", nativeQuery = true)
    List<BookCartDetails> getCartItems(@Param("cartId") UUID cartId);


}

