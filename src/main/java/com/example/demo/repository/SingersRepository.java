package com.example.demo.repository;
import com.example.demo.entity.Singers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingersRepository extends JpaRepository<Singers, Integer> {
}
