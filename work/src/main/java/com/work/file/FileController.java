package com.work.file;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    @PostMapping("/uploadfile/{filename}")
    public String upload(MultipartFile file,@PathVariable String filename) {
        if (file == null) {
            return "上传失败，未选择文件";
        }
        String fileName = file.getOriginalFilename();
        System.out.println("文件名：" + fileName);
        String filePath = "D:\\upload\\";
//        String filePath = "/root/test";

        File dest = new File(filePath + filename);
        try {
            file.transferTo(dest);
            System.out.println("上传成功！");
        } catch (IOException e) {
            System.out.println("上传异常！" + e);
            return "error";
        }
        return "success";
    }

}
