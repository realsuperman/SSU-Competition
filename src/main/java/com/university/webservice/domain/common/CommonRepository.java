package com.university.webservice.domain.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;


import java.util.stream.Stream;

public interface CommonRepository extends JpaRepository<Common, Long> { // 이렇게하면 CRUD가 기본적으로 생김
    @Query("SELECT u FROM Common u WHERE u.userId = ?1")
    Stream<Common> findAllDesc(String userId);

    @Query("SELECT u FROM Common u")
    Stream<Common> findAllDesc();

    @Query("SELECT COALESCE(max(u.typeCode)+1,1) FROM Common u  where u.userId=?1")
    Long getCodeValue(String userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Common u WHERE u.userId=?1 AND u.typeCode=?2")
    void deleteCommon(String userId,Long typeCode);

    @Modifying
    @Transactional
    @Query("UPDATE Common u SET u.typeName=?3 WHERE u.userId=?1 AND u.typeCode=?2 AND moddate=CURRENT_TIMESTAMP ")
    void updateCommon(String userId,Long typeCode,String typeName);
}