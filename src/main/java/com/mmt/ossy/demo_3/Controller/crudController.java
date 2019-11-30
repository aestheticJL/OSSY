package com.mmt.ossy.demo_3.Controller;

import com.mmt.ossy.demo_3.DTO.MemoryDTO;
import com.mmt.ossy.demo_3.Mapper.MemoryMapeer;
import com.mmt.ossy.demo_3.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class crudController {
    @Autowired
    private MemoryMapeer memoryMapeer;
    @Autowired
    private MemoryService memoryService;

    @GetMapping("/remove")
    public String remove(@RequestParam("Id") Integer id) {
        memoryMapeer.remove(id);
        return "redirect:/";
    }

    @GetMapping("/first")
    public String first(@RequestParam("Id") Integer id, @RequestParam("Long") Integer Long) {
        List<MemoryDTO> memoryDTOS = memoryService.changeMemory();
        for (MemoryDTO memoryDTO : memoryDTOS) {
            if (memoryDTO.getStatus() == 0) {
                if (memoryDTO.getLong() >= Long) {
                    memoryMapeer.add(id, memoryDTO.getBegin(), memoryDTO.getBegin() + Long);
                    return "redirect:/";
                }
            }
        }
        return "redirect:/";
    }

    @GetMapping("/best")
    public String best(@RequestParam("Id") Integer id, @RequestParam("Long") Integer Long) {
        List<MemoryDTO> memoryDTOS = memoryService.changeMemory();
        Map<Integer, Integer> map = new HashMap<>();
        memoryDTOS.forEach(memoryDTO -> {
            if (memoryDTO.getStatus() == 0 && memoryDTO.getLong() >= Long) {
                map.put(memoryDTO.getBegin(), memoryDTO.getLong());
            }
        });
        System.out.println(map.toString());
        List<Map.Entry<Integer, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));
        memoryMapeer.add(id, list.get(0).getKey(), list.get(0).getKey() + Long);
        return "redirect:/";
    }

    @GetMapping("/worst")
    public String worst(@RequestParam("Id") Integer id, @RequestParam("Long") Integer Long) {
        List<MemoryDTO> memoryDTOS = memoryService.changeMemory();
        Map<Integer, Integer> map = new HashMap<>();
        memoryDTOS.forEach(memoryDTO -> {
            if (memoryDTO.getStatus() == 0 && memoryDTO.getLong() >= Long) {
                map.put(memoryDTO.getBegin(), memoryDTO.getLong());
            }
        });
        System.out.println(map.toString());
        List<Map.Entry<Integer, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));
        memoryMapeer.add(id, list.get(list.size()-1).getKey(), list.get(list.size()-1).getKey() + Long);
        return "redirect:/";
    }
}
