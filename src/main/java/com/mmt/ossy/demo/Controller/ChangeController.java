package com.mmt.ossy.demo.Controller;

import com.mmt.ossy.demo.Mapper.ProcessmodelMapper;
import com.mmt.ossy.demo.model.ProcessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChangeController {
    @Autowired
    private ProcessmodelMapper processmodelMapper;

    @GetMapping("/Add")
    public String add(@RequestParam("id") int id) {
        ProcessModel process = new ProcessModel();
        process.setId(id);
        process.setWorkTime((int) System.currentTimeMillis());
        process.setState(0);
        processmodelMapper.add(process);
        return "redirect:/";
    }

    @GetMapping("/change/{state}")
    public String readyToExecute(@RequestParam("id") int id,@PathVariable("state") int state) {
        processmodelMapper.updateById(id,state);
        processmodelMapper.updateTime(id,(int)System.currentTimeMillis());
        return "redirect:/";
    }

    @GetMapping("/Delete")
    public String delete(@RequestParam("id") int id) {
        processmodelMapper.DeleteById(id);
        return "redirect:/";
    }

}
