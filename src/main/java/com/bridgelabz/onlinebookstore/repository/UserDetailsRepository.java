package com.bridgelabz.onlinebookstore.repository;

import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsModel, UUID> {
    Optional<UserDetailsModel> findByEmail(String email);
    Optional<UserDetailsModel>findBy(UUID userId);
}
