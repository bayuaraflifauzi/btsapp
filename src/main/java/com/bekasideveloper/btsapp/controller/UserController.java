package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.model.Pengajuan;
import com.bekasideveloper.btsapp.model.PengajuanId;
import com.bekasideveloper.btsapp.model.Perusahaan;
import com.bekasideveloper.btsapp.model.User;
import com.bekasideveloper.btsapp.service.UserService;
import com.bekasideveloper.btsapp.util.FileUploader;
import com.bekasideveloper.btsapp.wrapper.input.AjuanUserWrapper;
import com.bekasideveloper.btsapp.wrapper.input.UpdateStatusAjuanWrapper;
import com.bekasideveloper.btsapp.wrapper.input.UserRegistrationWrapper;
import com.bekasideveloper.btsapp.wrapper.output.CustomMessage;
import com.bekasideveloper.btsapp.wrapper.output.HistoryAjuanWrapper;
import com.bekasideveloper.btsapp.wrapper.output.PerusahaanWrapper;
import com.sun.net.httpserver.Headers;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register-user")
    public ResponseEntity<?> getAllKecamatan(@RequestBody UserRegistrationWrapper wrapper){
        logger.info("register user");

        String userId = String.valueOf(new Date().getTime());
        User user = new User();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(wrapper.getPassword()));
        user.setRole(1);

        Perusahaan perusahaan = new Perusahaan();
        perusahaan.setUserId(userId);
        perusahaan.setEmail(wrapper.getEmail());
        perusahaan.setNpwpd(wrapper.getNpwpd());
        perusahaan.setNamaPerusahaan(wrapper.getNamaPerusahaan());
        perusahaan.setNamaPendaftar(wrapper.getNamaPendaftar());
        perusahaan.setAlamatPerusahaan(wrapper.getAlamatPerusahaan());
        perusahaan.setNoTelp(wrapper.getNoTelp());

        userService.createUser(user, perusahaan);

        return new ResponseEntity<>(new CustomMessage("pendaftaran berhasil"), HttpStatus.OK);
    }

    @GetMapping("/get-detail-perusahaan/{userName}")
    public ResponseEntity<?> getDetailPerusahaan(@PathVariable("userName") String userName) {
        logger.info("get detail perusahaan "+userName);

        Perusahaan perusahaan = userService.getPerusahaan(userName);
        if (perusahaan==null)
            return new ResponseEntity<>(new CustomMessage("detail perusahaan tidak ditemukan"), HttpStatus.OK);
        PerusahaanWrapper wrapper
                = new PerusahaanWrapper(
                        perusahaan.getEmail(),
                        perusahaan.getNamaPendaftar(),
                        perusahaan.getNamaPerusahaan(),
                        perusahaan.getAlamatPerusahaan(),
                        perusahaan.getNoTelp(),
                        perusahaan.getNpwpd());

        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @PostMapping("/create-ajuan")
    public  ResponseEntity<?> createAjuan(@RequestBody AjuanUserWrapper wrapper) {
        logger.info("create ajuan");
        User pengaju = userService.getUser(wrapper.getUserId());

        Long ajuanId = new Date().getTime();
        Pengajuan pengajuan = new Pengajuan();
        pengajuan.setPengajuanId(new PengajuanId(ajuanId, pengaju.getUserId()));
        pengajuan.setIdKecamatan(wrapper.getIdKecamatan());
        pengajuan.setKecamatan(wrapper.getKecamatan());
        pengajuan.setLatitudeAjuan(wrapper.getLatitude());
        pengajuan.setLongitudeAjuan(wrapper.getLongitude());
        //0 pending
        //1 diterima
        //2 ditolak
        pengajuan.setStatus(0);
        pengajuan.setStatusAktif(1);
        pengajuan.setScanNpwpdFile(ajuanId+"A."+ FilenameUtils.getExtension(wrapper.getNamaFileNPWPD()));
        pengajuan.setDokumenPengajuan(ajuanId+"B."+ FilenameUtils.getExtension(wrapper.getNamaDokumenAjuan()));

        userService.createAjuan(pengajuan);

        return new ResponseEntity<>(new CustomMessage(String.valueOf(ajuanId)), HttpStatus.OK);
    }

    @PostMapping("/create-ajuan-file")
    public  ResponseEntity<?> createAjuanFile(
            @RequestParam("file_npwpd") MultipartFile fileNPWPD,
            @RequestParam("file_dokumen_ajuan") MultipartFile fileDokumenAjuan) {
        logger.info("create ajuan file");

        FileUploader fileUploader = new FileUploader();
        fileUploader.uploadDocuments(fileNPWPD, FilenameUtils.removeExtension(fileNPWPD.getOriginalFilename()));
        fileUploader.uploadDocuments(fileDokumenAjuan, FilenameUtils.removeExtension(fileDokumenAjuan.getOriginalFilename()));

        return new ResponseEntity<>(new CustomMessage("ajuan berhasil dibuat"), HttpStatus.OK);
    }

    @GetMapping("/get-ajuan-history/{idPengaju}")
    public ResponseEntity<?> getAjuanHistory(@PathVariable("idPengaju") String idPengaju) {
        logger.info("get ajuan history "+idPengaju);

        List<HistoryAjuanWrapper> ajuanHistory = new ArrayList<>();
        User user = userService.getUser(idPengaju);
        if (user==null)
            return new ResponseEntity<>(ajuanHistory, HttpStatus.OK);
        List<Pengajuan> pengajuanList = userService.getAjuanHistory(user.getUserId());
        for (Pengajuan p : pengajuanList) {
            if (p.getStatusAktif() == 1)
                ajuanHistory.add(new HistoryAjuanWrapper(
                        String.valueOf(p.getPengajuanId().getIdPengajuan()),
                        p.getIdKecamatan(),
                        p.getKecamatan(),
                        p.getLongitudeAjuan(),
                        p.getLatitudeAjuan(),
                        p.getPengajuanId().getIdPengajuan(),
                        p.getStatus()
                ));
        }

        return new ResponseEntity<>(ajuanHistory, HttpStatus.OK);
    }

    @DeleteMapping("/delete-ajuan/{ajuanId}")
    public ResponseEntity<?> updateStatusAjuan(@PathVariable("ajuanId") String ajuanId) {
        logger.info("update status ajuan");

        Pengajuan p = userService.getAjuanById(ajuanId);
        if (p==null)
            return new ResponseEntity<>(new CustomMessage("data tidak ditemukan"), HttpStatus.INTERNAL_SERVER_ERROR);
        p.setStatusAktif(0);

        userService.createAjuan(p);

        return new ResponseEntity<>(new CustomMessage("ajuan berhasil dihapus"), HttpStatus.OK);
    }

    @GetMapping("/get-file-npwpd/{idAjuan}")
    public ResponseEntity<?> getFileNPWPDAjuan(@PathVariable("idAjuan") String idAjuan) {
        final String documentFolderPath = "/home/user/documen_ajuan/";
        Pengajuan p = userService.getAjuanById(idAjuan);

        if (p==null)
            return new ResponseEntity<>(new CustomMessage("data ajuan tidak ditemukan"), HttpStatus.OK);

        String documentPath = documentFolderPath+p.getScanNpwpdFile();

        byte[] file = null;
        File filePath = new File(documentPath);
        try {
            file = Files.readAllBytes(filePath.toPath());
        } catch (IOException e) {
            logger.error("failed retreive file");
            return new ResponseEntity<>(new CustomMessage("file NPWPD tidak ditemukan"), HttpStatus.OK);
        }
        HttpHeaders headers = new HttpHeaders();
        setHeader(headers,p);

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    @GetMapping("/get-file-dokumen-ajuan/{idAjuan}")
    public ResponseEntity<?> getFileDokumenAjuan(@PathVariable("idAjuan") String idAjuan) {
        final String documentFolderPath = "/home/user/documen_ajuan/";
        Pengajuan p = userService.getAjuanById(idAjuan);

        if (p==null)
            return new ResponseEntity<>(new CustomMessage("data ajuan tidak ditemukan"), HttpStatus.OK);

        String documentPath = documentFolderPath+p.getDokumenPengajuan();

        byte[] file = null;
        File filePath = new File(documentPath);
        try {
            file = Files.readAllBytes(filePath.toPath());
        } catch (IOException e) {
            logger.error("failed retreive file");
            return new ResponseEntity<>(new CustomMessage("file NPWPD tidak ditemukan"), HttpStatus.OK);
        }

        HttpHeaders headers = new HttpHeaders();
        setHeader(headers, p);

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    private void setHeader(HttpHeaders headers, Pengajuan p) {
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("content-disposition", "inline;filename=" +p.getScanNpwpdFile());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    }

}
