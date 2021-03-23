package com.set.ui.surface.video;

/**
 *  播放接口
 */
public interface IVideoPlayer {
    //设置视频url
    void setUp(String url);

    //开始播放
    void start();

    //从指定位置开始播放
    void start(int pos);

    //重新播放
    void restart();

    //暂停
    void pause();

    //seek到指定位置播放
    void seekTo(int pos);

    //从上一次的位置继续播放
    void continueFromLastPosition(boolean continueFromLastPosition);

    /*********************************
     * 以下9个方法是播放器在当前的播放状态
     **********************************/
    boolean isIdle();

    boolean isPreparing();

    boolean isPrepared();

    boolean isBufferingPlaying();

    boolean isBufferingPaused();

    boolean isPlaying();

    boolean isPaused();

    boolean isError();

    boolean isCompleted();

    //获取视频总时长
    int getDuration();

    //获得视频当前播放的位置
    int getCurrentPosition();

    //获取播放百分比
    int getPlayPercentage();

    //获取缓存百分比
    int getBufferPercentage();

    //进入全屏
    void enterFullScreen();

    //退出全屏
    boolean exitFullScreen();

    //进入小窗口模式
    void enterTinyWindow();

    //退出小窗口模式
    boolean exitTinyWindow();

    //释放播放器
    void release();


    boolean isFullScreen();

    boolean isTinyWindow();

    boolean isNormal();
}


