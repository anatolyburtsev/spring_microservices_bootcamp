package ru.java.mentor.secutiry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
public class BankController {
    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String transfer4(@RequestParam("accountNo") int accountNo,
                            @RequestParam("amount") final int amount) {
        log.info("Transfer to {}", accountNo);
        return "SuccessBlah";
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public void transfer3(@RequestParam("accountNo") int accountNo, @RequestParam("amount") final int amount) {
        log.info("Transfer to {}", accountNo);
    }
}
