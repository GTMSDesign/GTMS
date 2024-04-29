package com.nju.edu.gtms.service.Impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.nju.edu.gtms.dao.*;
import com.nju.edu.gtms.model.po.*;
import com.nju.edu.gtms.service.EmailService;
import com.nju.edu.gtms.service.FileTransferService;
import com.nju.edu.gtms.service.RegistrarService;
import com.nju.edu.gtms.util.WriteModel;
import org.burningwave.core.assembler.StaticComponentContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Service
public class RegistrarServiceImpl implements RegistrarService {
    private final AccountDao accountDao;

    private final StudentDao studentDao;

    private final TeacherDao teacherDao;

    private final EmailService emailService;

    private final ThesisDao thesisDao;

    private final DistributionDao distributionDao;

    private final ThesisDefenseDao thesisDefenseDao;

    private final FileTransferService fileTransferService;
    @Autowired
    public RegistrarServiceImpl(AccountDao accountDao, StudentDao studentDao, TeacherDao teacherDao, EmailService emailService, ThesisDao thesisDao, DistributionDao distributionDao, ThesisDefenseDao thesisDefenseDao, FileTransferService fileTransferService){
        this.accountDao = accountDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.emailService = emailService;
        this.thesisDao = thesisDao;
        this.distributionDao = distributionDao;
        this.thesisDefenseDao = thesisDefenseDao;
        this.fileTransferService = fileTransferService;
    }


    @Override
    public List<AccountPO> getUnenteredStudents() {
        return accountDao.getUnenteredStudents();
    }

    @Override
    public List<AccountPO> getUnenteredTeachers() {
        return accountDao.getUnenteredTeachers();
    }

    @Override
    public void submitStudentPO(StudentPO studentPO) {
        StudentPO student = studentDao.findOneById(studentPO.getStudentId());
        if(student==null){
            studentDao.insertStudentPO(studentPO);
        }
    }

    @Override
    public void submitTeacherPO(TeacherPO teacherPO) {
        TeacherPO teacher = teacherDao.findOneById(teacherPO.getTeacherId());
        if(teacher==null){
            teacherDao.insertTeacherPO(teacherPO);
        }
    }

    @Override
    public void submitPlagiarismCheck(String studentId, String thesisId, String repetition, String conclusion) {
        if(conclusion.equals("不通过")){
            String subject = "论文重复检测率未通过";
            String body = "同学您好，您的论文重复检测率未通过，论文重复率为："+repetition+"%";
            emailService.send(studentId,subject,body);
            thesisDao.setThesisStatue("完成初稿",thesisId);
        }else {
            String subject = "论文重复检测率已通过";
            String body = "同学您好，您的论文重复检测率已通过";
            emailService.send(studentId,subject,body);
            thesisDao.setThesisStatue("通过重复率检测",thesisId);
        }
        PlagiarismCheckPO plagiarismCheckPO = PlagiarismCheckPO.builder()
                .conclusion(conclusion)
                .repetition(repetition)
                .thesisId(thesisId)
                .date(new Date())
                .build();
        thesisDao.addPlagiarismCheck(plagiarismCheckPO);
    }

    @Override
    public List<TeacherPO> getInternalTeachers() {
        return teacherDao.getInternalTeachers();
    }

    @Override
    public List<TeacherPO> getExternalTeachers() {
        return teacherDao.getExternalTeachers();
    }



    @Override
    public List<TeacherPO> getAllTeacher() {
        return teacherDao.getAllTeacher();
    }

    @Override
    public List<StudentPO> getAllStudent() {
        return studentDao.getAllStudent();
    }

    @Override
    public void submitAssignment(String studentId, String teacherId) {
        studentDao.updateDistribution(studentId,"是");
        distributionDao.submitAssignment(studentId,teacherId);
    }

    @Override
    public void deleteAssignment(String studentId, String teacherId) {
        studentDao.updateDistribution(studentId,"否");
        distributionDao.deleteAssignment(studentId,teacherId);
    }

    @Override
    public String generateEvaluation(String thesisId, String studentId) {
        StaticComponentContainer.Modules.exportAllToAll();

        List<WriteModel> createModelList = createModelList(thesisId, studentId);


        String rootFolderPath = "fileStore"; // 根文件夹路径
        String type = "review";
        String extension = ".xlsx";
        String folderPath = rootFolderPath + "/" + type; // 构建文件夹路径
        File folder = new File(folderPath); // 创建文件夹对象
        if (!folder.exists()) {
            folder.mkdirs(); // 创建文件夹及其父文件夹
        }
        String newFilename = thesisId + "_" + type + extension;

        try {
            // 文件输出位置
            OutputStream out = new FileOutputStream(folderPath + "/" + newFilename);

            ExcelWriter writer = EasyExcelFactory.getWriter(out);

            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet1 = new Sheet(1, 0, WriteModel.class);

            // 第一个 sheet 名称
            sheet1.setSheetName("三个一评价");

            // 写数据到 Writer 上下文中
            // 入参1: 创建要写入的模型数据
            // 入参2: 要写入的目标 sheet
            writer.write(createModelList, sheet1);

            // 将上下文中的最终 outputStream 写入到指定文件中
            writer.finish();

            // 关闭流
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
    }
        return fileTransferService.uploadForEvaluation(thesisId, newFilename, folderPath);
    }

    private List<WriteModel> createModelList(String thesisId, String studentId) {

        ThesisPO thesisInfo = thesisDao.findOneByThesisId(thesisId);
        StudentPO studentInfo = studentDao.findOneById(studentId);

        String teacherName = thesisInfo.getTeacherName();
        String studentName = thesisInfo.getStudentName();
        String degree = "硕士";
        String title = thesisInfo.getTitle();
        int excellentCourses = studentInfo.getExcellentCourses();
        int goodCourses = studentInfo.getGoodCourses();
        int fairCourses = studentInfo.getFairCourses();
        int passCourses = studentInfo.getPassCourses();
        String review = thesisDefenseDao.findReviewByThesisId(thesisId);
        String verification = "";
        Date defenseDate = thesisDefenseDao.findLatestDefenseTimeByThesisId(thesisId);

        List<WriteModel> writeModels = new ArrayList<>();
            WriteModel writeModel = WriteModel.builder()
                    .thesisId(thesisId)
                    .teacherName(teacherName)
                    .studentName(studentName)
                    .degree(degree)
                    .title(title)
                    .excellentCourses(excellentCourses)
                    .goodCourses(goodCourses)
                    .fairCourses(fairCourses)
                    .passCourses(passCourses)
                    .review(review)
                    .verification(verification)
                    .date(defenseDate)
                    .build();
            writeModels.add(writeModel);
            return writeModels;
    }

    @Override
    public void assignDefense(String thesis, String secretaryId, String teacherId1, String teacherId2, String teacherId3, String place, Date deadline) {
        thesisDefenseDao.assignDefense(thesis,secretaryId,teacherId1,teacherId2,teacherId3,place,deadline);
        thesisDao.setThesisStatue("答辩中",thesis);

        ThesisPO thesisPO = thesisDao.findOneByThesisId(thesis);
        TeacherPO teacherPO = teacherDao.findOneById(secretaryId);
        String subject = "答辩：" + thesisPO.getStudentName() + "_" + thesisPO.getTitle();
        String body1 = "尊敬的"+ teacherPO.getTeacherName()+"请您参加该同学的论文答辩，答辩时间为"+deadline+"，地点为"+place+"评审链接为http://localhost:5173。";
        teacherPO = teacherDao.findOneById(teacherId1);
        String body2 = "尊敬的"+ teacherPO.getTeacherName()+"请您参加该同学的论文答辩，答辩时间为"+deadline+"，地点为"+place+"评审链接为http://localhost:5173。";
        teacherPO = teacherDao.findOneById(teacherId2);
        String body3 = "尊敬的"+ teacherPO.getTeacherName()+"请您参加该同学的论文答辩，答辩时间为"+deadline+"，地点为"+place+"评审链接为http://localhost:5173。";
        teacherPO = teacherDao.findOneById(teacherId3);
        String body4 = "尊敬的"+ teacherPO.getTeacherName()+"请您参加该同学的论文答辩，答辩时间为"+deadline+"，地点为"+place+"评审链接为http://localhost:5173。";


        try {
            System.out.println(convert(thesisPO.getThesisUrl(),thesisPO.getTitle()));
            emailService.sendAttachmentMail(secretaryId,subject,body1,convert(thesisPO.getThesisUrl(),thesisPO.getTitle()));
            emailService.sendAttachmentMail(teacherId1,subject,body2,convert(thesisPO.getThesisUrl(),thesisPO.getTitle()));
            emailService.sendAttachmentMail(teacherId2,subject,body3,convert(thesisPO.getThesisUrl(),thesisPO.getTitle()));
            emailService.sendAttachmentMail(teacherId3,subject,body4,convert(thesisPO.getThesisUrl(),thesisPO.getTitle()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static MultipartFile convert(String fileUrl,String fileName) throws IOException {
        URL url = new URL(fileUrl);
        URLConnection connection = url.openConnection();
        String contentType = connection.getContentType();
        try (InputStream inputStream = connection.getInputStream()) {
            byte[] bytes = inputStream.readAllBytes();
            return new MockMultipartFile(fileName, fileName, contentType, bytes);
        }
    }

}
