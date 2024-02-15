package org.visp.imgproc;

import org.visp.core.VpImagePoint;
import org.visp.core.VpImageUChar;

/* loaded from: classes4.dex */
public class VpDirection {
    public int mDirectionType;
    public int[] m_dirx;
    public int[] m_diry;

    public VpDirection() {
        this.m_dirx = r1;
        this.m_diry = r0;
        int[] iArr = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] iArr2 = {-1, -1, 0, 1, 1, 1, 0, -1};
    }

    public VpDirection clockwise() {
        VpDirection vpDirection = new VpDirection();
        vpDirection.mDirectionType = (this.mDirectionType + 1) % 8;
        return vpDirection;
    }

    public VpDirection counterClockwise() {
        VpDirection vpDirection = new VpDirection();
        vpDirection.mDirectionType = (this.mDirectionType - 1) % 8;
        return vpDirection;
    }

    public VpImagePoint active(VpImageUChar vpImageUChar, VpImagePoint vpImagePoint) {
        int _iVar = (int) (vpImagePoint.get_i() + this.m_diry[this.mDirectionType]);
        int _jVar = (int) (vpImagePoint.get_j() + this.m_dirx[this.mDirectionType]);
        if (_jVar < 0 || _jVar >= vpImageUChar.cols() || _iVar < 0 || _iVar >= vpImageUChar.rows()) {
            return new VpImagePoint(-1.0d, -1.0d);
        }
        return vpImageUChar.getPixel(_iVar, _jVar) != 0 ? new VpImagePoint(_iVar, _jVar) : new VpImagePoint(-1.0d, -1.0d);
    }

    /* loaded from: classes4.dex */
    public class VpDirectionType {
        public static final int EAST = 2;
        public static final int LAST_DIRECTION = 8;
        public static final int NORTH = 0;
        public static final int NORTH_EAST = 1;
        public static final int NORTH_WEST = 7;
        public static final int SOUTH = 4;
        public static final int SOUTH_EAST = 3;
        public static final int SOUTH_WEST = 5;
        public static final int WEST = 6;

        public VpDirectionType() {
            VpDirection.this = r1;
        }
    }
}
