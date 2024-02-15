package org.informatika.sirekap.support;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import java.io.File;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.ResourceRefType;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ElectionPageStage;
import org.informatika.sirekap.ui.autocapture.PolygonView;
import org.opencv.core.Point;

/* compiled from: DataBindingAdapters.kt */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u0004H\u0007\u001a\u001a\u0010\u0005\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\u001c\u0010\n\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007\u001a\u001a\u0010\f\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0007\u001a\u001a\u0010\u0010\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\tH\u0007\u001a!\u0010\u0012\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\u0016\u001a\u001a\u0010\u0017\u001a\u00020\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007\u001a\u001c\u0010\u001c\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007\u001a\u001c\u0010\u001f\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007\u001a!\u0010 \u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\u0016\u001a(\u0010!\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\"2\u0014\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020%\u0018\u00010$H\u0007¨\u0006&"}, d2 = {"bitmap", "", "imageView", "Landroid/widget/ImageView;", "Landroid/graphics/Bitmap;", "dividerItemDecorationVertical", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "enabled", "", ResourceRefType.FILE_PATH, "", "hidden", "view", "Landroid/view/View;", "isHide", "invisible", "isInvisible", "seHintTextBackgroundColor", "textView", "Landroid/widget/TextView;", "checked", "(Landroid/widget/TextView;Ljava/lang/Boolean;)V", "setElectionImageStatusChip", "chip", "Lcom/google/android/material/chip/Chip;", "electionImageStatus", "", "setElectionPageStageIcon", "electionPageStage", "Lorg/informatika/sirekap/model/ElectionPageStage;", "setElectionPageStageStepper", "setFormC1CheckTextColor", "setPolygonPoint", "Lorg/informatika/sirekap/ui/autocapture/PolygonView;", "omrPoints", "", "Lorg/opencv/core/Point;", "app_productionRelease"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DataBindingAdaptersKt {
    @BindingAdapter({"invisible"})
    public static final void invisible(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setVisibility(z ? 4 : 0);
    }

    @BindingAdapter({"hidden"})
    public static final void hidden(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setVisibility(z ? 8 : 0);
    }

    @BindingAdapter({ResourceRefType.FILE_PATH})
    public static final void filePath(ImageView imageView, String str) {
        if (imageView != null) {
            String str2 = str;
            if ((str2 == null || str2.length() == 0) || !new File(str).exists()) {
                return;
            }
            imageView.setImageBitmap(BitmapFactory.decodeFile(str));
        }
    }

    @BindingAdapter({"bitmap"})
    public static final void bitmap(ImageView imageView, Bitmap bitmap) {
        if (imageView == null || bitmap == null) {
            return;
        }
        imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter({"formC1CheckTextColor"})
    public static final void setFormC1CheckTextColor(TextView textView, Boolean bool) {
        int paintFlags;
        if (textView != null) {
            if (Intrinsics.areEqual((Object) bool, (Object) false)) {
                paintFlags = textView.getPaintFlags() | 16;
            } else {
                paintFlags = textView.getPaintFlags() & (-17);
            }
            textView.setPaintFlags(paintFlags);
            if (bool != null) {
                textView.setTextColor(ContextCompat.getColor(textView.getContext(), bool.booleanValue() ? R.color.colorSuccess : R.color.colorError));
            } else {
                textView.setTextColor(ContextCompat.getColor(textView.getContext(), R.color.textColorPrimaryDark60));
            }
        }
    }

    @BindingAdapter({"electionImageStatusChip"})
    public static final void setElectionImageStatusChip(Chip chip, int i) {
        String string;
        if (chip != null) {
            if (i == 1) {
                string = chip.getContext().getString(R.string.not_sent);
            } else if (i == 2) {
                string = chip.getContext().getString(R.string.processed);
            } else if (i == 3) {
                string = chip.getContext().getString(R.string.form_valid);
            } else if (i == 4) {
                string = chip.getContext().getString(R.string.form_invalid);
            }
            chip.setText(string);
            if (i == 1) {
                chip.setChipStrokeColor(ContextCompat.getColorStateList(chip.getContext(), R.color.colorGreyDark));
                chip.setTextColor(ContextCompat.getColor(chip.getContext(), R.color.colorGreyDark));
            } else if (i == 2) {
                chip.setChipStrokeColor(ContextCompat.getColorStateList(chip.getContext(), R.color.colorInfo60));
                chip.setTextColor(ContextCompat.getColor(chip.getContext(), R.color.colorInfo60));
            } else if (i == 3) {
                chip.setChipStrokeColor(ContextCompat.getColorStateList(chip.getContext(), R.color.colorSuccess));
                chip.setTextColor(ContextCompat.getColor(chip.getContext(), R.color.colorSuccess));
            } else if (i != 4) {
            } else {
                chip.setChipStrokeColor(ContextCompat.getColorStateList(chip.getContext(), R.color.colorError));
                chip.setTextColor(ContextCompat.getColor(chip.getContext(), R.color.colorError));
            }
        }
    }

    @BindingAdapter({"dividerItemDecorationVertical"})
    public static final void dividerItemDecorationVertical(RecyclerView recyclerView, boolean z) {
        if (recyclerView == null || !z) {
            return;
        }
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
    }

    @BindingAdapter({"hintTextBackgroundColor"})
    public static final void seHintTextBackgroundColor(TextView textView, Boolean bool) {
        if (textView != null) {
            if (bool != null) {
                textView.setBackgroundColor(ContextCompat.getColor(textView.getContext(), bool.booleanValue() ? R.color.colorSuccess : R.color.colorError));
            } else {
                textView.setBackgroundColor(ContextCompat.getColor(textView.getContext(), R.color.textColorPrimaryDark60));
            }
        }
    }

    @BindingAdapter({"electionPageStageIcon"})
    public static final void setElectionPageStageIcon(ImageView imageView, ElectionPageStage electionPageStage) {
        int i;
        if (imageView == null || electionPageStage == null) {
            return;
        }
        Context context = imageView.getContext();
        int status = electionPageStage.getStatus();
        if (status == -1) {
            i = R.color.colorGrey;
        } else if (status != 1) {
            if (status == 2) {
                i = R.color.colorPrimary;
            } else {
                i = R.color.colorError;
            }
        } else if (electionPageStage.isOffline()) {
            i = R.color.colorInfo60;
        } else {
            i = R.color.colorSuccess;
        }
        imageView.setColorFilter(ContextCompat.getColor(context, i));
    }

    @BindingAdapter({"electionPageStageStepper"})
    public static final void setElectionPageStageStepper(View view, ElectionPageStage electionPageStage) {
        int i;
        if (view == null || electionPageStage == null) {
            return;
        }
        Context context = view.getContext();
        if (electionPageStage.getStatus() == 1) {
            if (electionPageStage.isOffline()) {
                i = R.color.colorInfo60;
            } else {
                i = R.color.colorSuccess;
            }
        } else {
            i = R.color.colorGrey;
        }
        view.setBackgroundColor(ContextCompat.getColor(context, i));
    }

    @BindingAdapter({"polygonPoints"})
    public static final void setPolygonPoint(PolygonView polygonView, Map<Integer, ? extends Point> map) {
        if (polygonView == null || map == null) {
            return;
        }
        polygonView.setPoints(map);
    }
}
