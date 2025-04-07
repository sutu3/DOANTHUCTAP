package com.ddd.scheduleservice.Client.Impl;

import com.ddd.scheduleservice.Client.FileService;
import com.ddd.scheduleservice.Dto.Response.ImageResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileServiceImpl implements FileService {

    @Override
    public ImageResponse uploadFileImage(MultipartFile file) {
        System.out.println("File Service Not Found");
        return null;
    }
}
