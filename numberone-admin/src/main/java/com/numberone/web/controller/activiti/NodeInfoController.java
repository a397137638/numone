package com.numberone.web.controller.activiti;

import java.util.List;

import com.numberone.activiti.domain.NodeInfo;
import com.numberone.activiti.service.INodeInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.numberone.common.annotation.Log;
import com.numberone.common.enums.BusinessType;
import com.numberone.framework.web.base.BaseController;
import com.numberone.common.page.TableDataInfo;
import com.numberone.common.base.AjaxResult;
import com.numberone.common.utils.poi.ExcelUtil;

/**
 * 流程节点 信息操作处理
 * 
 * @author numberone
 * @date 2020-06-23
 */
@Controller
@RequestMapping("/nodeInfo")
public class NodeInfoController extends BaseController
{
    private String prefix = "nodeInfo";
	
	@Autowired
	private INodeInfoService nodeInfoService;
	
	@RequiresPermissions("nodeInfo:view")
	@GetMapping()
	public String nodeInfo()
	{
	    return prefix + "/nodeInfo";
	}
	
	/**
	 * 查询流程节点列表
	 */
	@RequiresPermissions("nodeInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(NodeInfo nodeInfo)
	{
		startPage();
        List<NodeInfo> list = nodeInfoService.selectNodeInfoList(nodeInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出流程节点列表
	 */
	@RequiresPermissions("nodeInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NodeInfo nodeInfo)
    {
    	List<NodeInfo> list = nodeInfoService.selectNodeInfoList(nodeInfo);
        ExcelUtil<NodeInfo> util = new ExcelUtil<NodeInfo>(NodeInfo.class);
        return util.exportExcel(list, "nodeInfo");
    }
	
	/**
	 * 新增流程节点
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存流程节点
	 */
	@RequiresPermissions("nodeInfo:add")
	@Log(title = "流程节点", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(NodeInfo nodeInfo)
	{		
		return toAjax(nodeInfoService.insertNodeInfo(nodeInfo));
	}

	/**
	 * 修改流程节点
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		NodeInfo nodeInfo = nodeInfoService.selectNodeInfoById(id);
		mmap.put("nodeInfo", nodeInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存流程节点
	 */
	@RequiresPermissions("nodeInfo:edit")
	@Log(title = "流程节点", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(NodeInfo nodeInfo)
	{		
		return toAjax(nodeInfoService.updateNodeInfo(nodeInfo));
	}
	
	/**
	 * 删除流程节点
	 */
	@RequiresPermissions("nodeInfo:remove")
	@Log(title = "流程节点", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(nodeInfoService.deleteNodeInfoByIds(ids));
	}
	
}
