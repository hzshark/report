package cn.xp.hashpower.controller.home;

import java.io.*;

import cn.xp.hashpower.common.annotation.SystemControllerLog;
import cn.xp.hashpower.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/msic")
public class FileUploadController {

    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }



    @RequestMapping(value="/upload", method=RequestMethod.POST)
    @SystemControllerLog(description = "/msic/upload")
    public ResultVO handleFileUpload(@RequestParam("file")MultipartFile file){
        ResultVO result=new ResultVO();
        if(!file.isEmpty()){
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                result.setFailRepmsg("上传失败,"+e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                result.setFailRepmsg("上传失败,"+e.getMessage());
            }
            result.setSucessRepmsg("上传成功");
        }else{
            result.setFailRepmsg("上传失败，因为文件是空的.");
        }
        return result;
    }

}

