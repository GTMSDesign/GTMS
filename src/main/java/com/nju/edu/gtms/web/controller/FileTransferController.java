package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.vo.FileTransferVO;
import com.nju.edu.gtms.service.FileTransferService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/filetransfer")
public class FileTransferController {
    private final FileTransferService fileTransferService;
    @Autowired
    public FileTransferController(FileTransferService fileTransferService){
        this.fileTransferService = fileTransferService;
    }

    @PostMapping("/upload")
    public Result fileUpload(@RequestPart("file") MultipartFile file, @RequestParam("id") String id, @RequestParam("type") String type){
        FileTransferVO fileTransferVO = new FileTransferVO(id, type);
        fileTransferService.fileUpload(file, fileTransferVO);
        return Result.success();
    }

    @GetMapping("/download")
    public Result fileDownload(@RequestParam("id") String id, @RequestParam("type") String type){
        String obsUrl = fileTransferService.fileDownload(id, type);
        return Result.success(obsUrl);
    }
}
