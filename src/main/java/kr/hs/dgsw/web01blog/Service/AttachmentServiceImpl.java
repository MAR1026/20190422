package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Attachment;
import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Repository.AttachmentRepository;
import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    private int pictureNum = 0;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Attachment> upload(List<MultipartFile> srcFile, Long postId) {
        List<Attachment> attachments = new ArrayList<>();

        for(MultipartFile file : srcFile) {
            String storedPath = "C:\\Users\\User\\IdeaProjects\\web_01_326\\upload\\" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/")) + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            try {
                File destFile = new File(storedPath);
                destFile.getParentFile().mkdirs();
                file.transferTo(destFile);

                Attachment savedAttachment = new Attachment(storedPath, postId);
                attachmentRepository.save(savedAttachment);

                attachments.add(savedAttachment);
            } catch (IOException e) {
                return null;
            }
        }

        return attachments;
    }

    @Override
    public HttpServletResponse download(HttpServletResponse response, String type, Long id) {
        try
        {
            String filepath = "";

            switch (type) {
                case "user" :
                    User user = this.userRepository.findById(id).orElse(null);

                    if(user != null) {
                        filepath = user.getProfilePath();
                    }
                    break;

                case "comment" :
                    Post comment = this.postRepository.findById(id).orElse(null);

                    if(comment != null) {
                        filepath = comment.getPictures().get(pictureNum).getStoredPath();
                        pictureNum++;

                        if(comment.getPictures().size() == (pictureNum - 1))
                            pictureNum = 0;
                    }
                    break;
            }


            File file = new File(filepath);
            if(!file.exists()) {
                return null;
            }

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());

            if(mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline;");
            response.setContentLength((int)file.length());

            InputStream is = null;
            is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, response.getOutputStream());

            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
