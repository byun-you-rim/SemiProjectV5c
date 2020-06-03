package ulim.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulim.spring.mvc.dao.GallerDAO;
import ulim.spring.mvc.vo.GallerVO;

@Service("gsrv")
public class GallerService {

    private GallerDAO gdao;

    @Autowired
    public GallerService(GallerDAO gdao) {
        this.gdao = gdao;
    }

    // 새 갤러리 쓰기
    public void newGallery(GallerVO gvo) {
        gdao.insertGallery(gvo);
    }
};