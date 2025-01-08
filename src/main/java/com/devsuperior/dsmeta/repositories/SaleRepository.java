package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSellDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleSellDTO(obj.id, obj.amount, obj.date, obj.seller.name) " +
            "FROM Sale obj JOIN obj.seller " +
            "WHERE (obj.date BETWEEN :fromDate AND :toDate) " +
            "AND (:name IS NULL OR LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%')))",
            countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller " +
                    "WHERE (obj.date BETWEEN :fromDate AND :toDate) " +
                    "AND (:name IS NULL OR LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<SaleSellDTO> searchAll(@Param("fromDate") LocalDate fromDate,
                                @Param("toDate") LocalDate toDate,
                                @Param("name") String name,
                                Pageable pageable);
}
