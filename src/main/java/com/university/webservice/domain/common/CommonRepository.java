package com.university.webservice.domain.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface CommonRepository extends JpaRepository<Common, Long> { // 이렇게하면 CRUD가 기본적으로 생김
    @Query("SELECT u FROM Common u WHERE u.userId = ?1")
    Stream<Common> findAllDesc(String userId);

    @Query("SELECT u FROM Common u")
    Stream<Common> findAllDesc();
}