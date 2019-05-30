#!/usr/bin/env bash

#Create java environment:
fission env create --name jvm --image csjablonkay/fission-undertow-jvm-env --version 2 --keeparchive --mincpu 100 --maxcpu 500 --minmemory 256 --maxmemory 1024 --spec

#Create incrementer function
fission fn create --name incrementer --deploy incrementer.fission-0.0.1-SNAPSHOT-shaded.jar --env jvm --entrypoint csana.incrementer.fission.Fission --configmap hello --executortype newdeploy --minscale 1 --maxscale 1 --spec
#Create incrementer timetrigger
fission tt create --name incrementer --function incrementer --cron "@every 1m" --spec

#Create viewer function
fission fn create --name viewer --deploy viewer.fission-0.0.1-SNAPSHOT-shaded.jar --env jvm --entrypoint csana.viewer.fission.Fission --configmap hello --spec
#Create viewer route
fission route create --name viewer --function viewer --url /view --method GET --spec

#Create cleaner function
fission fn create --name cleaner --deploy cleaner.fission-0.0.1-SNAPSHOT-shaded.jar --env jvm --entrypoint csana.cleaner.fission.Fission --configmap hello --spec
#Create cleaner route
fission route create --name cleaner --function cleaner --url /clean --method GET --spec
