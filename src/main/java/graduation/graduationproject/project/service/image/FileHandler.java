package graduation.graduationproject.project.service.image;

import graduation.graduationproject.project.dmain.image.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class FileHandler {

    public Image parseFileInfo(
            MultipartFile multipartFile
    ) throws Exception {
        //반환할 파일 리스트
        Image image = new Image();

        //파일이 빈것이 들어오면 빈 것을 반환 ?
        if (multipartFile.isEmpty()) {
            //return fileList;
            System.out.print("파일이 비었음");
            log.info("파일이 비었음");
        }

        //파일 이름을 업로드 한 날짜로 바꾸어서 저장
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 ?
        String absolutePath = new File("").getAbsolutePath() + "/";

        // 경로를 지정하고 그곳에다가 저장
        String path = "images/" + current_date;
        File file = new File(path);

        //저장할 위치의 디렉토리가 존재하지 않을 경우
        if (!file.exists()) {
            //mkdir() 함수와 다름 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }


        //파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다.
        if (!multipartFile.isEmpty()) {
            // jpeg, png, gif 파일들만 받아서 처리할 예정
            String contentType = multipartFile.getContentType();
            String originalFileExtension;
            //  확장자 명이 없으면 이 파일은 잘 못 된 파일이다.
            if (ObjectUtils.isEmpty(contentType)) {
                System.out.print("확장자 명이 없다");
                return image;
            }
            else{
                if (contentType.contains("image/jpeg")) {
                    originalFileExtension = ".jpg";
                }
                else if (contentType.contains("image/png")) {
                    originalFileExtension = ".png";
                }
                else if (contentType.contains("image/gif")) {
                    originalFileExtension = ".gif";
                }
                else if (contentType.contains("image/heic")) {
                    // 확장자를 뭐라고 저장해야될지 모르겠다.
                    originalFileExtension = ".heic";
                }
                else{
                    System.out.printf("지원하지 않는 확장자 명이다");
                    return image;
                }
            }
            // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
            String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
            // 생성
            image = Image.builder()
                    .original_file_name(multipartFile.getOriginalFilename())
                    .stored_file_path(path + "/" + new_file_name)
                    .file_size(multipartFile.getSize())
                    .file_content_type(multipartFile.getContentType())
                    .build();

            //저장된 파일로 변경하여 이를 보여주기 위함.
            file = new File(absolutePath + path + "/" + new_file_name);
            multipartFile.transferTo(file);
        }

        return image;
    }
}
