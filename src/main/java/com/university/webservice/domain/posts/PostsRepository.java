package com.university.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { // 이렇게하면 CRUD가 기본적으로 생김

}
