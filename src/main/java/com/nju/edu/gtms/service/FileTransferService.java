package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.vo.FileTransferVO;
import com.nju.edu.gtms.util.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface FileTransferService {
    void fileUpload(MultipartFile file, FileTransferVO fileTransferVO);

    String fileDownload(String id, String type);
}
