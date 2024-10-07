package org.scamlet.mvc.mvcstock.Controller;

import org.scamlet.mvc.mvcstock.Entity.Log;
import org.scamlet.mvc.mvcstock.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @RequestMapping("/logs")
    public String logs(Model model) {
        List<Log> logList = logService.getAllLogs();
        model.addAttribute("pageTitle", "Logs");
        model.addAttribute("logs", logList);
        return "/log/log";
    }


}
