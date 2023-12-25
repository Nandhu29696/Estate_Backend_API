package com.backend.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.demo.modal.Estate;

@Repository
public interface EstateRepository extends JpaRepository<Estate, Integer> {

	@Query(value = "select * from estate where id like %?1", nativeQuery = true)
	Estate getByID(@Param("id") int id);

	@Query(value = "select * from estate where name like %?1", nativeQuery = true)
	Estate FindtByName(@Param("name") String name);

}
