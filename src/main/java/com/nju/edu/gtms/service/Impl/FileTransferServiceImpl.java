package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.SessionDao;
import com.nju.edu.gtms.dao.ThesisDao;
import com.nju.edu.gtms.dao.ThesisDefenseDao;
import com.nju.edu.gtms.model.vo.FileTransferVO;
import com.nju.edu.gtms.service.FileTransferService;
import com.nju.edu.gtms.util.Result;
import com.obs.services.ObsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

@Service
public class FileTransferServiceImpl implements FileTransferService {
    private ThesisDao thesisDao;
    private ThesisDefenseDao thesisDefenseDao;
    private SessionDao sessionDao;

    private static final String endPoint = "obs.cn-east-3.myhuaweicloud.com";
    private static final String ak = "LRJMKGBXMMTHZVAQHDZU";
    private static final String sk = "JboaeRrJMOOF0Hf6bwxjLs2nke1obvZjZty04dUn";
    public static String BUCKET_NAME = "marweey";//你创建的桶名

    public void ObsUpload(String bucketName, String key, InputStream inputStream) throws IOException {
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        obsClient.putObject(bucketName, key, inputStream);
        obsClient.close();
    }

    @Autowired
    public FileTransferServiceImpl(ThesisDao thesisDao, ThesisDefenseDao thesisDefenseDao, SessionDao sessionDao){
        this.thesisDao = thesisDao;
        this.thesisDefenseDao = thesisDefenseDao;
        this.sessionDao = sessionDao;
    }
    @Override
    public void fileUpload(MultipartFile file, FileTransferVO fileTransferVO) {
        try {
            // 构建文件保存路径，根据 thesis_id 创建文件夹
            String rootFolderPath = "fileStore"; // 根文件夹路径
            String id = fileTransferVO.getId(); // 获取thesis_id参数
            String type = fileTransferVO.getType();// 获取type参数
            String folderPath = rootFolderPath + "/" + type; // 构建文件夹路径
            File folder = new File(folderPath); // 创建文件夹对象
            if (!folder.exists()) {
                folder.mkdirs(); // 创建文件夹及其父文件夹
            }

            // 构建新的文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = fileTransferVO.getId() + "_" + fileTransferVO.getType() + extension;

            // 保存文件
            Path filePath = Paths.get(folderPath, newFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            InputStream inputStream = file.getInputStream();
            System.out.println(folderPath + "/" + newFilename);
            ObsUpload(BUCKET_NAME, folderPath + "/" + newFilename, inputStream);

            //保存url到thesis库
            String url = "https://marweey.obs.cn-east-3.myhuaweicloud.com/" + folderPath + "/" + newFilename;
            String typeFormatted = type + "_url";
            switch (type) {
                case "opinion":
                    thesisDao.fileUpload(id, typeFormatted, url);
                case "thesis":
                    thesisDao.fileUpload(id, typeFormatted, url);
                case "proposal":
                    thesisDao.fileUpload(id, typeFormatted, url);
                    break;
                case "defense":
                case "resolution":
                    thesisDefenseDao.fileUpload(id, typeFormatted, url);
                    break;
                case "message":
                    sessionDao.fileUpload(id, typeFormatted, url);
                    break;
                default:
                    // Handle unknown type
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String fileDownload(String id, String type){
        try {
            // 指定要下载的文件路径
            String filePath = "";

            String typeFormatted = type + "_url";
            switch (type) {
                case "opinion":
                case "thesis", "proposal":
                    filePath = thesisDao.fileDownload(id, typeFormatted);
                    break;
                case "defense":
                case "resolution":
                    filePath = thesisDefenseDao.fileDownload(id, typeFormatted);
                    break;
                case "session":
                    // 链接已直接传给前端
                    // filePath = sessionDao.fileDownload(id, typeFormatted);
                    break;
                default:
                    // Handle unknown type
                    break;
            }

//            Path path = Paths.get(filePath);
//            String[] fileNameArr = filePath.split("/");
//            String fileName = fileNameArr[fileNameArr.length - 1];
//            // 读取文件内容为字节数组
//            byte[] data = Files.readAllBytes(path);
//
//            // 设置响应头
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            headers.setContentDispositionFormData("attachment", fileName);
//            headers.setContentLength(data.length);
//
//            // 返回 ResponseEntity 包含文件内容和响应头信息
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(data);
            if (filePath.length() == 0){
                return "Error";
            }
            return filePath;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
