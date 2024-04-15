package com.nju.edu.gtms.service.Impl;

import com.nju.edu.gtms.dao.ThesisDao;
import com.nju.edu.gtms.model.po.ThesisPO;
import com.nju.edu.gtms.model.vo.FileStoreVO;
import com.nju.edu.gtms.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ThesisServiceImpl implements ThesisService {
    private ThesisDao thesisDao;

    @Autowired
    public ThesisServiceImpl(ThesisDao thesisDao){this.thesisDao=thesisDao;}

    @Override
    public List<ThesisPO> getThesisByTeacherId(String teacherId){
        return thesisDao.findThesisByTeacherId(teacherId);
    }

    @Override
    public List<ThesisPO> getThesisByTeacherIdAndStatus(String teacherId, String status) {
        return thesisDao.findThesisByTeacherIdAndStatus(teacherId,status);
    }

    @Override
    public void fileStore(MultipartFile file, FileStoreVO fileStoreVO) {
        System.out.println(file);
        System.out.println(fileStoreVO);
        try {
            // 构建文件保存路径，根据 thesis_id 创建文件夹
            String rootFolderPath = "fileStore"; // 根文件夹路径
            String thesisId = fileStoreVO.getThesis_id(); // 获取thesis_id参数
            String folderPath = rootFolderPath + "/" + thesisId; // 构建文件夹路径
            File folder = new File(folderPath); // 创建文件夹对象
            if (!folder.exists()) {
                folder.mkdirs(); // 创建文件夹及其父文件夹
            }

            // 构建新的文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = fileStoreVO.getThesis_id() + "_" + fileStoreVO.getType() + extension;

            // 保存文件
            Path filePath = Paths.get(folderPath, newFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
        }
    }
}

