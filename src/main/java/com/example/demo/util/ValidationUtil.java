package com.example.demo.util;

import com.example.demo.dto.KtpRequestDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationUtil {

    public List<String> validateKtpRequest(KtpRequestDTO dto) {
        List<String> errors = new ArrayList<>();

        if (dto.getNomorKtp() == null || dto.getNomorKtp().trim().isEmpty()) {
            errors.add("Nomor KTP tidak boleh kosong");
        } else if (!dto.getNomorKtp().matches("\\d{16}")) {
            errors.add("Nomor KTP harus 16 digit angka");
        }

        if (dto.getNamaLengkap() == null || dto.getNamaLengkap().trim().isEmpty()) {
            errors.add("Nama Lengkap tidak boleh kosong");
        }

        if (dto.getTanggalLahir() == null) {
            errors.add("Tanggal Lahir tidak boleh kosong");
        }

        if (dto.getJenisKelamin() == null || dto.getJenisKelamin().trim().isEmpty()) {
            errors.add("Jenis Kelamin tidak boleh kosong");
        } else if (!dto.getJenisKelamin().equals("Laki-laki") &&
                !dto.getJenisKelamin().equals("Perempuan")) {
            errors.add("Jenis Kelamin harus 'Laki-laki' atau 'Perempuan'");
        }

        return errors;
    }
}