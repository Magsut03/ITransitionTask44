package com.example.itransitiontask4.repository;


import com.example.itransitiontask4.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByEmail(String email);

//    @Query("select users from users s where s.states = ?1 or s.states = ?2")
//    List<UserEntity> findAll(String active, String blocked);

}
