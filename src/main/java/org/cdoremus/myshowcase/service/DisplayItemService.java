package org.cdoremus.myshowcase.service;

import org.cdoremus.myshowcase.model.DisplayItem;
import org.cdoremus.myshowcase.model.User;
import org.cdoremus.myshowcase.repository.DisplayItemRepository;
import org.cdoremus.myshowcase.repository.UserRepository;
import org.cdoremus.myshowcase.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
@Service
public class DisplayItemService {

    private static final Logger LOG = LoggerFactory.getLogger(DisplayItemService.class);

    @Autowired
    private DisplayItemRepository repository;
    @Autowired
    private UserRepository userRepository;

    public DisplayItemService() {
    }

    public DisplayItem save(DisplayItem item, String username) {
        User user = userRepository.findByLoginId(username);
        item.setUser(user);

        return repository.save(item);
    }

    public DisplayItem findItemById(long itemId) {
        return repository.findOne(itemId);
    }

    public List<DisplayItem> findItemsByLoginId(String loginId) {
        List<DisplayItem> items = repository.findByLoginId(loginId);
        return items;
    }

    public List<DisplayItem> findItemsByUserId(long userId) {
        return repository.findByUserId(userId);
    }

    public DisplayItem uploadFile(MultipartFile file, String userName) {
        if (file == null || file.isEmpty()) {
            throw new FileUploadException("Null or empty file to upload");
        }
        if (userName == null || userName.equals("")) {
            throw new FileUploadException("User name is null or empty");
        }
        DisplayItem newItem = new DisplayItem();
        String filename = file.getOriginalFilename();
        LOG.info(String.format("Uploading file: %s", filename));

        Path path = Paths.get(Constants.FILE_UPLOAD_DIR, userName).resolve(filename);
        LOG.info(String.format("File upload path: %s", path));
        int width = 0;
        int height = 0;
        // save uploaded file
        try {
            // find width and height of image file
            InputStream inputStream = file.getInputStream();
            BufferedImage image = ImageIO.read(inputStream);
            width = image.getWidth();
            height = image.getHeight();
            // allow overwriting of existing file
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new FileUploadException(String.format("Problem uploading file %s to path %s", filename, path), e);
        }

        // created metadata record
        try {
            User user = userRepository.findByLoginId(userName);
            DisplayItem item = new DisplayItem();
            item.setFilename(filename);
            item.setHeight(height);
            item.setWidth(width);
            item.setUser(user);
            item.setCreateDate(LocalDateTime.now());
            newItem = repository.save(item);
        } catch (Exception e) {
            // delete uploaded file if present
            File uploadedFile = path.toFile();
            if (uploadedFile.exists()) {
                boolean isDeleted = uploadedFile.delete();
                if (!isDeleted) {
                    LOG.error(String.format("Problem deleting uploaded file %s/%s"), path, filename);
                }
            }
            throw new FileUploadException(String.format("Propblem saving file %s data to database", filename), e);
        }
        LOG.info("New DisplayItem created " +  newItem);
        return newItem;
    }
}
