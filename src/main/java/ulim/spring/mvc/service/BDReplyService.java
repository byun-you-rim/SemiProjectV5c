package ulim.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulim.spring.mvc.dao.BDReplyDAO;
import ulim.spring.mvc.vo.ReplyVO;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<ReplyVO> showReply(String bno) {
        return (ArrayList<ReplyVO>)brdao.selectReply(bno);
    }

    // 대댓글 쓰기
    public void makeComment(ReplyVO rvo) {
        brdao.insertComment(rvo);
    }
}
