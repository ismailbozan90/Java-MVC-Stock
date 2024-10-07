package org.scamlet.mvc.mvcstock.Service;

import jakarta.transaction.Transactional;
import org.scamlet.mvc.mvcstock.Entity.Log;
import org.scamlet.mvc.mvcstock.Entity.User;
import org.scamlet.mvc.mvcstock.Repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }


    public long logCount() {
        return logRepository.count();
    }

    @Transactional
    public void addLog(User user, String action) {
        Log tempLog = new Log();
        tempLog.setAction(action);
        tempLog.setUser(user);
        tempLog.setDate(new Date());
        logRepository.save(tempLog);
    }
}
