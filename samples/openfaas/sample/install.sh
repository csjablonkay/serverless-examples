#!/usr/bin/env bash
faas-cli build -f ./incrementer.yml
faas-cli push -f ./incrementer.yml
faas-cli deploy -f ./incrementer.yml

#Call url
curl http://192.168.99.100:31112/function/increment

#Logs
kubectl --namespace openfaas-fn get pods
kubectl --namespace openfaas-fn logs <name>
