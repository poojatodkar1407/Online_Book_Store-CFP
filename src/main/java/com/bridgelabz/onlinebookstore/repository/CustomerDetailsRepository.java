package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.CartDetails;
import com.bridgelabz.onlinebookstore.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, UUID> {
}
