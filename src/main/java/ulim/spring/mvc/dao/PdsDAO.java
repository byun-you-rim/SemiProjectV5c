package ulim.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ulim.spring.mvc.vo.BoardVO;
import ulim.spring.mvc.vo.PdsVO;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

@Repository("pdao")
public class PdsDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("#{jdbc['insertPdsSQL']}") private String insertPdsSQL;
    @Value("#{jdbc['selectPdsSQL']}") private String selectPdsSQL;
    @Value("#{jdbc['selectOnePdsSQL']}") private String selectOnePdsSQL;

    @Autowired
    public PdsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 게시판 데이터를 board테이블에 저장
    public boolean insertPds( PdsVO p ) {
        // sql 정의
        // String sql = "insert into board (title,userid,contents) values (?,?,?)";

        // 매개변수 정의
        Object[] params = new Object[] {
                p.getTitle(), p.getUserid(), p.getContents(),
                p.getFname(), p.getFsize(), p.getFdown(),
                p.getFtype()
        };

        // 매개변수 타입 정의 - 생략 ^^;

        // 샐행
        boolean isInsert = false;
        if (jdbcTemplate.update(insertPdsSQL, params) > 0)
            isInsert = true;

        return isInsert;
    }


    public List<PdsVO> selectPds() {
        // String sql = "select pno,title,userid,regdate,thumbup,views from board order by bno desc";

        RowMapper<PdsVO> mapper = new PdsRowMapper();


        return jdbcTemplate.query(selectPdsSQL, mapper);
    }

    // 글번호로 선택한 게시물에 대해 모든 컬럼을 조회해서
    // BoardVO 객체에 저장하고 반환함
    public PdsVO selectOnePds(String pno) {
        // String sql = "select * from board where bno = ?";

        Object[] params = new Object[] { pno };

        RowMapper<PdsVO> mapper = new PdsOneMapper();

        PdsVO pvo = jdbcTemplate.queryForObject(
                selectOnePdsSQL, mapper, params);

        return pvo;
    }

    private class PdsRowMapper implements RowMapper<PdsVO> {

        @Override
        public PdsVO mapRow(ResultSet rs, int num) throws SQLException {

            PdsVO pvo = new PdsVO(
                    rs.getString("pno"),
                    rs.getString("title"),
                    rs.getString("userid"),
                    rs.getString("regdate"),
                    rs.getString("thumbup"),
                    rs.getString("views"),
                    null,null,null,null,null
            );

            return pvo;
        }
    }

    private class PdsOneMapper implements RowMapper<PdsVO> {
        @Override
        public PdsVO mapRow(ResultSet rs, int num) throws SQLException {

            PdsVO pvo = new PdsVO(
                    rs.getString("pno"),
                    rs.getString("title"),
                    rs.getString("userid"),
                    rs.getString("regdate"),
                    rs.getString("thumbup"),
                    rs.getString("views"),
                    rs.getString("contents"),
                    rs.getString("fname"),
                    rs.getString("fsize"),
                    rs.getString("fdown"),
                    rs.getString("ftype")
            );

            return pvo;
        }
    }

}
