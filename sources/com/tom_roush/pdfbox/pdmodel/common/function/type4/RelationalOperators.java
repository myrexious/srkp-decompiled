package com.tom_roush.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

/* loaded from: classes3.dex */
class RelationalOperators {
    private RelationalOperators() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Eq implements Operator {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            stack.push(Boolean.valueOf(isEqual(stack.pop(), stack.pop())));
        }

        protected boolean isEqual(Object obj, Object obj2) {
            if ((obj instanceof Number) && (obj2 instanceof Number)) {
                return ((Number) obj).floatValue() == ((Number) obj2).floatValue();
            }
            return obj.equals(obj2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class AbstractNumberComparisonOperator implements Operator {
        protected abstract boolean compare(Number number, Number number2);

        private AbstractNumberComparisonOperator() {
        }

        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            stack.push(Boolean.valueOf(compare((Number) stack.pop(), (Number) stack.pop())));
        }
    }

    /* loaded from: classes3.dex */
    static class Ge extends AbstractNumberComparisonOperator {
        public Ge() {
            super();
        }

        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() >= number2.floatValue();
        }
    }

    /* loaded from: classes3.dex */
    static class Gt extends AbstractNumberComparisonOperator {
        public Gt() {
            super();
        }

        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() > number2.floatValue();
        }
    }

    /* loaded from: classes3.dex */
    static class Le extends AbstractNumberComparisonOperator {
        public Le() {
            super();
        }

        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() <= number2.floatValue();
        }
    }

    /* loaded from: classes3.dex */
    static class Lt extends AbstractNumberComparisonOperator {
        public Lt() {
            super();
        }

        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() < number2.floatValue();
        }
    }

    /* loaded from: classes3.dex */
    static class Ne extends Eq {
        @Override // com.tom_roush.pdfbox.pdmodel.common.function.type4.RelationalOperators.Eq
        protected boolean isEqual(Object obj, Object obj2) {
            return !super.isEqual(obj, obj2);
        }
    }
}
