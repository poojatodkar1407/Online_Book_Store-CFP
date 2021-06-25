package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupons, UUID> {
    Optional<Coupons> findByCouponsType(String coupons);
    Optional<Coupons> findByCouponsTypeAndAndMinimumPrice(String couponType, Double minimumPrice);
}
