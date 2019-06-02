#!/usr/bin/env bash

fission route delete --name cleaner
fission route delete --name viewer
fission tt delete --name incrementer
fission fn delete --name cleaner
fission fn delete --name viewer
fission fn delete --name incrementer
fission env delete --name jvm
