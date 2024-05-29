package kh.mclass.shushoong;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaymentController {
	
	private final IamportClient iamportClient;
	
    @ResponseBody
    @PostMapping("/payments/complete/{imp_uid}")
    public void /*IamportResponse<Payment>*/ paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
    	System.out.println("-------------------------------");
//    	return "aaa";
       /* return iamportClient.paymentByImpUid(imp_uid);*/
    }
}
