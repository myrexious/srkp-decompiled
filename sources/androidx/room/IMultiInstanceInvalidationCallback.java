package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IMultiInstanceInvalidationCallback extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IMultiInstanceInvalidationCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.room.IMultiInstanceInvalidationCallback
        public void onInvalidation(String[] tables) throws RemoteException {
        }
    }

    void onInvalidation(String[] tables) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IMultiInstanceInvalidationCallback {
        private static final String DESCRIPTOR = "androidx.room.IMultiInstanceInvalidationCallback";
        static final int TRANSACTION_onInvalidation = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMultiInstanceInvalidationCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMultiInstanceInvalidationCallback)) {
                return (IMultiInstanceInvalidationCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onInvalidation(data.createStringArray());
                return true;
            } else if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            } else {
                return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        public static class Proxy implements IMultiInstanceInvalidationCallback {
            public static IMultiInstanceInvalidationCallback sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.room.IMultiInstanceInvalidationCallback
            public void onInvalidation(String[] tables) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringArray(tables);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInvalidation(tables);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMultiInstanceInvalidationCallback impl) {
            if (Proxy.sDefaultImpl == null) {
                if (impl != null) {
                    Proxy.sDefaultImpl = impl;
                    return true;
                }
                return false;
            }
            throw new IllegalStateException("setDefaultImpl() called twice");
        }

        public static IMultiInstanceInvalidationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
