package com.bideafactory.codingtest.Repository;

import com.bideafactory.codingtest.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Booking, Long> {
}
