package com.numberone.web.controller.activiti;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.numberone.activiti.domain.NodeRelation;
import com.numberone.activiti.service.INodeRelationService;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.editor.language.json.converter.BpmnJsonConverterUtil;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.FileUtils;
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
import org.springframework.web.servlet.ModelAndView;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * 流程配置 信息操作处理
 * 
 * @author numberone
 * @date 2020-06-23
 */
@Controller
@RequestMapping("/nodeRelation")
public class NodeRelationController extends BaseController
{
    private String prefix = "nodeRelation";
    ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private INodeRelationService nodeRelationService;
	
	@GetMapping("/detail")
	public ModelAndView nodeRelation(String modelId)
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("modelId",modelId);
		modelAndView.setViewName(prefix + "/nodeRelation");
	    return modelAndView;
	}
	
	/**
	 * 查询流程配置列表
	 */
	@RequiresPermissions("nodeRelation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(NodeRelation nodeRelation)
	{
		startPage();
        List<NodeRelation> list = nodeRelationService.selectNodeRelationList(nodeRelation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出流程配置列表
	 */
	@RequiresPermissions("nodeRelation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NodeRelation nodeRelation)
    {
    	List<NodeRelation> list = nodeRelationService.selectNodeRelationList(nodeRelation);
        ExcelUtil<NodeRelation> util = new ExcelUtil<NodeRelation>(NodeRelation.class);
        return util.exportExcel(list, "nodeRelation");
    }
	
	/**
	 * 新增流程配置
	 */
	@GetMapping("/add")
	public ModelAndView add(String modelId)
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("modelId",modelId);
		modelAndView.setViewName(prefix + "/add");
	    return modelAndView;
	}

	/**
	 * 新增流程配置
	 */
	@GetMapping("/addProcess")
	public ModelAndView addProcess(String modelId)
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("modelId",modelId);
		modelAndView.setViewName(prefix + "/addProcess");
		return modelAndView;
	}
	
	/**
	 * 新增保存流程配置
	 */
	@RequiresPermissions("nodeRelation:add")
	@Log(title = "流程配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(NodeRelation nodeRelation)
	{
		nodeRelation.setId("A"+UUID.randomUUID().toString());
		System.out.println(nodeRelation.getTaskType());
		return toAjax(nodeRelationService.insertNodeRelation(nodeRelation));
	}

	/**
	 * 修改流程配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		NodeRelation nodeRelation = nodeRelationService.selectNodeRelationById(id);
		mmap.put("nodeRelation", nodeRelation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存流程配置
	 */
	@RequiresPermissions("nodeRelation:edit")
	@Log(title = "流程配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(NodeRelation nodeRelation)
	{		
		return toAjax(nodeRelationService.updateNodeRelation(nodeRelation));
	}
	
	/**
	 * 删除流程配置
	 */
	@RequiresPermissions("nodeRelation:remove")
	@Log(title = "流程配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(nodeRelationService.deleteNodeRelationByIds(ids));
	}

	@RequestMapping("/autoProcess")
	@ResponseBody
	public AjaxResult AutoProcess(String modelId,String processId) throws UnsupportedEncodingException, XMLStreamException {
		NodeRelation nodeRelation=new NodeRelation();
		nodeRelation.setModelId(modelId);
		List<NodeRelation> ns=nodeRelationService.selectNodeRelationList(nodeRelation);
		BpmnModel bpmnModel = new BpmnModel();
		Process process=new Process();
		process.setId(processId);
		bpmnModel.addProcess(process);
		//创建bpmn元素
		for (NodeRelation n:ns){//遍历配置表单添加node节点
			if(n.getTaskType().equals("0")){//开始
				process.addFlowElement(createStartEvent(n.getNodeId()));
			}
			if(n.getTaskType().equals("1")){//结束
				if(process.getFlowElement(n.getNodeId())==null){
					process.addFlowElement(createEndEvent(n.getNodeId()));
				}
			}
			if(n.getTaskType().equals("2")){//用户
				if(process.getFlowElement(n.getNodeId())==null){
					process.addFlowElement(createUserTask(n.getNodeId(),n.getNodeName(),n.getUserType(),n.getUserIds()));
				}
			}
		}
		List<String> parentIds=nodeRelationService.getParentIdByDistinct(modelId);//获取不重复的父节点
		for (String p:parentIds){
			if(p!=null&&!p.equals("")){
				NodeRelation relation=new NodeRelation();
				relation.setModelId(modelId);
				relation.setParentId(p);
				List<NodeRelation> nrs=nodeRelationService.selectNodeRelationList(relation);//根据父节点查询所有的子节点
				if(nrs.size()>1){//子节点数大于1
					ExclusiveGateway exclusiveGateway=createExclusiveGateway(p+"eway",p+"下发");
					process.addFlowElement(exclusiveGateway);
					process.addFlowElement(createSequenceFlow(p,exclusiveGateway.getId(),null));//添加排他网关
					for(NodeRelation nr:nrs){//给每个子节点添加连线
						process.addFlowElement(createSequenceFlow(exclusiveGateway.getId(),nr.getNodeId(),nr.getTerms()));
					}
				}else {
					process.addFlowElement(createSequenceFlow(nrs.get(0).getParentId(),nrs.get(0).getNodeId(),nrs.get(0).getTerms()));
				}
			}
		}

		new BpmnAutoLayout(bpmnModel).execute();
		BpmnXMLConverter bpmnXMLConverter=new BpmnXMLConverter();
		byte[] bs=bpmnXMLConverter.convertToXML(bpmnModel,"utf-8");
		BpmnModel bpmnModel1=converterXMLToBpmn(bs);
		BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();
		JsonNode jsonNodes = bpmnJsonConverter.convertToJson(bpmnModel1);
		BpmnModel b=bpmnJsonConverter.convertToBpmnModel(jsonNodes);
		repositoryService.addModelEditorSource(modelId, jsonNodes.toString().getBytes("utf-8"));
		byte[] bytes = bpmnXMLConverter.convertToXML(bpmnModel,"utf-8");
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		String filename = bpmnModel.getMainProcess().getId() + ".bpmn";
		  File file = new File(filename);
	//      //选择流
		  FileOutputStream fos = null;
		  try {
			fos = new FileOutputStream(file);
			int temp;
			byte[] bt = new byte[1024*10];
			while((temp = in.read(bt))!= -1) {
			  fos.write(bt,0,temp);
			}
		  } catch (FileNotFoundException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		  }finally {
			//关流
			try {
			  if(null != fos)
				fos.close();
			} catch (IOException e) {
			  e.printStackTrace();
			}
		  }
		return toAjax(1);
	}
	public static BpmnModel converterXMLToBpmn(byte[] xmlJson)throws XMLStreamException, UnsupportedEncodingException{
	ByteArrayInputStream bis=new ByteArrayInputStream(xmlJson);
	 BpmnXMLConverter converter=new BpmnXMLConverter();
	 XMLInputFactory factory = XMLInputFactory.newInstance();
	 XMLStreamReader reader = factory.createXMLStreamReader(bis);
	 BpmnModel bpmnModel = converter.convertToBpmnModel((XMLStreamReader) reader);
	 return bpmnModel;

 }
	//
//	@RequestMapping("/modify")
//	@ResponseBody
//	public AjaxResult modify(String modelId){
//		NodeRelation nodeRelation=new NodeRelation();
//		nodeRelation.setModelId(modelId);
//		List<NodeRelation> ns=nodeRelationService.selectNodeRelationList(nodeRelation);
//		byte[] modelSource = repositoryService.getModelEditorSource(modelId);
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode jsonNode = null;
//		try {
//			jsonNode = mapper.readTree(modelSource);
//			//创建一个bpmn的json转换器
//			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
//			//将jsonNode对象转换为bpmnModel的对象
//			BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(jsonNode);
//			//读取bpmnModel中id为process的流程定义
//			Process process = bpmnModel.getProcessById("process");
//			if(process==null){
//				process=new Process();
//				process.setId("process");
//			}
//			//创建bpmn元素
//			for (NodeRelation n:ns){
//				if(n.getNodeName().equals("开始")){
//					process.addFlowElement(createStartEvent(n.getId()));
//				}
//				if(n.getNodeName().equals("结束")){
//					process.addFlowElement(createEndEvent(n.getId()));
//				}
//				if(n.getNodeName().equals("主管审批")){
//					process.addFlowElement(createUserTask(n.getId(),n.getNodeName(),n.getUserType(),n.getUserIds()));
//				}
//				if(n.getNodeName().equals("经理审批")){
//					process.addFlowElement(createUserTask(n.getId(),n.getNodeName()));
//				}
//			}
//			for (NodeRelation n:ns){
//				if(n.getParentId()!=null&&!n.getParentId().equals("")){
//					process.addFlowElement(createSequenceFlow(n.getParentId(),n.getId(),n.getTerms()));
//				}
//			}
//			bpmnModel.addProcess(process);
//			new BpmnAutoLayout(bpmnModel).execute();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//// 7. 存储xml
//
//		return toAjax(1);
//	}

	protected UserTask createUserTask(String id, String name,String userType,String userIds) {
		UserTask userTask = new UserTask();
		userTask.setName(name);
		userTask.setId(id);
		if(userType!=null){
			String[] types=userType.split(",");
			List us=new ArrayList();
			for (String ts:types){
				if(userIds!=null){
					String[] users=userIds.split(",");
					List<String> l= Arrays.asList(users);
					if(userType.equals("0")){
						//此处根据角色Id获取user集合并添加到us中
					}else if(userType.equals("1")){
						//此处根据部门Id获取user集合并添加到us中
					}
					us.addAll(l);
				}
			}
			userTask.setCandidateUsers(us);
		}
		return userTask;
	}


	//创建箭头
	protected SequenceFlow createSequenceFlow(String from, String to,String condition) {
		SequenceFlow flow = new SequenceFlow();
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		flow.setConditionExpression(condition);
		return flow;
	}

	protected ExclusiveGateway createExclusiveGateway(String id, String name) {
		ExclusiveGateway eway = new ExclusiveGateway();
		eway.setId(id);
		eway.setName(name);
		return eway;
	}


	protected StartEvent createStartEvent(String id) {
		StartEvent startEvent = new StartEvent();
		startEvent.setId(id);
		return startEvent;
	}


	protected EndEvent createEndEvent(String id) {
		EndEvent endEvent = new EndEvent();
		endEvent.setId(id);
		return endEvent;
	}

}

