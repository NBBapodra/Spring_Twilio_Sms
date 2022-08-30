package Spring.Twilio;
import Spring.Twilio.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.twilio.exception.ApiException;

import Spring.Twilio.service.Smsservice;


@Controller
public class TwilioController {

	@Autowired
	private Smsservice smsservice;

	@Autowired
	private CallService callService;

	@RequestMapping("/")
	public String homepage(ModelAndView model) {
		return "index";
	}

	@PostMapping("/sendSMS")
	public ResponseEntity<Object> sendmessage(Smsrequest smsrequest) {
		String status = smsservice.sendsms(smsrequest);
		if ("sent".equals(status) || "queued".equals(status)) {
			return new ResponseEntity<Object>("sent successfully", HttpStatus.OK);
		}
		return new ResponseEntity<Object>("failed to send message", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/makeCall")
	public ResponseEntity<Object> makeCall(CallRequest callRequest)
	{
		String status= callService.makeCall(callRequest);

		if ("sent".equals(status) || "queued".equals(status)) {
			return new ResponseEntity<Object>("Making Call.........", HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Disconnected........", HttpStatus.NOT_FOUND);
	}
	}
   

