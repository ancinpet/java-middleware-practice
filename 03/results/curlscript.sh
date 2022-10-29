#!/bin/bash

echo "Default functionality test --------------------------------"
curl --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=new
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=payment
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=completed
rm cookies.txt
echo
echo
echo "Payment skip test --------------------------------"
curl --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=new
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=completed
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=payment
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=completed
rm cookies.txt
echo
echo
echo "User navigation test --------------------------------"
curl --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=new
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=payment
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=completed
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=new
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=payment
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=completed
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=new
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=payment
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=completed
rm cookies.txt
echo
echo
echo "Bad function test --------------------------------"
curl --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=payment
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=completed
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=completed
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=dasdsa
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?nosdsad=sadas
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=new
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?nosdsad=sadas
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=payment
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=blabla
echo
curl --cookie cookies.txt --cookie-jar cookies.txt http://127.0.0.1:7001/hw3/booking?state=completed
rm cookies.txt



