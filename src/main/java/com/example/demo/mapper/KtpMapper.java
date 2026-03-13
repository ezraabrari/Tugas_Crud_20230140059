package com.example.demo.mapper;

import com.example.demo.dto.KtpRequestDTO;
import com.example.demo.dto.KtpResponseDTO;
import com.example.demo.entity.Ktp;
import org.springframework.stereotype.Component;

@Component
public class KtpMapper {

    // Konversi Request DTO → Entity
    public Ktp toEntity(KtpRequestDTO dto) {
        Ktp ktp = new Ktp();
        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());
        return ktp;
    }

    // Konversi Entity → Response DTO
    public KtpResponseDTO toResponseDTO(Ktp ktp) {
        return new KtpResponseDTO(
                ktp.getId(),
                ktp.getNomorKtp(),
                ktp.getNamaLengkap(),
                ktp.getAlamat(),
                ktp.getTanggalLahir(),
                ktp.getJenisKelamin()
        );
    }
}