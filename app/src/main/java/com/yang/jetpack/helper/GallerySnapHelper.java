package com.yang.jetpack.helper;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

/**
 * Created by yxm on 2021/1/19.
 */
public class GallerySnapHelper extends SnapHelper {

    private OrientationHelper mHorizontalHelper;
    private float INVALID_DISTANCE = 0;
    Context context;

    public GallerySnapHelper(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }
        return out;
    }

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return finStartView(layoutManager, getHorizontalHelper(layoutManager));
    }

    private View finStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper horizontalHelper) {
        if (layoutManager instanceof LinearLayoutManager) {
            int firstChildPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            if (firstChildPosition == RecyclerView.NO_POSITION) {
                return null;
            }
            if (((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1) {
                return null;
            }
            View firstView = layoutManager.findViewByPosition(firstChildPosition);
            View firstView1 = layoutManager.getChildAt(firstChildPosition);
            if (horizontalHelper.getDecoratedEnd(firstView) >= horizontalHelper.getDecoratedMeasurement(firstView) / 2 && horizontalHelper.getDecoratedEnd(firstView) > 0) {
                return firstView;
            } else {
                return layoutManager.findViewByPosition(firstChildPosition + 1);
            }
        }
        return null;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider))
            return RecyclerView.NO_POSITION;
        int itemCount = layoutManager.getItemCount();
        if (itemCount == 0) {
            return RecyclerView.NO_POSITION;
        }
        View snapView = findSnapView(layoutManager);
        if (snapView == null) {
            return RecyclerView.NO_POSITION;
        }
        int screenCount = layoutManager.getWidth() / getHorizontalHelper(layoutManager).getDecoratedMeasurement(snapView);
        int position = layoutManager.getPosition(snapView);
        if (position == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION;
        }
        RecyclerView.SmoothScroller.ScrollVectorProvider vectorProvider = (RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager;
        PointF vectorForEnd = vectorProvider.computeScrollVectorForPosition(itemCount - 1);
        if (vectorForEnd == null) {
            return RecyclerView.NO_POSITION;
        }
        int deltaJump;
        if (layoutManager.canScrollHorizontally()) {
            deltaJump = estimateNextPositionDiffForFling(layoutManager, getHorizontalHelper(layoutManager), velocityX, velocityY);
            if (deltaJump > screenCount) {
                deltaJump = screenCount;
            }
            if (deltaJump < -screenCount) {
                deltaJump = -screenCount;
            }
            if (vectorForEnd.x < 0) {
                deltaJump = -deltaJump;
            }
        } else {
            deltaJump = 0;
        }
        if (deltaJump == 0) {
            return RecyclerView.NO_POSITION;
        }
        int targetPos = position + deltaJump;
        if (targetPos < 0) {
            targetPos = 0;
        }
        if (targetPos >= itemCount) {
            targetPos = itemCount - 1;
        }
        return targetPos;
    }

    private int distanceToStart(View targetView, OrientationHelper helper) {
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
    }

    @NonNull
    private OrientationHelper getHorizontalHelper(
            @NonNull RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }

    private int estimateNextPositionDiffForFling(RecyclerView.LayoutManager layoutManager,
                                                 OrientationHelper helper, int velocityX, int velocityY) {
        //计算滚动的总距离，这个距离受到触发fling时的速度的影响
        int[] distances = calculateScrollDistance(velocityX, velocityY);
        //计算每个ItemView的长度
        float distancePerChild = computeDistancePerChild(layoutManager, helper);
        if (distancePerChild <= 0) {
            return 0;
        }
        //这里其实就是根据是横向布局还是纵向布局，来取对应布局方向上的滚动距离
        int distance =
                Math.abs(distances[0]) > Math.abs(distances[1]) ? distances[0] : distances[1];
        //distance的正负值符号表示滚动方向，数值表示滚动距离。横向布局方式，内容从右往左滚动为正；竖向布局方式，内容从下往上滚动为正
        // 滚动距离/item的长度=滚动item的个数，这里取计算结果的整数部分
        if (distance > 0) {
            return (int) Math.floor(distance / distancePerChild);
        } else {
            return (int) Math.ceil(distance / distancePerChild);
        }
    }

    private float computeDistancePerChild(RecyclerView.LayoutManager layoutManager,
                                          OrientationHelper helper) {
        View minPosView = null;
        View maxPosView = null;
        int minPos = Integer.MAX_VALUE;
        int maxPos = Integer.MIN_VALUE;
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return INVALID_DISTANCE;
        }

        //循环遍历layoutManager的itemView，得到最小position和最大position，以及对应的view
        for (int i = 0; i < childCount; i++) {
            View child = layoutManager.getChildAt(i);
            final int pos = layoutManager.getPosition(child);
            if (pos == RecyclerView.NO_POSITION) {
                continue;
            }
            if (pos < minPos) {
                minPos = pos;
                minPosView = child;
            }
            if (pos > maxPos) {
                maxPos = pos;
                maxPosView = child;
            }
        }
        if (minPosView == null || maxPosView == null) {
            return INVALID_DISTANCE;
        }
        //最小位置和最大位置肯定就是分布在layoutManager的两端，但是无法直接确定哪个在起点哪个在终点（因为有正反向布局）
        //所以取两者中起点坐标小的那个作为起点坐标
        //终点坐标的取值一样的道理
        int start = Math.min(helper.getDecoratedStart(minPosView),
                helper.getDecoratedStart(maxPosView));
        int end = Math.max(helper.getDecoratedEnd(minPosView),
                helper.getDecoratedEnd(maxPosView));
        //终点坐标减去起点坐标得到这些itemview的总长度
        int distance = end - start;
        if (distance == 0) {
            return INVALID_DISTANCE;
        }
        // 总长度 / itemview个数 = itemview平均长度
        return 1f * distance / ((maxPos - minPos) + 1);
    }

    //SnapHelper中该值为100，这里改为40
    private static final float MILLISECONDS_PER_INCH = 40f;

    @Nullable
    protected LinearSmoothScroller createSnapScroller(final RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }
        return new LinearSmoothScroller(context) {
            @Override
            protected void onTargetFound(View targetView, RecyclerView.State state, Action action) {
                int[] snapDistances = calculateDistanceToFinalSnap(layoutManager, targetView);
                final int dx = snapDistances[0];
                final int dy = snapDistances[1];
                final int time = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)));
                if (time > 0) {
                    action.update(dx, dy, time, mDecelerateInterpolator);
                }
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
            }
        };
    }
}
