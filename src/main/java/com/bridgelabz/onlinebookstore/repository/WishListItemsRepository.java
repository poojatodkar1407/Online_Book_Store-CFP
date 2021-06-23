package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.WishListItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface WishListItemsRepository extends JpaRepository<WishListItems, UUID> {
   List<WishListItems> findByWishListItemsId(UUID wishListId);

    @Transactional
    @Modifying
    @Query(value = "delete from wishlistitems where book_id = :bookId and  wish_list_id = :wishListId", nativeQuery = true)
    Integer deleteWishItems(@Param("bookId") UUID bookId, @Param("wishListId") UUID wishListId);

   List<WishListItems> findByBookAndWishListItemsId(UUID bookId, UUID wishListId);
}
