package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.WishListItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WishListItemsRepository extends JpaRepository<WishListItems, Integer> {
//    List<WishListItems> findAllByWishListWishId(Integer wishListId);
//    List<WishListItems> findAllByBookId(Integer bookId);
//
//    @Transactional
//    @Modifying
//    @Query(value = "delete from wishlistitems where book_id = :bookId and  wish_list_id = :wishListId", nativeQuery = true)
//    Integer deleteWishItems(@Param("bookId") Integer bookId, @Param("wishListId") Integer wishListId);
//
//    List<WishListItems> findByBookIdAndWishListWishId(Integer bookId, Integer wishListId);
}
