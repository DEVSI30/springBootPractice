package com.example.practice.repository;

import com.example.practice.model.SomeItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SomeItemRepository extends JpaRepository<SomeItemEntity, String> {

}
