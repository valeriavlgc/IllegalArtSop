package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Shop;

@Repository
@Transactional
public interface Database extends JpaRepository<Shop, Integer> {

}
