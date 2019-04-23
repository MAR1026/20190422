package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.Attachment;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import kr.hs.dgsw.web01blog.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/attachment/upload/{postid}")
    public ResponseFormat upload(@RequestBody List<MultipartFile> srcFile, @PathVariable Long postId) {
        List<Attachment> result = this.attachmentService.upload(srcFile, postId);

        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.ATTACHMENT_STORED, result);
            return responseFormat;
        }

        return new ResponseFormat(ResponseType.FAIL, null);
    }

    @GetMapping("/attachment/download/{type}/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String type, @PathVariable Long id) {
        response = attachmentService.download(response, type, id);
    }


}
