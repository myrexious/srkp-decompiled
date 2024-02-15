package com.tom_roush.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

/* loaded from: classes3.dex */
class BitwiseOperators {
    private BitwiseOperators() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class AbstractLogicalOperator implements Operator {
        protected abstract boolean applyForBoolean(boolean z, boolean z2);

        protected abstract int applyforInteger(int i, int i2);

        private AbstractLogicalOperator() {
        }

        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            Object pop = stack.pop();
            Object pop2 = stack.pop();
            if ((pop2 instanceof Boolean) && (pop instanceof Boolean)) {
                stack.push(Boolean.valueOf(applyForBoolean(((Boolean) pop2).booleanValue(), ((Boolean) pop).booleanValue())));
            } else if ((pop2 instanceof Integer) && (pop instanceof Integer)) {
                stack.push(Integer.valueOf(applyforInteger(((Integer) pop2).intValue(), ((Integer) pop).intValue())));
            } else {
                throw new ClassCastException("Operands must be bool/bool or int/int");
            }
        }
    }

    /* loaded from: classes3.dex */
    static class And extends AbstractLogicalOperator {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.BitwiseOperators.AbstractLogicalOperator
        protected boolean applyForBoolean(boolean z, boolean z2) {
            return z && z2;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.BitwiseOperators.AbstractLogicalOperator
        protected int applyforInteger(int i, int i2) {
            return i & i2;
        }

        public And() {
            super();
        }
    }

    /* loaded from: classes3.dex */
    static class Bitshift implements Operator {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            int intValue = ((Integer) stack.pop()).intValue();
            int intValue2 = ((Integer) stack.pop()).intValue();
            if (intValue < 0) {
                stack.push(Integer.valueOf(intValue2 >> Math.abs(intValue)));
            } else {
                stack.push(Integer.valueOf(intValue2 << intValue));
            }
        }
    }

    /* loaded from: classes3.dex */
    static class False implements Operator {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Boolean.FALSE);
        }
    }

    /* loaded from: classes3.dex */
    static class Not implements Operator {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            Object pop = stack.pop();
            if (pop instanceof Boolean) {
                stack.push(Boolean.valueOf(!((Boolean) pop).booleanValue()));
            } else if (pop instanceof Integer) {
                stack.push(Integer.valueOf(-((Integer) pop).intValue()));
            } else {
                throw new ClassCastException("Operand must be bool or int");
            }
        }
    }

    /* loaded from: classes3.dex */
    static class Or extends AbstractLogicalOperator {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.BitwiseOperators.AbstractLogicalOperator
        protected boolean applyForBoolean(boolean z, boolean z2) {
            return z || z2;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.BitwiseOperators.AbstractLogicalOperator
        protected int applyforInteger(int i, int i2) {
            return i | i2;
        }

        public Or() {
            super();
        }
    }

    /* loaded from: classes3.dex */
    static class True implements Operator {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Boolean.TRUE);
        }
    }

    /* loaded from: classes3.dex */
    static class Xor extends AbstractLogicalOperator {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.BitwiseOperators.AbstractLogicalOperator
        protected boolean applyForBoolean(boolean z, boolean z2) {
            return z ^ z2;
        }

        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.BitwiseOperators.AbstractLogicalOperator
        protected int applyforInteger(int i, int i2) {
            return i ^ i2;
        }

        public Xor() {
            super();
        }
    }
}
