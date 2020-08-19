package com.numberone.web.controller.activiti;

import com.numberone.common.page.TableDataInfo;
import com.numberone.framework.web.base.BaseController;
import com.numberone.system.domain.SysUser;
import org.activiti.engine.TaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/myTaskController")
public class MyTaskController extends BaseController {

    @Autowired
    private TaskService taskService;
    @RequestMapping("/myTaskList")
    public String modelConfig()
    {
        return "activiti/myTaskList" ;
    }
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        SysUser user = getSysUser();
        String userName= user.getLoginName();
        startPage();
        List list = taskService.createTaskQuery().taskCandidateOrAssigned(userName).list();
        return getDataTable(list);
    }
}
