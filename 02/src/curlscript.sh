#!/bin/bash
curl http://147.32.233.18:8888/MI-MDW-ApplicationProtocols/protocol
echo
echo
curl http://147.32.233.18:8888/MI-MDW-ApplicationProtocols/protocol/welcome
echo
echo
curl -H "Accept: text/plain" -X GET http://147.32.233.18:8888/MI-MDW-ApplicationProtocols/protocol/get?name=confusing
echo
echo
curl -H "Accept: text/plain" -H "Accept-Language: en-us" -X POST http://147.32.233.18:8888/MI-MDW-ApplicationProtocols/protocol/post?name=allen
echo
echo
curl -H "Accept: text/html" --referer impression -X POST http://147.32.233.18:8888/MI-MDW-ApplicationProtocols/protocol/referer
echo
echo
curl -H "Accept-Language: en-us" -A expresses -X POST http://147.32.233.18:8888/MI-MDW-ApplicationProtocols/protocol/useragent
echo
echo
curl -b name=rice -X POST http://147.32.233.18:8888/MI-MDW-ApplicationProtocols/protocol/cookie
echo
echo
curl -H "Accept: text/html" --user worse:needed -X POST http://147.32.233.18:8888/MI-MDW-ApplicationProtocols/protocol/auth