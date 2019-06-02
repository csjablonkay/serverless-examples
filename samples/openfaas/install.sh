#!/usr/bin/env bash
faas-cli build -f ./hello.yml
faas-cli push -f ./hello.yml
faas-cli deploy -f ./hello.yml
