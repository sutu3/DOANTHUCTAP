package com.ddd.scheduleservice.Client;

import com.ddd.scheduleservice.Client.Impl.FileServiceImpl;
import com.ddd.scheduleservice.Dto.Response.ImageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "FileService",fallback = FileServiceImpl.class)
public interface FileService {
    @PostMapping(value = "/images/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ImageResponse uploadFileImage(@RequestPart("file") MultipartFile file);

    }
