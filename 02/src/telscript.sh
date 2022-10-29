#!/bin/bash
(echo "open 147.32.233.18 8888";sleep 1;
echo "GET /MI-MDW-ApplicationProtocols/httpTelnet1 HTTP/1.1";sleep 1;
echo "Host: 147.32.233.18";sleep 1;
echo "User-Agent: fit-telnet";sleep 1;
echo "Accept: text/html";sleep 1;
echo "Accept-Language: en-US";sleep 1;
echo "Accept-Charset: utf-8";sleep 1;echo ;sleep 1;echo ;) | telnet