/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.ballerinalang.observe.nativeimpl;

import org.ballerinalang.bre.Context;
import org.ballerinalang.bre.bvm.BlockingNativeCallableUnit;
import org.ballerinalang.jvm.BallerinaErrors;
import org.ballerinalang.jvm.scheduling.Strand;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.ReturnType;

/**
 * This function which implements the finishSpan method for observe.
 */
@BallerinaFunction(
        orgName = "ballerina",
        packageName = "observe",
        functionName = "finishSpan",
        args = {
                @Argument(name = "spanId", type = TypeKind.INT),
        },
        returnType = @ReturnType(type = TypeKind.BOOLEAN),
        isPublic = true
)
public class FinishSpan extends BlockingNativeCallableUnit {
    @Override
    public void execute(Context context) {
//        int spanId = (int) context.getIntArgument(0);
//        boolean isFinished = OpenTracerBallerinaWrapper.getInstance().finishSpan(context, spanId);
//
//        if (!isFinished) {
//            context.setReturnValues(Utils.createError(context,
//                    "Can not finish span with id " + spanId + ". Span already finished"));
//        }
    }

    public static Object finishSpan(Strand strand, long spanId) {
        boolean isFinished = OpenTracerBallerinaWrapper.getInstance().finishSpan(strand, spanId);

        if (isFinished) {
            return null;
        }

        return BallerinaErrors.createError("Can not finish span with id " + spanId + ". Span already finished");
    }
}
