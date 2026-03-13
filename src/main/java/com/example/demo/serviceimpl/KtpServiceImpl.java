package com.example.demo.service.impl;

import com.example.demo.dto.KtpRequestDTO;
import com.example.demo.dto.KtpResponseDTO;
import com.example.demo.entity.Ktp;
import com.example.demo.mapper.KtpMapper;
import com.example.demo.repository.KtpRepository;
import com.example.demo.service.KtpService;
import com.example.demo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Autowired
    private KtpMapper ktpMapper;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public KtpResponseDTO createKtp(KtpRequestDTO dto) {
        // Validasi input
        List<String> errors = validationUtil.validateKtpRequest(dto);
        if (!errors.isEmpty()) {
            throw new RuntimeException("Validasi gagal: " + String.join(", ", errors));
        }

        // Cek duplikasi nomor KTP
        if (ktpRepository.existsByNomorKtp(dto.getNomorKtp())) {
            throw new RuntimeException("Nomor KTP sudah terdaftar: " + dto.getNomorKtp());
        }

        Ktp ktp = ktpMapper.toEntity(dto);
        Ktp saved = ktpRepository.save(ktp);
        return ktpMapper.toResponseDTO(saved);
    }

    @Override
    public List<KtpResponseDTO> getAllKtp() {
        return ktpRepository.findAll()
                .stream()
                .map(ktpMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public KtpResponseDTO getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan dengan ID: " + id));
        return ktpMapper.toResponseDTO(ktp);
    }

    @Override
    public KtpResponseDTO updateKtp(Integer id, KtpRequestDTO dto) {
        // Cek data ada
        Ktp existing = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan dengan ID: " + id));

        // Validasi input
        List<String> errors = validationUtil.validateKtpRequest(dto);
        if (!errors.isEmpty()) {
            throw new RuntimeException("Validasi gagal: " + String.join(", ", errors));
        }

        // Cek duplikasi nomorKtp (exclude id saat ini)
        if (ktpRepository.existsByNomorKtpAndIdNot(dto.getNomorKtp(), id)) {
            throw new RuntimeException("Nomor KTP sudah digunakan oleh data lain: " + dto.getNomorKtp());
        }

        // Update field
        existing.setNomorKtp(dto.getNomorKtp());
        existing.setNamaLengkap(dto.getNamaLengkap());
        existing.setAlamat(dto.getAlamat());
        existing.setTanggalLahir(dto.getTanggalLahir());
        existing.setJenisKelamin(dto.getJenisKelamin());

        Ktp updated = ktpRepository.save(existing);
        return ktpMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteKtp(Integer id) {
        if (!ktpRepository.existsById(id)) {
            throw new RuntimeException("Data KTP tidak ditemukan dengan ID: " + id);
        }
        ktpRepository.deleteById(id);
    }
}