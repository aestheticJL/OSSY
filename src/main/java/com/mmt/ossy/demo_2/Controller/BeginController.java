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
public class BeginController {
    @Autowired
    private InitialBankMapper initialBankMapper;
    @Autowired
    private BankMapper bankMapper;
    @GetMapping("/")
    public String begin(Model model,@RequestParam(value = "processId", required = false) String processId){
        List<InitialBankModel> initialList = initialBankMapper.getList();
        BankModel bank = bankMapper.getBankList();
        List<BankDTO> bankDTOS = new ArrayList<>();
        for (InitialBankModel initialBankModel : initialList) {
            BankModel processList = bankMapper.getProcessList(initialBankModel.getProcessId());
            BankDTO bankDTO = new BankDTO();
            BeanUtils.copyProperties(initialBankModel,bankDTO);
            bankDTO.setAvailableA(processList.getAvailableA());
            bankDTO.setAvailableB(processList.getAvailableB());
            bankDTO.setAvailableC(processList.getAvailableC());
            bankDTOS.add(bankDTO);
        }
        model.addAttribute("bankDTOS", bankDTOS);
        model.addAttribute("bank", bank);
        model.addAttribute("processId", processId);
        return "bankBegin";
    }
    @GetMapping("/initial")
    public String initial() {
        bankMapper.initialProcess();
        bankMapper.initialBank();
        return "redirect:/";
    }
}
