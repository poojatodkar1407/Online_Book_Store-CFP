package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.AdminDetailsModel;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminDetailsRepository extends JpaRepository<AdminDetailsModel, UUID> {
    Optional<AdminDetailsModel> findByEmailID(String emailID);
}