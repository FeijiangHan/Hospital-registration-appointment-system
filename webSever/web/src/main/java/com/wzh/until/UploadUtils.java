package com.wzh.until;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtils {
    //定义一个目标路径，就是我们要把图片上传到的位置
    private static final String BASE_PATH="D:\\IdeaProjects\\2023\\android\\yygh\\src\\main\\resources\\public\\";
    //定义一个图片回显的路径
    private static final String SERVER_PATH="http://localhost:8091/image/";

    public static String upload(MultipartFile file) {
        //获得上传文件的名称
        String filename = file.getOriginalFilename();
        System.out.println("图片:"+filename);
//        为了保证图片在服务器中名字的唯一性，这个是我呢要用UUID来对filename进行改写
        String uuid = UUID.randomUUID().toString().replace("_","");
        //将生成的UIDD和filename进行拼接
        String newFilename = uuid+"_"+filename;
        //创建一个文件实例对象
        File image = new File(BASE_PATH, filename);
        System.out.println("图片路径"+image);
        //对这个文件进行上传操作
        try {
            file.transferTo(image);
        } catch (IOException e) {
            return null;
        }
        System.out.println("图片名称:"+filename);
        return filename;
    }

}
