import ballerina/http;
import ballerina/io;

listener http:MockListener passthruEP  = new(9092);

@http:ServiceConfig {
    basePath:"/sample/{version}",
    versioning:{
       pattern:"v{Major}.{Minor}",
       allowNoVersion:true,
       matchMajorVersion:true
    }
}
service sample on passthruEP {

    @http:ResourceConfig {
        path:"/go"
    }
    resource function sample(http:Caller caller, http:Request req) {
        http:Response res = new;
        res.setJsonPayload({hello:"common service"});
        checkpanic caller->respond(res);
    }
}
