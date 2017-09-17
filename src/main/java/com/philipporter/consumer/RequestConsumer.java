package com.philipporter.consumer;

import com.philipporter.request.Request;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;

/**
 * @author Philip Porter
 */
public class RequestConsumer implements Runnable {
    
    private static final Logger LOG = Logger.getLogger(RequestConsumer.class);
    private BlockingQueue<Request> queue;
    
    public RequestConsumer(BlockingQueue<Request> queue){
        this.queue = queue;
    }
    
    @Override
    public void run() {
        try{
            Request req = null;
            while(true){
                req = queue.take();
                LOG.info(new StringBuilder().append("Consumed: ").append(req.getId()).toString());
            }
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
