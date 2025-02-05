package com.jobautomation.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "applicant.profile")
public class ApplicantProperties {
    private String name;
    private String email;
    private String location;
    private String phone;
    private String github;
    private String linkedin;
    private String currentCompany;
    private String currentRole;
    private Integer experienceYears;
}