package com.numberone.web.controller.kafka;

import java.time.Duration;
import java.util.*;

import com.numberone.common.json.JSONObject;
import com.numberone.kafka.domain.Kafkamessage;
import com.numberone.kafka.service.IKafkamessageService;
import com.numberone.system.domain.SysDept;
import com.numberone.web.controller.system.SysProfileController;
import com.numberone.web.util.TestTopicReceiver;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.concurrent.ListenableFuture;
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
import springfox.documentation.spring.web.json.Json;

/**
 * kafka消息 信息操作处理
 * 
 * @author numberone
 * @date 2020-07-30
 */
@Controller
@RequestMapping("/kafkamessageController")
public class KafkamessageController extends BaseController
{
	private static final Logger log = LoggerFactory.getLogger(SysProfileController.class);
	@Autowired
	private KafkaTemplate kafkaTemplate;
    private String prefix = "kafka/kafkamessage";
    @Autowired
	TestTopicReceiver testTopicReceiver;

	@Autowired
	private IKafkamessageService kafkamessageService;
	
	@RequestMapping("/kafkamessage")
	public String kafkamessage()
	{
	    return "kafka/kafkamessage";
	}
	
	/**
	 * 查询kafka消息列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Kafkamessage kafkamessage)
	{

//		Properties props = new Properties();
////		props.put("bootstrap.servers","47.100.20.182:9092,106.14.5.90:9092,106.15.91.136:9092");
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"47.100.20.182:9092,106.14.5.90:9092,106.15.91.136:9092");
//		props.put(ConsumerConfig.GROUP_ID_CONFIG,"test-consumer-group");
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//		//Value 的反序列化器
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
////		props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
////		props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
//		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
//		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"20000");
//		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
//		consumer.subscribe(Collections.singletonList("BootstrapTopic"));
//		List list=new ArrayList();
////		while (true) {
//		ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//		for (ConsumerRecord<String, String> record : records){
//			System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
//			list.add(record.value());
//		}
////		}
		startPage();
		List<Kafkamessage> list=TestTopicReceiver.ms;
		return getDataTable(list);

	}
	
	
	/**
	 * 导出kafka消息列表
	 */
	@RequiresPermissions("system:kafkamessage:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Kafkamessage kafkamessage)
    {
    	List<Kafkamessage> list = kafkamessageService.selectKafkamessageList(kafkamessage);
        ExcelUtil<Kafkamessage> util = new ExcelUtil<Kafkamessage>(Kafkamessage.class);
        return util.exportExcel(list, "kafkamessage");
    }
	
	/**
	 * 新增kafka消息
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存kafka消息
	 */
	@RequiresPermissions("system:kafkamessage:add")
	@Log(title = "kafka消息", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Kafkamessage kafkamessage)
	{		
		return toAjax(kafkamessageService.insertKafkamessage(kafkamessage));
	}

	@RequestMapping("/send")
	@ResponseBody
	public AjaxResult send(){
		Map map=new HashMap();
		String message="测试kakfa"+System.currentTimeMillis();
//		map.put("message",message);
		ProducerRecord<String,String> record = new ProducerRecord<>("BootstrapTopic", "message",message);
		kafkaTemplate.send(record);
		return toAjax(1);
	}
	/**
	 * 修改kafka消息
	 */
	@GetMapping("/edit/{messageId}")
	public String edit(@PathVariable("messageId") Integer messageId, ModelMap mmap)
	{
		Kafkamessage kafkamessage = kafkamessageService.selectKafkamessageById(messageId);
		mmap.put("kafkamessage", kafkamessage);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存kafka消息
	 */
	@RequiresPermissions("system:kafkamessage:edit")
	@Log(title = "kafka消息", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Kafkamessage kafkamessage)
	{		
		return toAjax(kafkamessageService.updateKafkamessage(kafkamessage));
	}
	
	/**
	 * 删除kafka消息
	 */
	@Log(title = "kafka消息", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(kafkamessageService.deleteKafkamessageByIds(ids));
	}
	
}
