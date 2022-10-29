#!/bin/bash
(echo "open 147.32.233.18 8888";sleep 1;
echo "POST /MI-MDW-ApplicationProtocols/httpTelnet2 HTTP/1.1";sleep 1;
echo "Host: 147.32.233.18";sleep 1;
echo "Referer: mi-mdw";sleep 1;
echo "Content-Type: application/x-www-form-urlencoded";sleep 1;
echo "Content-Length: 8";sleep 1;
echo ;sleep 1;
echo "data=fit";sleep 1;
echo ;sleep 1;echo ;) | telnet