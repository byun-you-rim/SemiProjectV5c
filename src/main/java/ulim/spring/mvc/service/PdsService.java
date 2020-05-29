package ulim.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulim.spring.mvc.dao.PdsDAO;
import ulim.spring.mvc.vo.PdsVO;

import java.util.ArrayList;
import java.util.Map;

@Service("psrv")
public class PdsService {

    private PdsDAO pdao = null;

    @Autowired
    public PdsService(PdsDAO pdao) {
        this.pdao = pdao;
    }


    public String newPds(PdsVO pd, Map<String, String> frmdata ) {
        String result = "데이터 입력 실패!!";

        procFormdata(pd,frmdata);
        pd.setFdown("0");

        if (pdao.insertPds(pd))
            result = "데이터입력 성공!!";

        System.out.println(result);  // 가입여부 확인용

        return result;
    }

    public ArrayList<PdsVO> showPds() {
        return (ArrayList<PdsVO>)pdao.selectPds();
    }

    public PdsVO showOnePds(String pno) {
        return pdao.selectOnePds(pno);
    }

    // multipart 폼 데이터 처리
    private void procFormdata(PdsVO p, Map<String, String> frmdata){
        // multipart 폼 데이터 처리
        for(String key:frmdata.keySet()) {
            String val = frmdata.get(key);
            switch (key) {
                case "title":p.setTitle(val);break;
                case "userid":p.setUserid(val);break;
                case "contents":p.setContents(val);break;

                case "file1":p.setFname(val);break;
                case "file1size":p.setFsize(val);break;
                case "file1type":p.setFtype(val);break;
            }
        }
    }

}
