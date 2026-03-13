package com.example.demo.controller;

import com.example.demo.dto.KtpRequestDTO;
import com.example.demo.dto.KtpResponseDTO;
import com.example.demo.model.ApiResponse;
import com.example.demo.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ktp")
@CrossOrigin(origins = "*")
public class KtpController {

    @Autowired
    private KtpService ktpService;

    // POST /ktp — Tambah data KTP baru
    @PostMapping
    public ResponseEntity<ApiResponse<KtpResponseDTO>> createKtp(@RequestBody KtpRequestDTO dto) {
        try {
            KtpResponseDTO result = ktpService.createKtp(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Data KTP berhasil ditambahkan", result));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // GET /ktp — Ambil semua data KTP
    @GetMapping
    public ResponseEntity<ApiResponse<List<KtpResponseDTO>>> getAllKtp() {
        try {
            List<KtpResponseDTO> result = ktpService.getAllKtp();
            return ResponseEntity.ok(ApiResponse.success("Berhasil mengambil semua data KTP", result));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // GET /ktp/{id} — Ambil data KTP berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<KtpResponseDTO>> getKtpById(@PathVariable Integer id) {
        try {
            KtpResponseDTO result = ktpService.getKtpById(id);
            return ResponseEntity.ok(ApiResponse.success("Data KTP ditemukan", result));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // PUT /ktp/{id} — Update data KTP
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<KtpResponseDTO>> updateKtp(
            @PathVariable Integer id,
            @RequestBody KtpRequestDTO dto) {
        try {
            KtpResponseDTO result = ktpService.updateKtp(id, dto);
            return ResponseEntity.ok(ApiResponse.success("Data KTP berhasil diperbarui", result));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // DELETE /ktp/{id} — Hapus data KTP
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteKtp(@PathVariable Integer id) {
        try {
            ktpService.deleteKtp(id);
            return ResponseEntity.ok(ApiResponse.success("Data KTP berhasil dihapus", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}