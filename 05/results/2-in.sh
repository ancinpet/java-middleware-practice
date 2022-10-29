#!/bin/bash
#only works on freshly generated DB - otherwise the confirmation will be for different customer DELETE
curl http://localhost:7001/hw5/customer
echo 
echo 
sleep 1
curl -X DELETE http://localhost:7001/hw5/customer/2
echo 
echo 
sleep 1
curl http://localhost:7001/hw5/customer
echo 
echo 
sleep 1
curl http://localhost:7001/hw5/confirm/0
echo 
echo 
sleep 1
curl http://localhost:7001/hw5/customer
echo 
echo 
sleep 3
curl http://localhost:7001/hw5/confirm/0
echo 
echo 
sleep 8
curl http://localhost:7001/hw5/confirm/0
echo 
echo 
sleep 1
curl http://localhost:7001/hw5/customer
echo 
echo 
sleep 5
curl http://localhost:7001/hw5/confirm/0
echo 
echo 
sleep 1
curl http://localhost:7001/hw5/customer

