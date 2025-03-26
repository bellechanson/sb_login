package edu.du.sb_thymeleaff.service;

import edu.du.sb_thymeleaff.model.Account;
import edu.du.sb_thymeleaff.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

@Service
public class AccountService {
    //회원가입
    @Autowired
    private AccountRepository accountRepository;

    public Account register(Account account) {
        return accountRepository.save(account);
    }

    // 로그인
    @Autowired
    private HttpSession session;

    public boolean login(String id, String password) {
        Account account = accountRepository.findByIdAndPassword(Long.parseLong(id), password);
        if (account != null) {
            session.setAttribute("user", account); // 로그인 성공 시 세션 저장
            return true;
        }
        return false;
    }
    public void logout() {
        session.invalidate();
    }
}
