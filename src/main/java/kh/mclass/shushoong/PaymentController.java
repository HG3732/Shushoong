package kh.mclass.shushoong;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaymentController {

	private final IamportClient iamportClient;

	@PostMapping("/payment/validation/{imp_uid}")
	@ResponseBody
	public IamportResponse<Payment> validateIamport(@PathVariable String imp_uid) {
		System.out.println("==============");
		System.out.println(imp_uid);
//	        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
		IamportResponse<Payment> payment = null;
		try {
			payment = iamportClient.paymentByImpUid(imp_uid);
			System.out.println("결제 요청 응답. 결제 내역 - 주문 번호: " + payment.getResponse().getMerchantUid());
		} catch (IamportResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payment;
	}
	@PostMapping("/payment/{imp_uid}")
	@ResponseBody
	public String savaPayment(@PathVariable String imp_uid /* , db save dto*/) {
		System.out.println("==============");
		// db save
		return "";
	}
}
