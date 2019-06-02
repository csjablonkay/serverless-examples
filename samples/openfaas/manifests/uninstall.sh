#!/usr/bin/env bash
kubectl delete service redisclient-master redisclient-slave
kubectl delete deployments redisclient-slave
kubectl delete statefulsets redisclient-master
kubectl delete pod --all
kubectl delete persistentvolumeclaims --all
kubectl delete persistentvolumes --all
