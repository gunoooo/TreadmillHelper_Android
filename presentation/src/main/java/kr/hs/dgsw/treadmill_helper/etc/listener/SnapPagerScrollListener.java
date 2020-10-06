package kr.hs.dgsw.treadmill_helper.etc.listener;

import android.view.View;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * https://stackoverflow.com/questions/52083768/from-to-page-changed-listener-in-pagersnaphelper
 */
public class SnapPagerScrollListener extends RecyclerView.OnScrollListener {
    public interface OnChangeListener {
        void onSnapped(int position);
    }

    // Properties
    private final PagerSnapHelper snapHelper;
    private final OnChangeListener listener;

    private int snapPosition = 0;
    private boolean isUserScrolling = false;

    public SnapPagerScrollListener(PagerSnapHelper snapHelper, OnChangeListener listener) {
        this.snapHelper = snapHelper;
        this.listener = listener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            isUserScrolling = true;
        } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            int position = getSnapPosition(recyclerView);
            if (isUserScrolling) {
                if (snapPosition != position) {
                    listener.onSnapped(position);
                }
                isUserScrolling = false;
            }
            snapPosition = position;
        }
    }

    private int getSnapPosition(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            return RecyclerView.NO_POSITION;
        }

        View snapView = snapHelper.findSnapView(layoutManager);
        if (snapView == null) {
            return RecyclerView.NO_POSITION;
        }

        return layoutManager.getPosition(snapView);
    }
}