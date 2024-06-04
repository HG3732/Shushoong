package kh.mclass.shushoong.member.email;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Service
@PropertySource("classpath:/keyfiles/apikey.properties")
public class EmailService {

}
