package com.example.demo.repository;

import com.example.demo.entity.Ktp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KtpRepository extends JpaRepository<Ktp, Integer> {

    // Cek apakah nomorKtp sudah ada
    boolean existsByNomorKtp(String nomorKtp);

    // Cari berdasarkan nomorKtp
    Optional<Ktp> findByNomorKtp(String nomorKtp);

    // Cek nomorKtp tapi exclude id tertentu (untuk update)
    boolean existsByNomorKtpAndIdNot(String nomorKtp, Integer id);
}