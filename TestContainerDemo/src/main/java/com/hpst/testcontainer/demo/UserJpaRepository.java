package com.hpst.testcontainer.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserJpaRepository extends JpaRepository<User, Long> {
}