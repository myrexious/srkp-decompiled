package com.tom_roush.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

/* loaded from: classes3.dex */
class ConditionalOperators {
    private ConditionalOperators() {
    }

    /* loaded from: classes3.dex */
    static class If implements Operator {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            InstructionSequence instructionSequence = (InstructionSequence) stack.pop();
            if (((Boolean) stack.pop()).booleanValue()) {
                instructionSequence.execute(executionContext);
            }
        }
    }

    /* loaded from: classes3.dex */
    static class IfElse implements Operator {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            InstructionSequence instructionSequence = (InstructionSequence) stack.pop();
            InstructionSequence instructionSequence2 = (InstructionSequence) stack.pop();
            if (((Boolean) stack.pop()).booleanValue()) {
                instructionSequence2.execute(executionContext);
            } else {
                instructionSequence.execute(executionContext);
            }
        }
    }
}
