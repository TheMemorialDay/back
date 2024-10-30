package com.korit.thememorialday.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


public interface FileService {
    String upload(MultipartFile file);
    Resource getFile(String fileName);
}
