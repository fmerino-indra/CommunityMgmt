package org.fmm.communitymgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"org.fmm.communitymgmt","org.fmm.oauth.springsocial"})
public class CommunityMgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityMgmtApplication.class, args);
    }

}
