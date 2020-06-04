package ulim.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ulim.spring.mvc.dao.GallerDAO;
import ulim.spring.mvc.vo.GallerVO;

import java.util.ArrayList;
import java.util.List;

@Service("gsrv")
public class GallerService {

    private GallerDAO gdao;
    private ImageUploadUtil imgutil;

    @Autowired
    public GallerService(GallerDAO gdao,
                         ImageUploadUtil imgutil) {
        this.gdao = gdao;
        this.imgutil = imgutil;
    }

    // 새 갤러리 쓰기
    public void newGallery(GallerVO gvo, MultipartFile[] img) {

        // 업로드 이미지 처리
        // 첨부파일이 존재하는 경우
        if(imgutil.checkGalleryFile(img)){

            List<String> fnames = new ArrayList<>();
            for(MultipartFile f: img){
                // 첨부파일이 존재한다면
                // 이미지 파일로 서버에 저장하고
                // 결과로 파일이름을 배열에 저장함
                if(!f.getOriginalFilename().isEmpty())
                    fnames.add(imgutil.ImageUpload(f));
                else fnames.add(null);
            }

            gvo.setFname1(fnames.get(0));
            gvo.setFname2(fnames.get(1));
            gvo.setFname3(fnames.get(2));

        }

        // 테이블에 갤러리 관련 정보 저장
        String id = gdao.insertGallery(gvo);

        // 썸네일 이미지 생성
        imgutil.imageCropResize(gvo.getFname1(), id);


    }
};