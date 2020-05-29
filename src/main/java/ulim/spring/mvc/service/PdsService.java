package ulim.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulim.spring.mvc.dao.PdsDAO;
import ulim.spring.mvc.vo.PdsVO;

import java.util.ArrayList;

@Service("psrv")
public class PdsService {

    private PdsDAO pdao = null;

    @Autowired
    public PdsService(PdsDAO pdao) {
        this.pdao = pdao;
    }


    public String newPds(PdsVO pd) {
        String result = "데이터 입력 실패!!";

            //pd.setFname("abc123xyz.zip");
            pd.setFsize("1234");
            pd.setFdown("9876");
            pd.setFtype("zip");

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

}
