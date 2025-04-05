package com.ddd.scheduleservice.Client;

import com.ddd.scheduleservice.Dto.Response.ImageResponse;
import com.ddd.scheduleservice.Exception.AppException;
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
