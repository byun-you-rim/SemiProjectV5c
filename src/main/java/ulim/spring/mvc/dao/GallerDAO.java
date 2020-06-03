package ulim.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ulim.spring.mvc.vo.GallerVO;

@Repository("gdao")
public class GallerDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GallerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertGallery(GallerVO gvo) {

    }

};