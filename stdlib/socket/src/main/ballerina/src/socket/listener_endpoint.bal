// Copyright (c) 2018 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

import ballerina/'lang\.object as lang;

# Represents service endpoint where socket server service registered and start.
#
public type Listener object {

    *lang:AbstractListener;

    public function __init(int port, ListenerConfig? config = ()) {
        var result = self.initServer(port, config ?: {});
        if (result is error) {
            panic result;
        }
    }

    public function __start() returns error? {
        return self.start();
    }

    public function __stop() returns error? {
        return self.stop();
    }

    public function __attach(service s, string? name = ()) returns error? {
        return self.register(s, name);
    }

    function initServer(int port, ListenerConfig config) returns error? = external;

    function register(service s, string? name) returns error? = external;

    function start() returns error? = external;

    function stop() returns error? = external;
};

# Represents the socket server configuration.
#
# + interface - the interface that server with to bind
# + readTimeoutInMilliseconds - Socket read timeout value to be used in milliseconds. Default is 300000 milliseconds (5 minutes)
public type ListenerConfig record {|
    string? interface = ();
    int readTimeoutInMilliseconds = 300000;
|};
