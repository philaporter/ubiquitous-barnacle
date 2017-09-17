package com.philipporter.request;

import com.philipporter.consumer.RequestConsumer;
import com.philipporter.producer.RequestProducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Philip Porter
 */
@RestController
public class RequestController {

    @Autowired
    private ArrayList<String> requestList;
    private BlockingQueue<Request> queue;
    private static final Logger LOG = Logger.getLogger(RequestController.class);

    @PostConstruct
    public void init() {
        try {
            LOG.info("======================================");
            LOG.info("Start simulating traffic in 5 seconds");
            LOG.info("======================================");
            Thread.sleep(5000);

            queue = new ArrayBlockingQueue<>(10);
            RequestProducer producer = new RequestProducer(queue, requestList);
            new Thread(producer).start();
 
            ExecutorService executor = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 10; i++) {
                Runnable worker = new RequestConsumer(queue);
                executor.execute(worker);
            }

        } catch (InterruptedException ie) {
            LOG.error("Unable to start mocking the incoming request traffic");
            LOG.error(ie);
        }
    }

    @RequestMapping(value = "/redeem", method = RequestMethod.POST)
    public ResponseEntity<String> addRequest(@RequestBody Request req) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LOG.info("UI submitted " + mapper.writeValueAsString(req));
            queue.add(req);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IOException ie) {
            LOG.error("Unable to map the Request object to JSON");
            LOG.error(ie);
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
    }
}
