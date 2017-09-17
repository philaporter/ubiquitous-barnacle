# High Volume Concurrency Example with BlockingQueue

## Description

This project is meant to demonstrate how you can use the BlockingQueue interface to manage work between threads. 50k simulated JSON requests get loaded into memory at the start of the application. After a brief 5 second pause, the requests get added to the BlockingQueue object. ExecutorService is used to manage a fixed thread pool for consuming the requests on the queue (FIFO).

### Setup

Clone, build, and then run the project. The application uses port 1337 and /hf as the base URL.

### http://localhost:1337/hf/redeem

If you post to that URL after deploying the application, then you can feed the BlockingQueue object a specific request to be processed. In Postman add content-type and application/json to the header. The request body should have the following structure:

`{
	"id":"12345-67890-asdfg-hjklq","type":"redeem","amount":500
}`