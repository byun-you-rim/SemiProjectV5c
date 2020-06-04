package ulim.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulim.spring.mvc.dao.LoginDAO;
import ulim.spring.mvc.dao.PdsDAO;
import ulim.spring.mvc.vo.MemberVO;
import ulim.spring.mvc.vo.PdsVO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Service
public class LoginService {

    private LoginDAO ldao;

    @Autowired
    public LoginService(LoginDAO ldao){
        this.ldao = ldao;
    }

    // 로그인 체크
    public boolean checkLogin(MemberVO mvo, HttpSession sess){
        boolean isLogin = false;

        if(ldao.selectLogin(mvo) > 0){
            // 로그인 성공시 회원정보(아이디)를 세션에 저장
            sess.setAttribute("UID",mvo.getUserid());

            isLogin = true;
        }
        return isLogin;
    }



};