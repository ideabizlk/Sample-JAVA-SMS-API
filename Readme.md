# Java SMS sample app for Ideabiz

Sample sms sending and recieving JAVA app using spring and maven. (Modification of (https://github.com/ideabizlk/IdeaBiz-Request-Handler-JAVA/tree/master/Sample)

### Flow
* Maven install bellow projects

	Ideabiz-APICallHandler (https://github.com/ideabizlk/IdeaBiz-Request-Handler-JAVA)
	
	ideabiz-common-java-class (https://github.com/ideabizlk/Classes-JAVA)
	
	ideabiz-sms-handler (https://github.com/ideabizlk/IdeaBiz-SMS-API-Handler-JAVA)
	
* Create mysql DB and run `Database.sql`
* Config `log4.properties` file `log4j.appender.file.File` File path
* Create Ideabiz App on ideabiz.lk
* Subscribe for relevent API's (SMS for this sample)
* Generate `consumer key`, `consumer secret`, `refresh token`, `access token` 
* add above to newly created table
* config `database.properties`
* Deploy project on tomcat or run as meven goal `jetty:run` (this project can run independently with IDE)
* Send SMS inboud API call to `{serverURL}/sms/inbound`


### Sample Inbound SMS Request 

DOC (http://docs.ideabiz.lk/en/APIs/SMS)

Send  `POST` Bellow request

URL 
```
{serverURL}/sms/inbound
```

BODY
```
{
  "inboundSMSMessageNotification": {
    "inboundSMSMessage": {
      "dateTime": "",
      "destinationAddress": "tel:87711",
      "messageId": "113883",
      "message": "My message",
      "senderAddress": "tel:+94777123456"
    }
  }
}
```