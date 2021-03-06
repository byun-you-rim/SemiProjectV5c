package ulim.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ulim.spring.mvc.vo.BoardVO;
import ulim.spring.mvc.vo.ReplyVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("brdao")
public class BDReplyDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("#{jdbc['insertReplyBoardSQL']}") private String insertReplyBoardSQL;
    @Value("#{jdbc['selectReplyBoardSQL']}") private String selectReplyBoardSQL;
    @Value("#{jdbc['insertBDCommentSQL']}") private String insertBDCommentSQL;
    @Value("#{jdbc['lastBDReplyIdSQL']}") private String lastBDReplyIdSQL;



    @Autowired
    public BDReplyDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    // 댓글쓰기
    public void insertReply(ReplyVO rvo) {
        Object[] params = new Object[]{rvo.getReply(),rvo.getUserid(),rvo.getBno(),lastBDReplyId()};

        jdbcTemplate.update(insertReplyBoardSQL,params);

    }
//   댓글 추가시 BDReply 테이블의 다음 rno 값을 알아냄
    private int lastBDReplyId() {
        return jdbcTemplate.queryForObject(lastBDReplyIdSQL,Integer.class);
    }

    // 댓글과 대댓글 가져오기
    public List<ReplyVO> selectReply(String bno) {
        Object[] params = new Object[] { bno };

        RowMapper<ReplyVO> mapper = new ReplyRowMapper();

        return jdbcTemplate.query(selectReplyBoardSQL,params,mapper);
    }

    // 대댓글 쓰기
    public void insertComment(ReplyVO rvo) {
        Object[] params = new Object[]{rvo.getReply(),rvo.getUserid(),rvo.getBno(),rvo.getRefno()};

        jdbcTemplate.update(insertBDCommentSQL,params);
    }


    public class ReplyRowMapper implements RowMapper<ReplyVO> {
        @Override
        public ReplyVO mapRow(ResultSet rs, int num) throws SQLException {
            ReplyVO rvo = new ReplyVO(
            rs.getString("rno"),
            rs.getString("reply"),
            rs.getString("userid"),
            rs.getString("regdate"),
            rs.getString("thumbup"),
            rs.getString("bno"),
            rs.getString("refno")
            );
            return rvo;
        }

    }

    // 댓글과 대댓글 관련 mapper
}
