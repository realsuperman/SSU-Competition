package com.university.webservice.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface UsersRepository extends JpaRepository<Users, Long> { // 이렇게하면 CRUD가 기본적으로 생김
    @Query("SELECT u FROM Users u WHERE u.userId = ?1")
    Stream<Users> findAllDesc(String userId);

    @Query("SELECT u FROM Users u WHERE u.userId = ?1 AND u.userPw =?2")
    Stream<Users> perpectLogin(String uId, String uPw);
}