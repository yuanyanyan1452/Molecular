package com.molecular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/molecular")
public class MolecularController {

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/search")
    public void search(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String molecularName = request.getParameter("molecularName");
        request.getSession().setAttribute("molecularName",molecularName);
        response.getWriter().print("success");
    }
}
