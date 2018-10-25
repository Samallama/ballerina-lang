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

function checkRefEqualityOfTwoTypes() returns boolean {
    int a;
    string b;
    return a === b;
}

function checkRefEqualityOfArraysOfDifferentTypes() returns boolean {
    int[2] a;
    string[2] b;
    boolean bool1 = a === b;

    (float|int)[] c;
    (boolean|xml)[] d;
    boolean bool2 = c === d;

    return bool1 && bool2;
}

function checkRefEqualityOfMapsOfIncompatibleConstraintTypes() returns boolean {
    map<int> a;
    map<float> b;
    boolean bool1 = a === b;

    map<string|int> c;
    map<float> d;
    boolean bool2 = c === d;

    return bool1 && bool2;
}

function checkRefEqualityOfTuplesOfDifferentTypes() returns boolean {
    (string, int) a;
    (boolean, float) b;
    boolean bool1 = a === b;

    (float|int, int) c;
    (boolean, int) d;
    boolean bool2 = c === d;

    return bool1 && bool2;
}

function checkRefEqualityOfRecordsOfIncompatibleTypes() returns boolean {
    Employee e = { name: "Maryam" };
    Person p = { name: "Maryam" };
    return e === p;
}

function checkRefEqualityWithJsonForIncompatibleType() returns boolean {
    (string, int) t = ("Hi", 1);
    json j = "Hi 1";
    boolean bool1 = t === j;

    Employee|(string, int) e = ("Hi", 1);
    j = "Hi 1";
    boolean bool2 = e === j;

    return bool1 && bool2;
}

function checkRefEqualityWithJsonForPrimitiveTypes() returns boolean {
    int i = 1;
    json j = i;
    boolean bool1 = i === j;

    string s = "hi";
    string s1 = s;
    j = s;
    bool1 = s === j;

    float f = 1.0;
    j = f;
    bool1 = f === j;

    boolean b = true;
    boolean b1 = b;
    j = b;
    bool1 = b === j;

    () n = ();
    j = n;
    bool1 = n === j;

    return bool1;
}

function checkRefEqualityOfObjectsOfIncompatibleTypes() returns boolean {
    Abc a = new("abc", 12);
    Def d = new("abc", 12);
    return a === d;
}

type Employee record {
    string name;
    int id;
};

type Person record {
    string name;
    int area;
};

type Abc object {
    public string name;
    private int area;

    public new(name, area) {}
};

type Def object {
    public string name;
    private int id;

    public new(name, id) {}
};
