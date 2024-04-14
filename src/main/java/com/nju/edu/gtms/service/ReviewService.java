package com.nju.edu.gtms.service;

import com.nju.edu.gtms.model.vo.ReviewVO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ReviewService {

    List<ReviewVO> getThesisByReviewerId(String teacherId);
}
