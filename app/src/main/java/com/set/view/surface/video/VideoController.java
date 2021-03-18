package com.set.view.surface.video;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.set.view.R;

/**
 *
 */

public class VideoController extends IVideoController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private Context mContext;

    private ImageView mImage;
    private AppCompatTextView mCenterStart;
    private LinearLayout mTop;
    private AppCompatTextView mBack;
    private String mVideoUrl;
    private LinearLayout mBottom;
    private AppCompatTextView mRestartPause;
    private TextView mPosition;
    private TextView mDuration;
    private SeekBar mSeek;
    private AppCompatTextView mFullScreen;
    private LinearLayout mLoading;
    private TextView mLoadText;
    private LinearLayout mChangePositon;
    private TextView mChangePositionCurrent;
    private ProgressBar mChangePositionProgress;
    private LinearLayout mError;
    private TextView mRetry;
    private LinearLayout mNote4G;
    private TextView mContinue;
    private LinearLayout mDisconnect;
    private TextView mRefresh;
    private LinearLayout mCompleted;
    private TextView mReplay;

    private boolean topBottomVisible;
    private CountDownTimer mDismissTopBottomCountDownTimer;
    private OnPlayListener mOnPlayListener;

    public VideoController(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public VideoController(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public VideoController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.video_player_controller, this, true);
        mCenterStart = findViewById(R.id.center_start);
        mImage = findViewById(R.id.image);
        mTop = findViewById(R.id.top);
        mBack = findViewById(R.id.back);
        mBottom = findViewById(R.id.bottom);
        mRestartPause = findViewById(R.id.restart_or_pause);
        mPosition = findViewById(R.id.position);
        mDuration = findViewById(R.id.duration);
        mSeek = findViewById(R.id.seek);
        mFullScreen = findViewById(R.id.full_screen);
        mLoading = findViewById(R.id.ll_loading);
        mLoadText = findViewById(R.id.load_text);
        mChangePositon = findViewById(R.id.change_position);
        mChangePositionCurrent = findViewById(R.id.change_position_current);
        mChangePositionProgress = findViewById(R.id.change_position_progress);
        mError = findViewById(R.id.error);
        mRetry = findViewById(R.id.retry);
        mNote4G = findViewById(R.id.note_4g);
        mContinue = findViewById(R.id.continue_play);
        mDisconnect = findViewById(R.id.disconnect);
        mRefresh = findViewById(R.id.refresh);
        mCompleted = findViewById(R.id.completed);
        mReplay = findViewById(R.id.replay);

        mCenterStart.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mRestartPause.setOnClickListener(this);
        mFullScreen.setOnClickListener(this);
        mRetry.setOnClickListener(this);
        mReplay.setOnClickListener(this);
        mSeek.setOnSeekBarChangeListener(this);
        mContinue.setOnClickListener(this);
        mRefresh.setOnClickListener(this);
        this.setOnClickListener(this);
    }

    @Override
    public void setVideoPlayer(IVideoPlayer videoPlayer) {
        super.setVideoPlayer(videoPlayer);
        mVideoPlayer.setUp(mVideoUrl);
    }

    @Override
    public void setUrl(String videoUrl) {
        this.mVideoUrl = videoUrl;
    }

    @Override
    protected void onPlayStateChanged(int playState) {
        switch (playState) {
            case VideoPlayer.STATE_IDLE:
                break;
            case VideoPlayer.STATE_PREPARING:
                mNote4G.setVisibility(View.GONE);
                mDisconnect.setVisibility(View.GONE);
                mError.setVisibility(View.GONE);
                mCompleted.setVisibility(View.GONE);

                mLoading.setVisibility(View.VISIBLE);
                mImage.setVisibility(View.VISIBLE);
                mLoadText.setText("正在准备...");
                mTop.setVisibility(View.GONE);
                mBottom.setVisibility(View.GONE);
                mCenterStart.setVisibility(View.GONE);
                setIsPlayingFlag();
                break;
            case VideoPlayer.STATE_PREPARED:
                startUpdateProgressTimer();
                setIsPlayingFlag();
                break;
            case VideoPlayer.STATE_PLAYING:
                mLoading.setVisibility(GONE);
                mError.setVisibility(GONE);
                mNote4G.setVisibility(View.GONE);
                mDisconnect.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);

                mImage.setVisibility(View.GONE);
                mRestartPause.setText("\ue546");//暂停
                mCenterStart.setText("\ue546");//暂停
                mCenterStart.setVisibility(VISIBLE);
                setTopBottomVisible(true);
                startDismissTopCenterBottomTimer();
                setIsPlayingFlag();
                break;
            case VideoPlayer.STATE_PAUSED:
                mError.setVisibility(GONE);
                mCompleted.setVisibility(GONE);
                mNote4G.setVisibility(GONE);
                mDisconnect.setVisibility(GONE);
                mLoading.setVisibility(View.GONE);

                mRestartPause.setText("\ue545");//播放
                mCenterStart.setText("\ue545");//播放
                setTopBottomVisible(false);
                cancelDismissTopCenterBottomTimer();
                mCenterStart.setVisibility(VISIBLE);
                setIsPauseFlag();
                if (mVideoPlayer.isFullScreen()) {
                    mTop.setVisibility(VISIBLE);
                }
                break;
            case VideoPlayer.STATE_BUFFERING_PLAYING:
                mError.setVisibility(GONE);
                mCompleted.setVisibility(GONE);
                mNote4G.setVisibility(GONE);
                mDisconnect.setVisibility(GONE);

                mRestartPause.setText("\ue546");//暂停
                mCenterStart.setText("\ue546");//暂停
                mCenterStart.setVisibility(VISIBLE);
                mLoading.setVisibility(View.VISIBLE);
                mLoadText.setText("正在缓冲...");
                setTopBottomVisible(true);
                startDismissTopCenterBottomTimer();
                setIsPlayingFlag();
                break;
            case VideoPlayer.STATE_BUFFERING_PAUSED:
                mError.setVisibility(GONE);
                mCompleted.setVisibility(GONE);
                mDisconnect.setVisibility(GONE);
                mNote4G.setVisibility(GONE);

                mLoading.setVisibility(View.VISIBLE);
                mLoadText.setText("正在缓冲...");
                mRestartPause.setText("\ue545");//播放
                mCenterStart.setText("\ue545");//播放
                mCenterStart.setVisibility(VISIBLE);
                cancelDismissTopCenterBottomTimer();
                setTopBottomVisible(false);
                setIsPauseFlag();
                if (mVideoPlayer.isFullScreen()) {
                    mTop.setVisibility(VISIBLE);
                }
                break;
            case VideoPlayer.STATE_ERROR:
                mCompleted.setVisibility(GONE);
                mDisconnect.setVisibility(GONE);
                mLoading.setVisibility(GONE);
                mNote4G.setVisibility(GONE);

                cancelUpdateProgressTimer();
                setTopBottomVisible(false);
                mCenterStart.setVisibility(GONE);
                mTop.setVisibility(View.VISIBLE);
                mError.setVisibility(View.VISIBLE);
                if (mVideoPlayer.isFullScreen()) {
                    mTop.setVisibility(VISIBLE);
                    mBack.setVisibility(View.VISIBLE);
                }
                break;
            case VideoPlayer.STATE_COMPLETED:
                mError.setVisibility(GONE);
                mCompleted.setVisibility(GONE);
                mDisconnect.setVisibility(GONE);
                mLoading.setVisibility(GONE);
                mNote4G.setVisibility(GONE);

                cancelUpdateProgressTimer();
                cancelDismissTopCenterBottomTimer();
                setTopBottomVisible(false);
                mCenterStart.setVisibility(GONE);
                mImage.setVisibility(View.VISIBLE);
                mCompleted.setVisibility(View.VISIBLE);
                if (mVideoPlayer.isFullScreen()) {
                    mTop.setVisibility(VISIBLE);
                    mBack.setVisibility(View.VISIBLE);
                }
                break;
            case VideoPlayer.STATE_NOTE_4G:
                mError.setVisibility(GONE);
                mCompleted.setVisibility(GONE);
                mDisconnect.setVisibility(GONE);
                mLoading.setVisibility(View.GONE);

                mNote4G.setVisibility(VISIBLE);
                mImage.setVisibility(View.VISIBLE);
                setTopBottomVisible(false);
                mCenterStart.setVisibility(GONE);
                if (mVideoPlayer.isFullScreen()) {
                    mTop.setVisibility(VISIBLE);
                    mBack.setVisibility(View.VISIBLE);
                }
                break;
            case VideoPlayer.STATE_NOTE_DISCONNECT:
                mError.setVisibility(GONE);
                mCompleted.setVisibility(GONE);
                mNote4G.setVisibility(GONE);
                mLoading.setVisibility(GONE);

                mDisconnect.setVisibility(VISIBLE);
                mImage.setVisibility(VISIBLE);
                setTopBottomVisible(false);
                mCenterStart.setVisibility(GONE);
                if (mVideoPlayer.isFullScreen()) {
                    mTop.setVisibility(VISIBLE);
                    mBack.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    protected void onPlayModeChanged(int playMode) {
        switch (playMode) {
            case VideoPlayer.MODE_NORMAL:
                mBack.setVisibility(View.GONE);
                mFullScreen.setText("\ue579");//全屏
                mFullScreen.setVisibility(View.VISIBLE);
                break;
            case VideoPlayer.MODE_FULL_SCREEN:
                mBack.setVisibility(View.VISIBLE);
                mFullScreen.setText("\ue57a");//缩小
                break;
            case VideoPlayer.MODE_TINY_WINDOW:
                mBack.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void reset() {
        topBottomVisible = false;
        cancelUpdateProgressTimer();
        cancelDismissTopCenterBottomTimer();
        mSeek.setProgress(0);
        mSeek.setSecondaryProgress(0);

        mCenterStart.setVisibility(View.VISIBLE);
        mImage.setVisibility(View.VISIBLE);

        mBottom.setVisibility(View.GONE);
        mFullScreen.setText("\ue579");//全屏

        mTop.setVisibility(View.VISIBLE);
        mBack.setVisibility(View.GONE);

        mLoading.setVisibility(View.GONE);
        mError.setVisibility(View.GONE);
        mCompleted.setVisibility(View.GONE);
    }

    public void autoStart() {
        mCenterStart.performClick();
    }

    @Override
    public void onClick(View v) {
        if (v == mCenterStart) {
            mRestartPause.performClick();
        } else if (v == mBack) {
            if (mVideoPlayer.isFullScreen()) {
                mVideoPlayer.exitFullScreen();
            } else if (mVideoPlayer.isTinyWindow()) {
                mVideoPlayer.exitTinyWindow();
            }
        } else if (v == mRestartPause) {
            if (NetworkChangeReceiver.isDisconnect()) {
                onPlayStateChanged(VideoPlayer.STATE_NOTE_DISCONNECT);
            } else if (NetworkChangeReceiver.is4G() && !VideoPlayer.allow4GFlag) {
                onPlayStateChanged(VideoPlayer.STATE_NOTE_4G);
            } else if (mVideoPlayer.isIdle()) {
                mVideoPlayer.start();
            } else if (mVideoPlayer.isPlaying() || mVideoPlayer.isBufferingPlaying()) {
                mVideoPlayer.pause();
            } else if (mVideoPlayer.isPaused() || mVideoPlayer.isBufferingPaused()) {
                mVideoPlayer.restart();
            }
        } else if (v == mFullScreen) {
            if (mVideoPlayer.isNormal() || mVideoPlayer.isTinyWindow()) {
                mVideoPlayer.enterFullScreen();
            } else if (mVideoPlayer.isFullScreen()) {
                mVideoPlayer.exitFullScreen();
            }
        } else if (v == mRetry) {
            mVideoPlayer.restart();
        } else if (v == mReplay) {
            mRetry.performClick();
        } else if (v == this) {
            if (mVideoPlayer.isPlaying()
                    || mVideoPlayer.isBufferingPlaying()) {
                setTopBottomVisible(!topBottomVisible);
                if (topBottomVisible) {
                    startDismissTopCenterBottomTimer();
                }
                mCenterStart.setVisibility(topBottomVisible ? VISIBLE : GONE);
            }
        } else if (v == mContinue) {
            VideoPlayer.allow4GFlag = true;
            mVideoPlayer.restart();
        } else if (v == mRefresh) {
            mVideoPlayer.restart();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mVideoPlayer.isBufferingPaused() || mVideoPlayer.isPaused()) {
            mVideoPlayer.restart();
        }
        int position = (int) (mVideoPlayer.getDuration() * seekBar.getProgress() / 100f);
        mVideoPlayer.seekTo(position);
        startDismissTopCenterBottomTimer();
    }

    private void setTopBottomVisible(boolean visible) {
        mTop.setVisibility(visible ? VISIBLE : GONE);
        mBottom.setVisibility(visible ? VISIBLE : GONE);
        topBottomVisible = visible;
        if (visible) {
            if (!mVideoPlayer.isPaused() && !mVideoPlayer.isBufferingPaused()) {
                startUpdateProgressTimer();
            }
        }
    }

    private void startDismissTopCenterBottomTimer() {
        cancelDismissTopCenterBottomTimer();
        if (mDismissTopBottomCountDownTimer == null) {
            mDismissTopBottomCountDownTimer = new CountDownTimer(3000, 3000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    setTopBottomVisible(false);
                    mCenterStart.setVisibility(GONE);
                }
            };
        }
        mDismissTopBottomCountDownTimer.start();
    }

    private void cancelDismissTopCenterBottomTimer() {
        if (mDismissTopBottomCountDownTimer != null) {
            mDismissTopBottomCountDownTimer.cancel();
        }
    }

    @Override
    protected void updateProgress() {
        long position = mVideoPlayer.getCurrentPosition();
        long duration = mVideoPlayer.getDuration();
        int bufferPercentage = mVideoPlayer.getBufferPercentage();
        mSeek.setSecondaryProgress(bufferPercentage);
        int progress = (int) (100f * position / duration);
        mSeek.setProgress(progress);
        mPosition.setText(VideoUtil.formatTime(position));
        mDuration.setText(VideoUtil.formatTime(duration));
    }

    @Override
    protected void showChangePosition(long duration, int newPositionProgress) {
        mChangePositon.setVisibility(View.VISIBLE);
        long newPosition = (long) (duration * newPositionProgress / 100f);
        mChangePositionCurrent.setText(VideoUtil.formatTime(newPosition));
        mChangePositionProgress.setProgress(newPositionProgress);
        mSeek.setProgress(newPositionProgress);
        mPosition.setText(VideoUtil.formatTime(newPosition));
    }

    @Override
    protected void hideChangePosition() {
        mChangePositon.setVisibility(View.GONE);
    }

    public interface OnPlayListener {
        void play();

        void pause();
    }

    private void setIsPauseFlag() {
        if (mOnPlayListener != null) {
            mOnPlayListener.pause();
        }
    }

    private void setIsPlayingFlag() {
        if (mOnPlayListener != null) {
            mOnPlayListener.play();
        }
    }

    public void setOnPlayListener(OnPlayListener onPlayListener) {
        this.mOnPlayListener = onPlayListener;
    }

}
