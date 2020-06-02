package ulim.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulim.spring.mvc.dao.BDReplyDAO;
import ulim.spring.mvc.vo.ReplyVO;

@Service("brsrv")
public class BDReplyService {

    BDReplyDAO brdao;

    @Autowired
    public BDReplyService(BDReplyDAO brdao){
        this.brdao = brdao;
    }

    // 댓글쓰기
    public void makeReply(ReplyVO rvo) {
        brdao.insertReply(rvo);
    }
}
