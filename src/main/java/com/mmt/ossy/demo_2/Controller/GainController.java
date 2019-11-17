package com.mmt.ossy.demo_2.Controller;

import com.mmt.ossy.demo_2.Mapper.BankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GainController {
    @Autowired
    private BankMapper bankMapper;
    @GetMapping("/gain")
    public String gain(@RequestParam("addA") Integer addA,
                       @RequestParam("addA") Integer addB,
                       @RequestParam("addA") Integer addC,
                       @RequestParam("processId") Integer processId) {
        bankMapper.gainById(processId,addA,addB,addC);
        bankMapper.gainBank(addA,addB,addC);
        return "redirect:/";
    }
}
