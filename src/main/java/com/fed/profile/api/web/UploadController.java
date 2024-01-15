package com.fed.profile.api.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UploadController {

    @GetMapping("/upload.html")
    public String showUploadPage() {
        return "upload";
    }
    
}
