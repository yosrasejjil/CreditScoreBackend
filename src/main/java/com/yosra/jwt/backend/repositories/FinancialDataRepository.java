package com.yosra.jwt.backend.repositories;
import com.yosra.jwt.backend.entites.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDataRepository extends JpaRepository<FinancialData,String> {
}
