#!/usr/bin/python
import redis
import os
import sys
import json

def main():
    client = redis.Redis(host=os.environ['REDIS_HOST'], port=6379, db=0, decode_responses=True)
    client.set("act","0")
    sys.stdout.write(json.dumps({"act":0}))
    sys.stdout.flush()

if __name__ == '__main__':    
    main()
