package kh.mclass.shushoong;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;

//@Controller
public class PaymentController {
    
	//카카오 로그인/로그아웃
	@Value("${portone.restapikey}")
	private String portoneRestapikey;
	@Value("${portone.restapisecretkey}")
	private String portoneRestapisecretkey;
	
	private final IamportClient iamportClient;
	
	public PaymentController() {
		System.out.println("=====1");
		System.out.println(portoneRestapikey);
		System.out.println(portoneRestapisecretkey);
		this.iamportClient = new IamportClient(portoneRestapikey, portoneRestapisecretkey);
	}
	
    @ResponseBody
    @PostMapping("/payments/complete/{imp_uid}")
    public void /*IamportResponse<Payment>*/ paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
    	System.out.println("-------------------------------");
//    	return "aaa";
       /* return iamportClient.paymentByImpUid(imp_uid);*/
    }
}
