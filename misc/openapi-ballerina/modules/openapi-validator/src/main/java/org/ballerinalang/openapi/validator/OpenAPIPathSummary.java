/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.openapi.validator;

import io.swagger.v3.oas.models.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * Summary of the OpenAPI documentation for a API path.
 */
class OpenAPIPathSummary {
    private String path;
    private List<String> availableOperations;
    private List<Operation> operations;

    OpenAPIPathSummary() {
        this.availableOperations = new ArrayList<>();
        this.operations = new ArrayList<>();
        this.path = null;
    }

    String getPath() {
        return path;
    }

    void setPath(String path) {
        this.path = path;
    }

    List<String> getAvailableOperations() {
        return availableOperations;
    }

    void setAvailableOperations(List<String> availableOperations) {
        this.availableOperations = availableOperations;
    }

    List<Operation> getOperations() {
        return operations;
    }

    void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    void addOperation(Operation operation) {
        this.operations.add(operation);
    }

    void addAvailableOperation(String operation) {
        this.availableOperations.add(operation);
    }
}