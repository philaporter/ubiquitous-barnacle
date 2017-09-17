package com.philipporter.producer;

import com.philipporter.request.Request;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Philip Porter
 */
public class RequestProducer implements Runnable {
    
    private BlockingQueue<Request> queue;
    private ArrayList<String> requestList;
    private static final Logger LOG = Logger.getLogger(RequestProducer.class);
    
    public RequestProducer(BlockingQueue<Request> queue, ArrayList<String> requestList){
        this.queue = queue;
        this.requestList = requestList;
    }
    
    @Override
    public void run(){
        ObjectMapper mapper = new ObjectMapper();
        for(String json: requestList){
            try {
                Request req = mapper.readValue(json, Request.class);
                queue.put(req);
                LOG.info(new StringBuilder().append("Simulated request: ").append(req.getId()).append(" ").append(req.getAmount()).toString());
            } catch (IOException ie) {
                LOG.error("Unable to map the JSON values to Request");
                LOG.error(ie);
            } catch (InterruptedException ie) {
                LOG.error("Unable to add the Request to the queue");
                LOG.error(ie);
            }            
        }
    }
}
