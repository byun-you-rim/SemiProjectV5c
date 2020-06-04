package ulim.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ulim.spring.mvc.vo.MemberVO;

@Repository("ldao")
public class LoginDAO {

    private JdbcTemplate jdbcTemplate;
    @Value("#{jdbc['selectLoginSQL']}") private String selectLoginSQL;

    public LoginDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int selectLogin(MemberVO mvo) {
        Object[] params = new Object[]{
                mvo.getUserid(), mvo.getPasswd()};
        return jdbcTemplate.queryForObject(
                selectLoginSQL,params,Integer.class
        );


    }
};