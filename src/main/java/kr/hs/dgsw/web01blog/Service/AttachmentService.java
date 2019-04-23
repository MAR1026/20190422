package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AttachmentService {

    public List<Attachment> upload(List<MultipartFile> srcFile, Long postId);
    public HttpServletResponse download(HttpServletResponse response, String type, Long id);

}
