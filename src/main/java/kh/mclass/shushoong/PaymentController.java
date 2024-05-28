package kh.mclass.shushoong;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;

@Controller
public class PaymentController {
//    private final IamportClient iamportClient;

//    public PaymentController() {
//        this.iamportClient = new IamportClient("rest",
//                "api");
//    }

	
	@GetMapping("/payment")
	public String payment() {
		
		return "payment";
	}
	
    @ResponseBody
    @PostMapping("/payments/complete")
    public void /*IamportResponse<Payment>*/ paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
    	System.out.println("-------------------------------");
       /* return iamportClient.paymentByImpUid(imp_uid);*/
    }
}
