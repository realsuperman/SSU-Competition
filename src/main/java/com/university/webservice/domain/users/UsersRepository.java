package com.university.webservice.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> { // 이렇게하면 CRUD가 기본적으로 생김

}
