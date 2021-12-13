package com.lee.mybatis;

import com.jcraft.jsch.Session;
import com.lee.mybatis.domain.Country;
import com.lee.mybatis.mapper.CountryMapper;
import com.lee.mybatis.ssh.MyContextListener;
import com.lee.mybatis.ssh.SSHConnection;
import com.lee.mybatis.utils.SSHUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/mybatis")
public class TestController {

    @Autowired
    private CountryMapper countryMapper;

    @GetMapping
    public String test() {
        List<Country> countries = countryMapper.selectAll();
        countries.forEach(country -> {
            System.out.println(country.toString());
        });
        return "";
    }

    @GetMapping("/cmd")
    public void cmd() {
        SSHConnection conexionssh = MyContextListener.getConexionssh();
        if (Objects.nonNull(conexionssh)) {
            Session session = conexionssh.getSession();
            if (Objects.nonNull(session)) {
                System.out.println(session.isConnected());
                System.out.println(session.toString());
                SSHUtil.sendCmd(session, "pwd");
            } else
                System.out.println("null");
        } else {
            System.out.println("空");
        }
    }
}
