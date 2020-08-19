package com.numberone.web.util;

import com.numberone.kylin.KylinHttpBasic;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TestBean {
    public List<Map<String, Object>> loadReportData(String dsName, String datasetName, Map<String,Object> parameters){
        KylinHttpBasic. login("ADMIN","KYLIN");
        List<Map<String, Object>> json=KylinHttpBasic.query("select sum(price) as ps,sum(SERIALNUMBER) as ss,create_time from GOODSTABLE group by create_time");
        return json;
    }
}
