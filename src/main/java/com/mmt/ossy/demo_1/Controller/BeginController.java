package com.mmt.ossy.demo_1.Controller;

import com.mmt.ossy.demo_1.Mapper.ProcessmodelMapper;
import com.mmt.ossy.demo_1.model.ProcessModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;


@Controller
@Slf4j
@Component
public class BeginController {
    @Autowired
    private ProcessmodelMapper processmodelMapper;
    @Scheduled(fixedRate = 5000)
    public String shishi(){
        log.info("The time is now {}", new Date());
        return "test";
    }
    @GetMapping("/")
    public String begin(Model model){
        List<ProcessModel> readyProcess = processmodelMapper.selectReady();
        List<ProcessModel> executeProcess = processmodelMapper.selectExecute();
        List<ProcessModel> chokeProcess = processmodelMapper.selectChock();
        model.addAttribute("readyProcess", readyProcess);
        model.addAttribute("executeProcess", executeProcess);
        model.addAttribute("chokeProcess", chokeProcess);
        return "processBegin";
    }
}
