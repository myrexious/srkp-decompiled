package org.bouncycastle.pqc.crypto.hqc;

import androidx.recyclerview.widget.ItemTouchHelper;
import org.bouncycastle.math.Primes;
import org.tensorflow.lite.schema.BuiltinOperator;

/* loaded from: classes2.dex */
class GFCalculator {
    static int[] exp = {1, 2, 4, 8, 16, 32, 64, 128, 29, 58, 116, 232, 205, 135, 19, 38, 76, 152, 45, 90, 180, 117, 234, 201, 143, 3, 6, 12, 24, 48, 96, 192, 157, 39, 78, 156, 37, 74, BuiltinOperator.RANDOM_UNIFORM, 53, 106, 212, 181, 119, 238, 193, 159, 35, 70, 140, 5, 10, 20, 40, 80, 160, 93, 186, 105, 210, 185, 111, 222, 161, 95, 190, 97, 194, 153, 47, 94, 188, 101, 202, 137, 15, 30, 60, 120, 240, 253, 231, Primes.SMALL_FACTOR_LIMIT, 187, 107, 214, 177, 127, 254, 225, 223, 163, 91, 182, BuiltinOperator.MATRIX_DIAG, 226, 217, 175, 67, 134, 17, 34, 68, 136, 13, 26, 52, 104, 208, 189, 103, 206, 129, 31, 62, 124, 248, 237, 199, BuiltinOperator.BUCKETIZE, 59, 118, 236, 197, BuiltinOperator.DYNAMIC_UPDATE_SLICE, 51, 102, 204, 133, 23, 46, 92, 184, 109, 218, 169, 79, 158, 33, 66, 132, 21, 42, 84, 168, 77, 154, 41, 82, 164, 85, 170, 73, BuiltinOperator.RANDOM_STANDARD_NORMAL, 57, 114, 228, 213, 183, 115, 230, 209, 191, 99, 198, BuiltinOperator.BROADCAST_ARGS, 63, 126, 252, 229, 215, 179, 123, 246, 241, 255, 227, 219, 171, 75, BuiltinOperator.GELU, 49, 98, 196, BuiltinOperator.MULTINOMIAL, 55, 110, 220, 165, 87, 174, 65, 130, 25, 50, 100, 200, 141, 7, 14, 28, 56, 112, 224, 221, 167, 83, 166, 81, 162, 89, 178, 121, 242, 249, 239, 195, 155, 43, 86, 172, 69, 138, 9, 18, 36, 72, BuiltinOperator.ASSIGN_VARIABLE, 61, 122, 244, 245, 247, 243, 251, 235, 203, 139, 11, 22, 44, 88, 176, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 233, 207, 131, 27, 54, 108, 216, 173, 71, 142, 1, 2, 4};
    static int[] log = {0, 0, 1, 25, 2, 50, 26, 198, 3, 223, 51, 238, 27, 104, 199, 75, 4, 100, 224, 14, 52, 141, 239, 129, 28, 193, 105, 248, 200, 8, 76, BuiltinOperator.MATRIX_DIAG, 5, 138, 101, 47, 225, 36, 15, 33, 53, BuiltinOperator.BUCKETIZE, 142, 218, 240, 18, 130, 69, 29, 181, 194, 125, 106, 39, 249, 185, 201, 154, 9, 120, 77, 228, 114, 166, 6, 191, 139, 98, 102, 221, 48, 253, 226, 152, 37, 179, 16, BuiltinOperator.BROADCAST_ARGS, 34, 136, 54, 208, BuiltinOperator.RANDOM_UNIFORM, 206, 143, BuiltinOperator.GELU, 219, 189, 241, 210, 19, 92, 131, 56, 70, 64, 30, 66, 182, 163, 195, 72, 126, 110, 107, 58, 40, 84, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 133, 186, 61, 202, 94, 155, 159, 10, 21, 121, 43, 78, 212, 229, 172, 115, 243, 167, 87, 7, 112, 192, 247, 140, 128, 99, 13, 103, 74, 222, 237, 49, 197, 254, 24, 227, 165, 153, 119, 38, 184, 180, 124, 17, 68, BuiltinOperator.RANDOM_STANDARD_NORMAL, 217, 35, 32, 137, 46, 55, 63, 209, 91, BuiltinOperator.MULTINOMIAL, 188, 207, 205, BuiltinOperator.ASSIGN_VARIABLE, 135, BuiltinOperator.DYNAMIC_UPDATE_SLICE, 178, 220, 252, 190, 97, 242, 86, Primes.SMALL_FACTOR_LIMIT, 171, 20, 42, 93, 158, 132, 60, 57, 83, 71, 109, 65, 162, 31, 45, 67, 216, 183, 123, 164, 118, 196, 23, 73, 236, 127, 12, 111, 246, 108, 161, 59, 82, 41, 157, 85, 170, 251, 96, 134, 177, 187, 204, 62, 90, 203, 89, 95, 176, 156, 169, 160, 81, 11, 245, 22, 235, 122, 117, 44, 215, 79, 174, 213, 233, 230, 231, 173, 232, 116, 214, 244, 234, 168, 80, 88, 175};

    GFCalculator() {
    }

    public static int inverse(int i) {
        return exp[255 - log[i]] & Utils.toUnsigned16Bits((-i) >> 31);
    }

    static int mod(int i) {
        int unsigned16Bits = Utils.toUnsigned16Bits(i - 255);
        return Utils.toUnsigned16Bits(unsigned16Bits + (Utils.toUnsigned8bits(-(unsigned16Bits >> 15)) & 255));
    }

    public static int mult(int i, int i2) {
        int unsigned16Bits = Utils.toUnsigned16Bits((-i) >> 31) & Utils.toUnsigned16Bits((-i2) >> 31);
        int[] iArr = exp;
        int[] iArr2 = log;
        return Utils.toUnsigned16Bits(iArr[mod(iArr2[i] + iArr2[i2])] & unsigned16Bits);
    }
}
