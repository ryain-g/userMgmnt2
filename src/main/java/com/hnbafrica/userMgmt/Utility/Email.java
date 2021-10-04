package com.hnbafrica.userMgmt.Utility;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Email {
 String subject;
 String body;
 String from;
 String to;
}
