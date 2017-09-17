package com.philipporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Philip Porter
 */
@Configuration
public class AppConfig {
    
    private static final Logger LOG = Logger.getLogger(AppConfig.class);
    
    @Bean
    public ArrayList<String> requestList(){
        ArrayList<String> list = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("jsonRequests"));
            while(br.ready()){
                list.add(br.readLine());
            }
            return list;
        }catch(IOException ioe){
            LOG.error("Unable to read up the JSON requests file");
            LOG.error(ioe);
            return null;
        }
    }
}
