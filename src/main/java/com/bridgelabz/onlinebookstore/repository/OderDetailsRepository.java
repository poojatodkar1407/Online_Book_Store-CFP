package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.model.CustomerDetails;
import com.bridgelabz.onlinebookstore.model.OderDetailsModel;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OderDetailsRepository extends JpaRepository<OderDetailsModel, UUID> {
    List<OderDetailsModel> findByUser(UserDetailsModel userDetailsModel);
    Optional<OderDetailsModel> findByOrderId(Integer orderId);
}
