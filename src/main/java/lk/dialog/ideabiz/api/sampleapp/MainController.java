package lk.dialog.ideabiz.api.sampleapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lk.dialog.ideabiz.library.APIHandler.SMSHandler;
import lk.dialog.ideabiz.library.APIHandler.model.SMS.SMSMessage;
import lk.dialog.ideabiz.library.LibraryManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Malinda_07654 on 2/9/2016.
 */
@Controller
@RequestMapping("/sms/")
public class MainController {

    public static Gson gson = null;
    final static Logger logger = Logger.getLogger(MainController.class);
    SMSHandler smsHandler;

    public MainController() {
        gson = new GsonBuilder().serializeNulls().create();
    }


    @RequestMapping(value = "inbound", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseEntity<String> inbound(HttpServletRequest request, @RequestBody String body) {

        //Create sms handler if its null
        if (smsHandler == null)
            smsHandler = new SMSHandler("https://ideabiz.lk/apicall/smsmessaging/v2/outbound/94777339033/requests", LibraryManager.getApiCall(), 1, "7555", "tel:7555");

        logger.info("Inbound SMS : " + body);
        //Reading SMS
        SMSMessage smsMessage = smsHandler.readSMS(body);

        //Sending sms
        Boolean status;
        try {
            status = smsHandler.sendSMS(smsMessage.getSourceAddress(), smsMessage.getMessage());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(gson.toJson(status), HttpStatus.OK);

    }

    @RequestMapping(value = "sendSMS", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<String> inbound(HttpServletRequest request,@RequestParam String msisdn,@RequestParam String message) {

        if (smsHandler == null)
            smsHandler = new SMSHandler("https://ideabiz.lk/apicall/smsmessaging/v2/outbound/94777339033/requests", LibraryManager.getApiCall(), 1, "7555", "tel:7555");

        logger.info("Send SMS : " + message);

        Boolean status;
        try {
            status = smsHandler.sendSMS("tel:+" + msisdn, message);
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(gson.toJson(status), HttpStatus.OK);

    }

}