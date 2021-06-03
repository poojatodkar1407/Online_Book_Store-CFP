package com.bridgelabz.onlinebookstore.repository;


import com.bridgelabz.onlinebookstore.model.OderDetailsModel;
import com.bridgelabz.onlinebookstore.model.UserDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface OrderDetailsRepository extends JpaRepository<OderDetailsModel, UUID> {
     List<OderDetailsModel> findOderDetailsModelByUser(UserDetailsModel userDetailsModel);
}

