package com.rainsen.display.util;

import com.rainsen.display.common.Constant;
import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.UUID;

public class UploadUtil {

    private static final Tika tika = new Tika();

    private static final Set<String> ALLOWED_VIDEO_TYPES = Set.of(
            "video/mp4"
    );

    private static final Set<String> ALLOWED_IMAGE_TYPES = Set.of(
            "image/jpg",
            "image/jpeg",
            "image/png",
            "image/gif"
    );

    private static final Set<String> ALLOWED_DOC_TYPES = Set.of(
            "application/pdf",
            "application/msword", //.doc
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", //.docx
            "application/vnd.ms-excel", //.xls
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", //.xlsx
            "application/vnd.ms-powerpoint", //.ppt
            "application/vnd.openxmlformats-officedocument.presentationml.presentation"
    );

    private static boolean isValidFileType(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            String type = tika.detect(inputStream, file.getOriginalFilename());
            return ALLOWED_VIDEO_TYPES.contains(type)
                    || ALLOWED_IMAGE_TYPES.contains(type)
                    || ALLOWED_DOC_TYPES.contains(type);
        } catch (IOException e) {
            throw new DisException(DisExceptionEnum.FILE_UPLOAD_FAILED);
        }
    }

    private static boolean isValidVideoType(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            String type = tika.detect(inputStream, file.getOriginalFilename());
            return ALLOWED_VIDEO_TYPES.contains(type);
        } catch (IOException e) {
            throw new DisException(DisExceptionEnum.FILE_UPLOAD_FAILED);
        }
    }

    private static boolean isValidImageType(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            String type = tika.detect(inputStream, file.getOriginalFilename());
            return ALLOWED_IMAGE_TYPES.contains(type);
        } catch (IOException e) {
            throw new DisException(DisExceptionEnum.FILE_UPLOAD_FAILED);
        }
    }

    private static boolean isValidDocType(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            String type = tika.detect(inputStream, file.getOriginalFilename());
            return ALLOWED_DOC_TYPES.contains(type);
        }
    }

    private static String getSuffix(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new DisException(DisExceptionEnum.FILE_NAME_WONG);
        }
        return originalFilename.substring(originalFilename.lastIndexOf("."));
    }

    private static String getUniqueFileName(String suffix) {
        UUID uuid = UUID.randomUUID();
        return uuid + suffix;
    }

    private static File getAimedFile(String filename) {
        File aimFileDir = new File(Constant.FILE_UPLOAD_DIR);
        if (!aimFileDir.exists()) {
            if (!aimFileDir.mkdir()) {
                throw new DisException(DisExceptionEnum.MAKE_DIR_FAILED);
            }
        }
        return new File(Constant.FILE_UPLOAD_DIR + filename);
    }

    private static void save(MultipartFile file, File aimedFile) {
        try {
            if (isValidImageType(file)) {
                file.transferTo(aimedFile);
                //Thumbnails.of(aimedFile).size(50, 50).keepAspectRatio(true).toFile(aimedFile);
            } else {
                file.transferTo(aimedFile);
            }
        } catch (IOException e) {
            throw new DisException(DisExceptionEnum.FILE_UPLOAD_FAILED);
        }
    }

    public static String upload(MultipartFile uploadedFile) {
        if (!isValidFileType(uploadedFile)) {
            throw new DisException(DisExceptionEnum.FILE_NOT_VALID);
        }

        String suffix = getSuffix(uploadedFile);
        String uniqueFileName = getUniqueFileName(suffix);
        File aimedFile = getAimedFile(uniqueFileName);
        save(uploadedFile, aimedFile);

        return Constant.FILE_ACCESS_PREFIX + uniqueFileName;
    }
}
