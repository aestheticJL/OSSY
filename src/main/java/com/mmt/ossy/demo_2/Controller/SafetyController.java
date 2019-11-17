package com.mmt.ossy.demo_2.Controller;

import com.mmt.ossy.demo_2.Mapper.BankMapper;
import com.mmt.ossy.demo_2.Mapper.InitialBankMapper;
import com.mmt.ossy.demo_2.dto.BankDTO;
import com.mmt.ossy.demo_2.model.BankModel;
import com.mmt.ossy.demo_2.model.InitialBankModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SafetyController {
    @Autowired
    private BankMapper bankMapper;
    @Autowired
    private InitialBankMapper initialBankMapper;


    public static void dfs(int index,boolean flag,int[] bank,int[][] need,int[][] max,int[] visited,int Name[]) {
        if (index == 5) {
            flag = true;
            return;
        } else {
            for (int i = 0; i < 5; i++) {
                if (visited[i] == 0 && need[i][0] <= bank[0] && need[i][1] <= bank[1] && need[i][2] <= bank[2]) {
                    bank[0] -= need[i][0];
                    bank[1] -= need[i][1];
                    bank[2] -= need[i][2];
                    bank[0] += max[i][0];
                    bank[1] += max[i][1];
                    bank[2] += max[i][2];
                    visited[i] = 1;
                    Name[index] = i + 1;
                    dfs(index + 1,flag,bank,need,max,visited,Name);
                    if (!flag) {
                        bank[0] += need[i][0];
                        bank[1] += need[i][1];
                        bank[2] += need[i][2];
                        bank[0] -= max[i][0];
                        bank[1] -= max[i][1];
                        bank[2] -= max[i][2];
                        visited[i] = 0;
                    }
                }
            }
        }
    }

    @GetMapping("/safety")
    public String safety(Model model,@RequestParam(value = "processId", required = false) String processId) {
         int[] bank = new int[3];
         int[][] need = new int[5][3];
         int[][] max = new int[5][3];
         int Name[] = new int[5];
         int[] visited = new int[5];
         boolean flag = false;
        BankModel bankList = bankMapper.getBankList();
        bank[0] = bankList.getAvailableA();
        bank[1] = bankList.getAvailableB();
        bank[2] = bankList.getAvailableC();
        List<BankModel> allProcessList = bankMapper.getAllProcessList();
        List<InitialBankModel> MaxList = initialBankMapper.getList();
        allProcessList.forEach(p -> {
            need[p.getProcessId() - 1][0] = p.getAvailableA();
            need[p.getProcessId() - 1][1] = p.getAvailableB();
            need[p.getProcessId() - 1][2] = p.getAvailableC();
        });
        MaxList.forEach(p -> {
            max[p.getProcessId() - 1][0] = p.getMaxA();
            max[p.getProcessId() - 1][1] = p.getMaxB();
            max[p.getProcessId() - 1][2] = p.getMaxC();
            need[p.getProcessId() - 1][0] = p.getMaxA() - need[p.getProcessId() - 1][0];
            need[p.getProcessId() - 1][1] = p.getMaxB() - need[p.getProcessId() - 1][1];
            need[p.getProcessId() - 1][2] = p.getMaxC() - need[p.getProcessId() - 1][2];
        });
        dfs(0 ,flag,bank,need,max,visited,Name);
        if (Name[0] + Name[1] + Name[2] + Name[3] + Name[4] == 15) {
            System.out.println(1);
            model.addAttribute("safety", "安全序列为：" + Name[0] + "->" + Name[1] + "->" + Name[2] + "->" + Name[3] + "->" + Name[4]);
        } else {
            System.out.println(0);
            model.addAttribute("safety", "没有安全序列建议恢复数据");
        }
        List<InitialBankModel> initialList = initialBankMapper.getList();
        BankModel bankModel = bankMapper.getBankList();
        List<BankDTO> bankDTOS = new ArrayList<>();
        for (InitialBankModel initialBankModel : initialList) {
            BankModel processList = bankMapper.getProcessList(initialBankModel.getProcessId());
            BankDTO bankDTO = new BankDTO();
            BeanUtils.copyProperties(initialBankModel, bankDTO);
            bankDTO.setAvailableA(processList.getAvailableA());
            bankDTO.setAvailableB(processList.getAvailableB());
            bankDTO.setAvailableC(processList.getAvailableC());
            bankDTOS.add(bankDTO);
        }
        model.addAttribute("bankDTOS", bankDTOS);
        model.addAttribute("bank", bankModel);
        model.addAttribute("processId", processId);
        return "bankBegin";
    }
}
