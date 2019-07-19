/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ballerinalang.jvm;

import org.ballerinalang.jvm.util.exceptions.BallerinaErrorReasons;

/**
 * Common utility methods used for arithmatic operations.
 *
 * @since 1.0
 */
public class MathUtils {

    public static long divide(long numerator, long denominator) {
        if (denominator == 0) {
            throw BallerinaErrors.createError(BallerinaErrorReasons.DIVISION_BY_ZERO_ERROR, " / by zero");
        }
        return numerator / denominator;
    }

    public static double divide(double numerator, double denominator) {
        if (denominator == 0) {
            throw BallerinaErrors.createError(BallerinaErrorReasons.DIVISION_BY_ZERO_ERROR, " / by zero");
        }
        return numerator / denominator;
    }

    public static long remainder(long numerator, long denominator) {
        if (denominator == 0) {
            throw BallerinaErrors.createError(BallerinaErrorReasons.DIVISION_BY_ZERO_ERROR, " / by zero");
        }
        return numerator % denominator;
    }

    public static double remainder(double numerator, double denominator) {
        if (denominator == 0) {
            throw BallerinaErrors.createError(BallerinaErrorReasons.DIVISION_BY_ZERO_ERROR, " / by zero");
        }
        return numerator % denominator;
    }
}
