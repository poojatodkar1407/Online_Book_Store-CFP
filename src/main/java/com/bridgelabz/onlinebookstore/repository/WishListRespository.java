package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRespository extends JpaRepository<WishList, Integer> {
//        Optional<WishList> findByUserId(Integer id);
}
