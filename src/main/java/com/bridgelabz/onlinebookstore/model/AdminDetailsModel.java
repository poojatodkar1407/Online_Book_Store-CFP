package com.bridgelabz.onlinebookstore.model;

import com.bridgelabz.onlinebookstore.dto.AdminDetailsDto;
import com.bridgelabz.onlinebookstore.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class AdminDetailsModel {

    @Id
    @GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    public UUID adminId;
    public String fullName;
    private String phoneNumber;
    public String emailID;
    public String password;
    public boolean isVerified;

    @JsonIgnore
    private UserRole userRole;


    public AdminDetailsModel(AdminDetailsDto adminDetailsDto) {
        this.fullName = adminDetailsDto.fullName;
        this.emailID=adminDetailsDto.emailID;
        this.password=adminDetailsDto.password;
        this.phoneNumber=adminDetailsDto.phoneNumber;
        this.isVerified=adminDetailsDto.emailVerificationStatus;
        this.userRole=adminDetailsDto.userRole;
    }
}
