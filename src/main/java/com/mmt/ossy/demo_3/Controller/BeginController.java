package com.mmt.ossy.demo_3.Controller;

import com.mmt.ossy.demo_3.DTO.MemoryDTO;
import com.mmt.ossy.demo_3.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BeginController {
    @Autowired
    private MemoryService memoryService;

    @GetMapping("/")
    public String begin(Model model){
        List<MemoryDTO> memoryDTOS = memoryService.changeMemory();
        model.addAttribute("memoryDTOS",memoryDTOS);
        return "memory";
    }

}
