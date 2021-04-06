package com.iat.iat.flutterWave;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.security.MyUserDetailsService;
import com.iat.iat.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlutterWaveService {
    @Autowired MyUserDetailsService myUserDetailsService;
    @Autowired
    RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(FlutterWaveService.class);

    public FlutterResp initiate(double amount, int tx_ref, User user){

        logger.info("Initiating payment...");
        PayLoad payLoad= new PayLoad();
        payLoad.setAmount(amount);
        payLoad.setCurrency("UGX");
        payLoad.setCustomer(new Customer(user.getEmail().replaceAll("\\s", ""), user.getContact(), user.getName()));

        payLoad.setCustomizations(new Customizations("IAT-Wallet","Deposit",""));
        payLoad.setMeta(new Meta(user.getId()));
        payLoad.setPaymentOptions("card");
        payLoad.setRedirect_url("http://iatnigeria.com:8087/payment/verify");
        payLoad.setTx_ref(tx_ref);

        logger.info("connecting to flutterWave service...");
        final String uri = "https://api.flutterwave.com/v3/payments";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + "FLWSECK-154fabdfee686a298e49948b49227cc0-X");
        HttpEntity<PayLoad> request = new HttpEntity<>(payLoad, headers);

        logger.info("connecting...");
        ResponseEntity<FlutterResp> response = restTemplate.exchange(uri, HttpMethod.POST, request, FlutterResp.class);
        HttpStatus httpStatus = response.getStatusCode();
        if(httpStatus.equals(HttpStatus.OK)){
            logger.info("flutterWave service status..."+ httpStatus);
            logger.info(user.getEmail().replaceAll("\\s", ""));
            return response.getBody();
        }
        logger.info("flutterWave service status..."+ httpStatus);
        throw new ResourceNotFoundException("external Service Error FW");
    }



    public VerifyRespFW verify(String transactionId) throws JsonProcessingException {
        logger.info("connecting to flutterWave service...");
        final String uri = "https://api.flutterwave.com/v3/transactions/";
        HttpHeaders headers =new HttpHeaders();
        headers.add("Authorization", "Bearer " + "FLWSECK-154fabdfee686a298e49948b49227cc0-X");
        HttpEntity<String> request = new HttpEntity<>(headers);

        logger.info("connecting...");//VerifyRespFW
        ResponseEntity<String> response = restTemplate.exchange(uri+transactionId+"/verify", HttpMethod.GET, request, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(response.getBody(), VerifyRespFW.class);

    }

   /* public FlutterResp initiate(double amount){
        return null;
    }*/
}
