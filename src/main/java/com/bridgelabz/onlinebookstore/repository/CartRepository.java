package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.BookDetailsModel;
import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartDetails,UUID> {
        List<CartDetails>findByUserDetailsModel(UserDetailsModel userDetailsModel);
   //Optional<CartDetails> findByUserDetailsModel1(UserDetailsModel userDetailsModel);
 }
