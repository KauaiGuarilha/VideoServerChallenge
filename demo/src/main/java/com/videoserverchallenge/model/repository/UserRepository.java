package com.videoserverchallenge.model.repository;

import com.videoserverchallenge.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name = ?1")
    User findByUser(String userName);
}
