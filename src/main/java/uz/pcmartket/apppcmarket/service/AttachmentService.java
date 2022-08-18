package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pcmartket.apppcmarket.entity.Attachment;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.repository.AttachementRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    private AttachementRepository attachementRepository;


    public ApiResponse uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        if (!fileNames.hasNext()) return new ApiResponse("File not found", false);

        List<MultipartFile> files = request.getFiles(fileNames.next());
        Iterator<MultipartFile> fileIterator = files.iterator();

        while (fileIterator.hasNext()) {
            MultipartFile file = fileIterator.next();
            Attachment attachment = new Attachment();
            attachment.setContentType(file.getContentType());
            attachment.setName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setBytes(file.getBytes());
            attachementRepository.save(attachment);
        }

        return new ApiResponse("File seccessfully saved", true);
    }

    public void downlaodFile(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachementRepository.findById(id);
        if (!optionalAttachment.isPresent()) return;

        response.setContentType(optionalAttachment.get().getContentType());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + optionalAttachment.get().getName() + "\"");
        FileCopyUtils.copy(optionalAttachment.get().getBytes(), response.getOutputStream());
    }

    public ApiResponse editFile(Integer id, MultipartHttpServletRequest request) throws IOException {
        Optional<Attachment> optionalAttachment = attachementRepository.findById(id);
        if (!optionalAttachment.isPresent()) return new ApiResponse("File not found", false);

        Iterator<String> fileNames = request.getFileNames();
        if (!fileNames.hasNext()) return new ApiResponse("New file not found", false);

        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = optionalAttachment.get();
        attachment.setName(file.getName());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        attachment.setBytes(file.getBytes());
        attachment = attachementRepository.save(attachment);

        return new ApiResponse("File seccessfully edited", true, attachment);
    }

    public ApiResponse deleteFile(Integer id) {
        Optional<Attachment> optionalAttachment = attachementRepository.findById(id);
        if (!optionalAttachment.isPresent()) return new ApiResponse("File not found", false);

        attachementRepository.delete(optionalAttachment.get());
        return new ApiResponse("File seccessfully deleted", true);
    }

}
