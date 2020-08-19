package com.numberone.web.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpServletResponse;

@WebService
public interface UreportService {
    @WebMethod
    public void openUreport();
}
