package com.casc.sczd.controller;

import com.casc.sczd.constant.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return " hello spring boot !";
    }

    @Resource
    Global global;

//    @Autowired
//    BookService bookService;
//
//    @GetMapping("/book")
//    public Book getbook() {
////        Book book=new Book();
////        book.setId(3);
////        book.setName("三國演義");
////        book.setAuthor("叢治志");
////        book.setPrice(23.99);
////        book.setDate(new Date());
//
//        return bookService.get(2);
//    }

    @GetMapping("/uploadfile")
    public ModelAndView file() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("uploadfile");
        return mv;
    }



    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    @PostMapping("/upload")
    public String upload(MultipartFile file , HttpServletRequest req) throws IOException {
        String format =  sdf.format(new Date());
        String oldName = file.getOriginalFilename();
        String newName = format+UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
        File newFile = new File(global.getUploadFileLocation()+newName);
        if(!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdir();
        }
        file.transferTo(newFile);
        return newName;
    }


    @GetMapping("/uploadfiles")
    public ModelAndView files() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("uploadfiles");
        return mv;
    }
    //鎖文件上傳
    @PostMapping("/uploads")
    public String uploads(MultipartFile[] files , HttpServletRequest req) throws IOException {
        for (int i=0;i<files.length;i++){
            MultipartFile file = files[i];
            String format =  sdf.format(new Date());
            String oldName = file.getOriginalFilename();
            String newName = format+UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
            File newFile = new File(global.getUploadFileLocation()+newName);
            if(!newFile.getParentFile().exists()){
                newFile.getParentFile().mkdir();
            }
            file.transferTo(newFile);
            }
            return "success";
    }
}
