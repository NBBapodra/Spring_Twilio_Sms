package Spring.Twilio.service;

import Spring.Twilio.CallRequest;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class CallService {
    private final Twilioproperties twilioproperties;


@Autowired
    public CallService(Twilioproperties twilioproperties) {
        this.twilioproperties = twilioproperties;
    }

    //send message to number
    public String  makeCall(CallRequest callRequest)
    {
        Call call = Call.creator(
                        new PhoneNumber(callRequest.getNumber()),
                        new PhoneNumber(twilioproperties.getFromNumber()),
                        URI.create("http://demo.twilio.com/docs/voice.xml"))
                .setStatusCallback(URI.create("http://postb.in/1234abcd"))
                .create();


        return call.getStatus().toString();

    }
}
