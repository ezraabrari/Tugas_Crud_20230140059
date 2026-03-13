package com.example.demo.service;

import com.example.demo.dto.KtpRequestDTO;
import com.example.demo.dto.KtpResponseDTO;

import java.util.List;

public interface KtpService {
    KtpResponseDTO createKtp(KtpRequestDTO dto);
    List<KtpResponseDTO> getAllKtp();
    KtpResponseDTO getKtpById(Integer id);
    KtpResponseDTO updateKtp(Integer id, KtpRequestDTO dto);
    void deleteKtp(Integer id);
}