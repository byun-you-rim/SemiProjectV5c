package ulim.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ulim.spring.mvc.service.GallerService;
import ulim.spring.mvc.vo.GallerVO;

@Controller
public class GalleryController {

    private GallerService gsrv;

    @Autowired
    public GalleryController(GallerService gsrv) {
        this.gsrv = gsrv;
    }

    @RequestMapping(value = "gallery/list")
    public String list() {

        return "redirect:/gallery/list";
    }

    @RequestMapping(value = "gallery/write")
    public String write(GallerVO gvo) {

        gsrv.newGallery(gvo);

        return "redirect:/gallery/write";
    }

    @RequestMapping(value = "gallery/view")
    public ModelAndView view() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout");
        mv.addObject("action","../gallery/view.jsp");
        return mv;
    }
};