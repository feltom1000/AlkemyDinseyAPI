package com.alkemy.aceleracion.disney.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.aceleracion.disney.auth.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);

}
