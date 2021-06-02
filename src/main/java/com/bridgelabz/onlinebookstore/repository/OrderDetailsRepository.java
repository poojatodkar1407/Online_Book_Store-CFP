package com.bridgelabz.onlinebookstore.repository;


import com.bridgelabz.onlinebookstore.model.OderDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrderDetailsRepository extends JpaRepository<OderDetailsModel, UUID> {

}

