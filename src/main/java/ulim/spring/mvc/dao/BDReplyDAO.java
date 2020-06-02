package ulim.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ulim.spring.mvc.vo.ReplyVO;

@Repository("brdao")
public class BDReplyDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("#{jdbc['insertReplyBoardSQL']}") private String insertReplyBoardSQL;
    @Value("#{jdbc['isselectBDRSQL']}") private String SelectReplyBoardSQL;

    @Autowired
    public BDReplyDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    // 댓글쓰기
    public void insertReply(ReplyVO rvo) {
        Object[] params = new Object[]{rvo.getReply(),rvo.getUserid(),rvo.getBno()};

        jdbcTemplate.update(insertReplyBoardSQL,params);

    }
}
