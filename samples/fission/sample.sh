#!/usr/bin/env bash

#Create java environment:
fission env create --name jvm --image csjablonkay/fission-undertow-jvm-env --version 2 --keeparchive --mincpu 100 --maxcpu 500 --minmemory 256 --maxmemory 1024

#Create incrementer function poolmanager
fission fn create --name incrementer --deploy incrementer.fission-0.0.1-SNAPSHOT-shaded.jar --env jvm --entrypoint csana.incrementer.fission.Fission --configmap hello

#Create sample route
fission route create --name incrementer --function incrementer --url /increment --method GET

#Call
curl http://$FISSION_ROUTER/increment

#Get log
kubectl --namespace fission-function get pod -l functionName=incrementer
kubectl --namespace fission-function logs <name> jvm

#Create incrementer function newdeploy
fission fn create --name incrementer2 --deploy incrementer.fission-0.0.1-SNAPSHOT-shaded.jar --env jvm --entrypoint csana.incrementer.fission.Fission --configmap hello --executortype newdeploy --minscale 1 --maxscale 1

#Create sample route
fission route create --name incrementer2 --function incrementer2 --url /increment2 --method GET

#Call
curl http://$FISSION_ROUTER/increment2

#Get log
kubectl --namespace fission-function get pod -l functionName=incrementer2
kubectl --namespace fission-function logs newdeploy-incrementer2-default-wye74wob-6dd6f5b757-5f4nq incrementer2

#Remove resources
fission route delete --name incrementer2
fission route delete --name incrementer
fission fn delete --name incrementer2
fission fn delete --name incrementer
fission env delete --name jvm
