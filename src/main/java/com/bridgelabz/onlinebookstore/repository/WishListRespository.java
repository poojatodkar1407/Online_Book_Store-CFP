package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WishListRespository extends JpaRepository<WishList, UUID> {
       Optional<WishList> findByUser_UserId(UUID id);
}
