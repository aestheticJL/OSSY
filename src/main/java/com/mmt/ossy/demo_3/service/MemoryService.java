package com.mmt.ossy.demo_3.service;

import com.mmt.ossy.demo_3.DTO.MemoryDTO;
import com.mmt.ossy.demo_3.Mapper.MemoryMapeer;
import com.mmt.ossy.demo_3.model.MemoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryService {
    @Autowired
    private MemoryMapeer memoryMapeer;
    public List<MemoryDTO> changeMemory(){
        List<MemoryModel> memoryList = memoryMapeer.getList();

        List<MemoryDTO> memoryDTOS = new ArrayList<>();

        memoryList.forEach(m -> {
            MemoryDTO memoryDTO = new MemoryDTO();
            memoryDTO.setId(m.getId());
            memoryDTO.setBegin(m.getBegin());
            memoryDTO.setEnd(m.getEnd());
            memoryDTO.setLong(m.getEnd()-m.getBegin());
            memoryDTO.setStatus(1);
            if (memoryDTOS.size()-1==-1){
                if (m.getBegin()==0){
                    memoryDTOS.add(memoryDTO);
                }else {
                    MemoryDTO empty = new MemoryDTO();
                    empty.setBegin(0);
                    empty.setEnd(m.getBegin());
                    empty.setLong(m.getBegin());
                    empty.setStatus(0);
                    memoryDTOS.add(empty);
                    memoryDTOS.add(memoryDTO);
                }
            }else {
                if(memoryDTOS.get(memoryDTOS.size()-1).getEnd()==m.getBegin()){
                    memoryDTOS.add(memoryDTO);
                }else {
                    MemoryDTO empty = new MemoryDTO();
                    empty.setBegin(memoryDTOS.get(memoryDTOS.size()-1).getEnd());
                    empty.setEnd(m.getBegin());
                    empty.setLong(m.getBegin()-memoryDTOS.get(memoryDTOS.size()-1).getEnd());
                    empty.setStatus(0);
                    memoryDTOS.add(empty);
                    memoryDTOS.add(memoryDTO);
                }
            }
        });
        if (memoryDTOS.size()-1!=-1){
            MemoryDTO empty = new MemoryDTO();
            empty.setLong(100-memoryDTOS.get(memoryDTOS.size()-1).getEnd());
            empty.setBegin(memoryDTOS.get(memoryDTOS.size()-1).getEnd());
            empty.setEnd(100);
            empty.setStatus(0);
            memoryDTOS.add(empty);
        }
        return memoryDTOS;
    }
}
