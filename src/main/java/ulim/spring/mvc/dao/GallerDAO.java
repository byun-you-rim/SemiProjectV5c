package ulim.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ulim.spring.mvc.vo.GallerVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("gdao")
public class GallerDAO {

    private JdbcTemplate jdbcTemplate;
    @Value("#{jdbc['insertGallerySQL']}") private String insertGallerySQL;
    @Value("#{jdbc['selectGallerySQL']}") private String selectGallerySQL;
    @Value("#{jdbc['selectOneGallerySQL']}") private String selectOneGallerySQL;
    @Value("#{jdbc['lastGalleryIdSQL']}") private String lastGalleryIdSQL;

    @Autowired
    public GallerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String insertGallery(GallerVO gvo) {
        Object[] params = new Object[]{
            gvo.getTitle(), gvo.getUserid(), gvo.getContents(),
                    gvo.getFname1(), gvo.getFname2(), gvo.getFname3()
        } ;

        // 갤러리 데이터를 gallery 테이블에 저장
        jdbcTemplate.update(insertGallerySQL, params);

        // 방금 입력한 갤러리 데이터의 gno 값을 조사해서 반환
        return jdbcTemplate.queryForObject(
                                lastGalleryIdSQL,String.class);
    }

//  갤러리 목록 표시
    public List<GallerVO> selectGallery() {
        RowMapper<GallerVO> mapper = new GalleryRowMapper();

        return jdbcTemplate.query(selectGallerySQL, mapper);
    }
    // 갤러리 본문출력
    public GallerVO selectOneGallery(String gno) {
        Object[] params = new Object[]{gno};
        RowMapper<GallerVO> mapper = new GalleryOneMapper();

        return jdbcTemplate.queryForObject(selectOneGallerySQL,params,mapper);
    }

    // selectGallery 데 대한 RowMapper
    private class GalleryRowMapper implements RowMapper<GallerVO>{

        @Override
        public GallerVO mapRow(ResultSet rs, int i) throws SQLException {
            GallerVO gvo = new GallerVO(
                    rs.getString("gno"),
                    rs.getString("title"),
                    rs.getString("userid"),
                    rs.getString("regdate"),
                    rs.getString("thumbup"),
                    rs.getString("views"),
                    null,
                    rs.getString("fname1"),
                    null, null);

            return gvo;
        }
    }
    //selectOneGallery 에 대한 RowMapper
    private class GalleryOneMapper implements RowMapper<GallerVO>{

        @Override
        public GallerVO mapRow(ResultSet rs, int num) throws SQLException {
            GallerVO gvo = new GallerVO(
                    rs.getString("gno"),
                    rs.getString("title"),
                    rs.getString("userid"),
                    rs.getString("regdate"),
                    rs.getString("thumbup"),
                    rs.getString("views"),
                    rs.getString("contents"),
                    rs.getString("fname1"),
                    rs.getString("fname2"),
                    rs.getString("fname3")
            );
            return gvo;
        }
    }
};