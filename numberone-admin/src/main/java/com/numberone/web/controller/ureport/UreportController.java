package com.numberone.web.controller.ureport;

import com.numberone.framework.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/ureportController")
public class UreportController extends BaseController {

    @RequestMapping("/ureport")
    public void ureport(HttpServletResponse response)
    {
        try {
            response.sendRedirect("/ureport/designer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
