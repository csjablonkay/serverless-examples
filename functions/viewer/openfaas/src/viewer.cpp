#include <Poco/JSON/Parser.h>
#include <hiredis/hiredis.h>
#include <iostream>
#include <string>

using namespace Poco::JSON;
using namespace std;

int main(int argc, char** argv)
{
    redisContext* c = redisConnect(getenv("REDIS_HOST"), 6379);
    void* reply;
    reply = redisCommand(c, "GET %s","act");
    redisReply* replyRedis = (struct redisReply*)reply;
    string redisValue(replyRedis->str);
    Poco::JSON::Object retObject;
    retObject.set("act", stoi(redisValue));
    retObject.stringify(cout);
    cout.flush();
    freeReplyObject(replyRedis);
    redisFree(c);
}