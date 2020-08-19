package com.numberone.web.controller.editor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 流程信息入库
 * liuzhize 2019年3月7日下午3:32:32
 */
@RestController
@RequestMapping("service")
public class ModelSaveRestResource implements ModelDataJsonConstants {
  
  protected static final Logger LOGGER = LoggerFactory.getLogger(ModelSaveRestResource.class);

  @Autowired
  private RepositoryService repositoryService;
  
  @Autowired
  private ObjectMapper objectMapper;
 
  /**
   * 保存流程
   * @param modelId 模型ID
   * @param name 流程模型名称
   * @param description
   * @param json_xml 流程文件
   * @param svg_xml 图片
   */
  @RequestMapping(value="/model/{modelId}/save", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.OK)
  public void saveModel(@PathVariable String modelId
          , String name, String description
          , String json_xml, String svg_xml) {
    try {
      
      Model model = repositoryService.getModel(modelId);
      
      ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());


//生成bpmn
//      byte[] bpmnBytes = null;
//      String filename = null;
//      JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelId));
//      BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
//      BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
//      filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
//      bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
//      ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
//      File file = new File(filename);
//      //选择流
//      FileOutputStream fos = null;
//      try {
//        fos = new FileOutputStream(file);
//        int temp;
//        byte[] bt = new byte[1024*10];
//        while((temp = in.read(bt))!= -1) {
//          fos.write(bt,0,temp);
//        }
//      } catch (FileNotFoundException e) {
//        e.printStackTrace();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }finally {
//        //关流
//        try {
//          if(null != fos)
//            fos.close();
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      }
//生成图片
      modelJson.put(MODEL_NAME, name);
      modelJson.put(MODEL_DESCRIPTION, description);
      model.setMetaInfo(modelJson.toString());
      model.setName(name);
      repositoryService.saveModel(model);
      
      repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes("utf-8"));
      
      InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
      TranscoderInput input = new TranscoderInput(svgStream);
      PNGTranscoder transcoder = new PNGTranscoder();
      // Setup output
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      TranscoderOutput output = new TranscoderOutput(outStream);
      
      // Do the transformation
      transcoder.transcode(input, output);
      final byte[] result = outStream.toByteArray();
      repositoryService.addModelEditorSourceExtra(model.getId(), result);
      outStream.close();
    } catch (Exception e) {
      LOGGER.error("Error saving model", e);
      throw new ActivitiException("Error saving model", e);
    }
  }
  
}
