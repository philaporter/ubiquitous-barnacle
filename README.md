# High Volume Concurrency Example with BlockingQueue

## Description

This project is meant to demonstrate how you can use the BlockingQueue interface to manage work between threads. I made 50k simulated JSON requests that get loaded into memory at the start of the application. After a brief wait, the requests start getting added to the ArrayBlockingQueue object, and I used ExecutorService to manage a fixed thread pool for consuming the requests on the queue (FIFO).

### Setup

Clone, build, and then run the project. It will use port 1337 and use /hf as the base URL.

## http://localhost:1337/hf/redeem

If you post to that URL, then you can feed the BlockingQueue object a specific request to be processed. If you're using Postman, add Content/Type and application/json to the header. The request body should look like this:

`{
	"id":"12345-67890-asdfg-hjklq","type":"redeem","amount":500
}`