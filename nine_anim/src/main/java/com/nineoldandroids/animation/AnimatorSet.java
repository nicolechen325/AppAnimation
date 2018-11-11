/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nineoldandroids.animation;

import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * This class plays a set of {@link Animator} objects in the specified order. Animations
 * can be set up to play together, in sequence, or after a specified delay.
 *
 * <p>There are two different approaches to adding animations to a <code>AnimatorSet</code>:
 * either the {@link AnimatorSet#playTogether(Animator[]) playTogether()} or
 * {@link AnimatorSet#playSequentially(Animator[]) playSequentially()} methods can be called to add
 * a set of animations all at once, or the {@link AnimatorSet#play(Animator)} can be
 * used in conjunction with methods in the {@link Builder Builder}
 * class to add animations
 * one by one.</p>
 *
 * <p>It is possible to set up a <code>AnimatorSet</code> with circular dependencies between
 * its animations. For example, an animation a1 could be set up to start before animation a2, a2
 * before a3, and a3 before a1. The results of this configuration are undefined, but will typically
 * result in none of the affected animations being played. Because of this (and because
 * circular dependencies do not make logical sense anyway), circular dependencies
 * should be avoided, and the dependency flow of animations should only be in one direction.
 */
public final class AnimatorSet extends Animator {

    /**
     * Internal variables
     * NOTE: This object implements the clone() method, making a deep copy of any referenced
     * objects. As other non-trivial fields are added to this class, make sure to add logic
     * to clone() to make deep copies of them.
     */

    /**
     * Tracks animations currently being played, so that we know what to
     * cancel or end when cancel() or end() is called on this AnimatorSet
     */
    private ArrayList<Animator> mPlayingSet = new ArrayList<Animator>();

    /**
     * Contains all nodes, mapped to their respective Animators. When new
     * dependency information is added for an Animator, we want to add it
     * to a single node representing that Animator, not create a new Node
     * if one already exists.
     */
    private HashMap<Animator, Node> mNodeMap = new HashMap<Animator, Node>();//Node与Animator的对应Map

    /**
     * Set of all nodes created for this AnimatorSet. This list is used upon
     * starting the set, and the nodes are placed in sorted order into the
     * sortedNodes collection.
     */
    private ArrayList<Node> mNodes = new ArrayList<Node>();//存放封装为Node类型的动画列表

    /**
     * The sorted list of nodes. This is the order in which the animations will
     * be played. The details about when exactly they will be played depend
     * on the dependency relationships of the nodes.
     */
    private ArrayList<Node> mSortedNodes = new ArrayList<Node>();

    /**
     * Flag indicating whether the nodes should be sorted prior to playing. This
     * flag allows us to cache the previous sorted nodes so that if the sequence
     * is replayed with no changes, it does not have to re-sort the nodes again.
     */
    private boolean mNeedsSort = true;

    private AnimatorSetListener mSetListener = null;

    /**
     * Flag indicating that the AnimatorSet has been manually
     * terminated (by calling cancel() or end()).
     * This flag is used to avoid starting other animations when currently-playing
     * child animations of this AnimatorSet end. It also determines whether cancel/end
     * notifications are sent out via the normal AnimatorSetListener mechanism.
     */
    boolean mTerminated = false;

    /**
     * Indicates whether an AnimatorSet has been start()'d, whether or
     * not there is a nonzero startDelay.
     */
    private boolean mStarted = false;

    // The amount of time in ms to delay starting the animation after start() is called
    private long mStartDelay = 0;

    // Animator used for a nonzero startDelay
    private ValueAnimator mDelayAnim = null;


    // How long the child animations should last in ms. The default value is negative, which
    // simply means that there is no duration set on the AnimatorSet. When a real duration is
    // set, it is passed along to the child animations.
    private long mDuration = -1;


    /**
     * Sets up this AnimatorSet to play all of the supplied animations at the same time.
     * 多个动画一起的运动方式  同时
     * @param items The animations that will be started simultaneously.
     */
    public void playTogether(Animator... items) {
        if (items != null) {
            mNeedsSort = true;
            Builder builder = play(items[0]);//创建Builder 
            for (int i = 1; i < items.length; ++i) {
                builder.with(items[i]);//为每个动画创建Node,存到mNodes列表中,Node中包含他们之间的依赖关系
            }
        }
    }

    /**
     * Sets up this AnimatorSet to play all of the supplied animations at the same time.
     *
     * @param items The animations that will be started simultaneously.
     */
    public void playTogether(Collection<Animator> items) {
        if (items != null && items.size() > 0) {
            mNeedsSort = true;
            Builder builder = null;
            for (Animator anim : items) {
                if (builder == null) {
                    builder = play(anim);//先创建Builder
                } else {
                    builder.with(anim);
                }
            }
        }
    }

    /**
     * Sets up this AnimatorSet to play each of the supplied animations when the
     * previous animation ends.
     * 动画一个一个按顺序来执行
     * @param items The animations that will be started one after another.
     */
    public void playSequentially(Animator... items) {
        if (items != null) {
            mNeedsSort = true;
            if (items.length == 1) {
                play(items[0]);//只有一个动画
            } else {
                for (int i = 0; i < items.length - 1; ++i) {
                    play(items[i]).before(items[i+1]);
                }
            }
        }
    }

    /**
     * Sets up this AnimatorSet to play each of the supplied animations when the
     * previous animation ends.
     *
     * @param items The animations that will be started one after another.
     */
    public void playSequentially(List<Animator> items) {
        if (items != null && items.size() > 0) {
            mNeedsSort = true;
            if (items.size() == 1) {
                play(items.get(0));//只有一个动画
            } else {
                for (int i = 0; i < items.size() - 1; ++i) {
                    play(items.get(i)).before(items.get(i+1));
                }
            }
        }
    }

    /**
     * Returns the current list of child Animator objects controlled by this
     * AnimatorSet. This is a copy of the internal list; modifications to the returned list
     * will not affect the AnimatorSet, although changes to the underlying Animator objects
     * will affect those objects being managed by the AnimatorSet.
     * 
     * 将列表中的每个Node对应的动画抽出来  返回动画列表
     * 
     * @return ArrayList<Animator> The list of child animations of this AnimatorSet.
     */
    public ArrayList<Animator> getChildAnimations() {
        ArrayList<Animator> childList = new ArrayList<Animator>();
        for (Node node : mNodes) {
            childList.add(node.animation);
        }
        return childList;
    }

    /**
     * Sets the target object for all current {@link #getChildAnimations() child animations}
     * of this AnimatorSet that take targets ({@link ObjectAnimator} and
     * AnimatorSet).
     *
     * @param target The object being animated
     */
    @Override
    public void setTarget(Object target) {
        for (Node node : mNodes) {
            Animator animation = node.animation;
            if (animation instanceof AnimatorSet) {
                ((AnimatorSet)animation).setTarget(target);
            } else if (animation instanceof ObjectAnimator) {
                ((ObjectAnimator)animation).setTarget(target);
            }
        }
    }

    /**
     * Sets the TimeInterpolator for all current {@link #getChildAnimations() child animations}
     * of this AnimatorSet.
     *
     * @param interpolator the interpolator to be used by each child animation of this AnimatorSet
     */
    @Override
    public void setInterpolator(/*Time*/Interpolator interpolator) {
        for (Node node : mNodes) {
            node.animation.setInterpolator(interpolator);
        }
    }

    /**
     * This method creates a <code>Builder</code> object, which is used to
     * set up playing constraints. This initial <code>play()</code> method
     * tells the <code>Builder</code> the animation that is the dependency for
     * the succeeding commands to the <code>Builder</code>. For example,
     * calling <code>play(a1).with(a2)</code> sets up the AnimatorSet to play
     * <code>a1</code> and <code>a2</code> at the same time,
     * <code>play(a1).before(a2)</code> sets up the AnimatorSet to play
     * <code>a1</code> first, followed by <code>a2</code>, and
     * <code>play(a1).after(a2)</code> sets up the AnimatorSet to play
     * <code>a2</code> first, followed by <code>a1</code>.
     *
     * <p>Note that <code>play()</code> is the only way to tell the
     * <code>Builder</code> the animation upon which the dependency is created,
     * so successive calls to the various functions in <code>Builder</code>
     * will all refer to the initial parameter supplied in <code>play()</code>
     * as the dependency of the other animations. For example, calling
     * <code>play(a1).before(a2).before(a3)</code> will play both <code>a2</code>
     * and <code>a3</code> when a1 ends; it does not set up a dependency between
     * <code>a2</code> and <code>a3</code>.</p>
     *
     * @param anim The animation that is the dependency used in later calls to the
     * methods in the returned <code>Builder</code> object. A null parameter will result
     * in a null <code>Builder</code> return value.
     * @return Builder The object that constructs the AnimatorSet based on the dependencies
     * outlined in the calls to <code>play</code> and the other methods in the
     * <code>Builder</code object.
     * 
     * 创建Builder
     */
    public Builder play(Animator anim) {
        if (anim != null) {
            mNeedsSort = true;
            return new Builder(anim);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Note that canceling a <code>AnimatorSet</code> also cancels all of the animations that it
     * is responsible for.</p>
     */
    @SuppressWarnings("unchecked")
    @Override
    public void cancel() {
        mTerminated = true;
        if (isStarted()) {
            ArrayList<AnimatorListener> tmpListeners = null;
            if (mListeners != null) {
                tmpListeners = (ArrayList<AnimatorListener>) mListeners.clone();
                for (AnimatorListener listener : tmpListeners) {
                    listener.onAnimationCancel(this);
                }
            }
            if (mDelayAnim != null && mDelayAnim.isRunning()) {
                // If we're currently in the startDelay period, just cancel that animator and
                // send out the end event to all listeners
                mDelayAnim.cancel();
            } else  if (mSortedNodes.size() > 0) {
                for (Node node : mSortedNodes) {
                    node.animation.cancel();
                }
            }
            if (tmpListeners != null) {
                for (AnimatorListener listener : tmpListeners) {
                    listener.onAnimationEnd(this);
                }
            }
            mStarted = false;
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Note that ending a <code>AnimatorSet</code> also ends all of the animations that it is
     * responsible for.</p>
     */
    @Override
    public void end() {
        mTerminated = true;
        if (isStarted()) {
            if (mSortedNodes.size() != mNodes.size()) {
                // hasn't been started yet - sort the nodes now, then end them
                sortNodes();
                for (Node node : mSortedNodes) {
                    if (mSetListener == null) {
                        mSetListener = new AnimatorSetListener(this);
                    }
                    node.animation.addListener(mSetListener);
                }
            }
            if (mDelayAnim != null) {
                mDelayAnim.cancel();
            }
            if (mSortedNodes.size() > 0) {
                for (Node node : mSortedNodes) {
                    node.animation.end();
                }
            }
            if (mListeners != null) {
                ArrayList<AnimatorListener> tmpListeners =
                        (ArrayList<AnimatorListener>) mListeners.clone();
                for (AnimatorListener listener : tmpListeners) {
                    listener.onAnimationEnd(this);
                }
            }
            mStarted = false;
        }
    }

    /**
     * Returns true if any of the child animations of this AnimatorSet have been started and have
     * not yet ended.
     * @return Whether this AnimatorSet has been started and has not yet ended.
     */
    @Override
    public boolean isRunning() {
        for (Node node : mNodes) {
            if (node.animation.isRunning()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isStarted() {
        return mStarted;
    }

    /**
     * The amount of time, in milliseconds, to delay starting the animation after
     * {@link #start()} is called.
     *
     * @return the number of milliseconds to delay running the animation
     */
    @Override
    public long getStartDelay() {
        return mStartDelay;
    }

    /**
     * The amount of time, in milliseconds, to delay starting the animation after
     * {@link #start()} is called.

     * @param startDelay The amount of the delay, in milliseconds
     */
    @Override
    public void setStartDelay(long startDelay) {
        mStartDelay = startDelay;
    }

    /**
     * Gets the length of each of the child animations of this AnimatorSet. This value may
     * be less than 0, which indicates that no duration has been set on this AnimatorSet
     * and each of the child animations will use their own duration.
     *
     * @return The length of the animation, in milliseconds, of each of the child
     * animations of this AnimatorSet.
     */
    @Override
    public long getDuration() {
        return mDuration;
    }

    /**
     * Sets the length of each of the current child animations of this AnimatorSet. By default,
     * each child animation will use its own duration. If the duration is set on the AnimatorSet,
     * then each child animation inherits this duration.
     *
     * @param duration The length of the animation, in milliseconds, of each of the child
     * animations of this AnimatorSet.
     */
    @Override
    public AnimatorSet setDuration(long duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("duration must be a value of zero or greater");
        }
        for (Node node : mNodes) {
            // TODO: don't set the duration of the timing-only nodes created by AnimatorSet to
            // insert "play-after" delays
            node.animation.setDuration(duration);
        }
        mDuration = duration;
        return this;
    }

    @Override
    public void setupStartValues() {
        for (Node node : mNodes) {
            node.animation.setupStartValues();
        }
    }

    @Override
    public void setupEndValues() {
        for (Node node : mNodes) {
            node.animation.setupEndValues();
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Starting this <code>AnimatorSet</code> will, in turn, start the animations for which
     * it is responsible. The details of when exactly those animations are started depends on
     * the dependency relationships that have been set up between the animations.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void start() {
        mTerminated = false;
        mStarted = true;

        // First, sort the nodes (if necessary). This will ensure that sortedNodes
        // contains the animation nodes in the correct order.
        sortNodes();//根据Node依赖关系 对Node排序

        int numSortedNodes = mSortedNodes.size();//操作每个Animator的Listener
        for (int i = 0; i < numSortedNodes; ++i) {//清除所有的旧监听事件
            Node node = mSortedNodes.get(i);
            // First, clear out the old listeners
            ArrayList<AnimatorListener> oldListeners = node.animation.getListeners();
            if (oldListeners != null && oldListeners.size() > 0) {
                final ArrayList<AnimatorListener> clonedListeners = new
                        ArrayList<AnimatorListener>(oldListeners);

                for (AnimatorListener listener : clonedListeners) {
                    if (listener instanceof DependencyListener ||
                            listener instanceof AnimatorSetListener) {//Node中Animator的Listener是DependencyListener或AnimatorSetListener类型  删掉
                        node.animation.removeListener(listener);
                    }
                }
            }
        }

        // nodesToStart holds the list of nodes to be started immediately. We don't want to
        // start the animations in the loop directly because we first need to set up
        // dependencies on all of the nodes. For example, we don't want to start an animation
        // when some other animation also wants to start when the first animation begins.
        final ArrayList<Node> nodesToStart = new ArrayList<Node>();
        for (int i = 0; i < numSortedNodes; ++i) {
            Node node = mSortedNodes.get(i);
            if (mSetListener == null) { //AnimatorSet动画监听事件
                mSetListener = new AnimatorSetListener(this);
            }
            if (node.dependencies == null || node.dependencies.size() == 0) {// 没有依赖的节点
                nodesToStart.add(node);
            } else {
                int numDependencies = node.dependencies.size();
                for (int j = 0; j < numDependencies; ++j) {//对有依赖的节点 遍历所有依赖 通过依赖找到其依赖的节点dependnode 给dependnode增加依赖监听事件(dependnode结束后运行该节点或一起运行)
                    Dependency dependency = node.dependencies.get(j);//
                    dependency.node.animation.addListener(
                            new DependencyListener(this, node, dependency.rule));//只有被依赖的动画有DependencyListener
                }
                node.tmpDependencies = (ArrayList<Dependency>) node.dependencies.clone();//同时给有依赖的节点备份其依赖列表 用于删除依赖
            }
            node.animation.addListener(mSetListener);//为动画增加新的Listener(Set中所有动画都有)  AnimatorSetListener
        }
        // Now that all dependencies are set up, start the animations that should be started.
        if (mStartDelay <= 0) {//无延迟  立即执行nodesToStart中动画
            for (Node node : nodesToStart) {
                node.animation.start();
                mPlayingSet.add(node.animation);
            }
        } else {//有延迟  增加一个延迟动画
            mDelayAnim = ValueAnimator.ofFloat(0f, 1f);
            mDelayAnim.setDuration(mStartDelay);
            mDelayAnim.addListener(new AnimatorListenerAdapter() {//动画监听器适配 重写结束和取消方法
                boolean canceled = false;
                public void onAnimationCancel(Animator anim) {
                    canceled = true;
                }
                public void onAnimationEnd(Animator anim) {//延迟动画结束  开始AnimatoionSet动画
                    if (!canceled) {
                        int numNodes = nodesToStart.size();
                        for (int i = 0; i < numNodes; ++i) {
                            Node node = nodesToStart.get(i);
                            node.animation.start();
                            mPlayingSet.add(node.animation);
                        }
                    }
                }
            });
            mDelayAnim.start();
        }
        if (mListeners != null) {//Set动画开始监听 onAnimationStart参数是AnimatorSet类型  相当于对Set整体开始的监听
            ArrayList<AnimatorListener> tmpListeners =
                    (ArrayList<AnimatorListener>) mListeners.clone();
            int numListeners = tmpListeners.size();
            for (int i = 0; i < numListeners; ++i) {
                tmpListeners.get(i).onAnimationStart(this);
            }
        }
        if (mNodes.size() == 0 && mStartDelay == 0) {//动画结束监听
            // Handle unusual case where empty AnimatorSet is started - should send out
            // end event immediately since the event will not be sent out at all otherwise
            mStarted = false;
            if (mListeners != null) {
                ArrayList<AnimatorListener> tmpListeners =
                        (ArrayList<AnimatorListener>) mListeners.clone();
                int numListeners = tmpListeners.size();
                for (int i = 0; i < numListeners; ++i) {
                    tmpListeners.get(i).onAnimationEnd(this);
                }
            }
        }
    }

    @Override
    public AnimatorSet clone() {
        final AnimatorSet anim = (AnimatorSet) super.clone();
        /*
         * The basic clone() operation copies all items. This doesn't work very well for
         * AnimatorSet, because it will copy references that need to be recreated and state
         * that may not apply. What we need to do now is put the clone in an uninitialized
         * state, with fresh, empty data structures. Then we will build up the nodes list
         * manually, as we clone each Node (and its animation). The clone will then be sorted,
         * and will populate any appropriate lists, when it is started.
         */
        anim.mNeedsSort = true;
        anim.mTerminated = false;
        anim.mStarted = false;
        anim.mPlayingSet = new ArrayList<Animator>();
        anim.mNodeMap = new HashMap<Animator, Node>();
        anim.mNodes = new ArrayList<Node>();
        anim.mSortedNodes = new ArrayList<Node>();

        // Walk through the old nodes list, cloning each node and adding it to the new nodemap.
        // One problem is that the old node dependencies point to nodes in the old AnimatorSet.
        // We need to track the old/new nodes in order to reconstruct the dependencies in the clone.
        HashMap<Node, Node> nodeCloneMap = new HashMap<Node, Node>(); // <old, new>
        for (Node node : mNodes) {
            Node nodeClone = node.clone();
            nodeCloneMap.put(node, nodeClone);
            anim.mNodes.add(nodeClone);
            anim.mNodeMap.put(nodeClone.animation, nodeClone);
            // Clear out the dependencies in the clone; we'll set these up manually later
            nodeClone.dependencies = null;
            nodeClone.tmpDependencies = null;
            nodeClone.nodeDependents = null;
            nodeClone.nodeDependencies = null;
            // clear out any listeners that were set up by the AnimatorSet; these will
            // be set up when the clone's nodes are sorted
            ArrayList<AnimatorListener> cloneListeners = nodeClone.animation.getListeners();
            if (cloneListeners != null) {
                ArrayList<AnimatorListener> listenersToRemove = null;
                for (AnimatorListener listener : cloneListeners) {
                    if (listener instanceof AnimatorSetListener) {
                        if (listenersToRemove == null) {
                            listenersToRemove = new ArrayList<AnimatorListener>();
                        }
                        listenersToRemove.add(listener);
                    }
                }
                if (listenersToRemove != null) {
                    for (AnimatorListener listener : listenersToRemove) {
                        cloneListeners.remove(listener);
                    }
                }
            }
        }
        // Now that we've cloned all of the nodes, we're ready to walk through their
        // dependencies, mapping the old dependencies to the new nodes
        for (Node node : mNodes) {
            Node nodeClone = nodeCloneMap.get(node);
            if (node.dependencies != null) {
                for (Dependency dependency : node.dependencies) {
                    Node clonedDependencyNode = nodeCloneMap.get(dependency.node);
                    Dependency cloneDependency = new Dependency(clonedDependencyNode,
                            dependency.rule);
                    nodeClone.addDependency(cloneDependency);
                }
            }
        }

        return anim;
    }

    /**
     * This class is the mechanism by which animations are started based on events in other
     * animations. If an animation has multiple dependencies on other animations, then
     * all dependencies must be satisfied before the animation is started.
     * 
     * 创建的DependencyListener用于被依赖的节点上  当被依赖的节点开始或结束时候 根据依赖规则 运行依赖其的动画
     */
    private static class DependencyListener implements AnimatorListener {

        private AnimatorSet mAnimatorSet;

        // The node upon which the dependency is based.
        private Node mNode;//依赖其他节点的节点,也就是判断何时运动的动画节点 是一起运动 还是它依赖的结束后再运动

        // The Dependency rule (WITH or AFTER) that the listener should wait for on
        // the node
        private int mRule;

		public DependencyListener(AnimatorSet animatorSet, Node node, int rule) {
            this.mAnimatorSet = animatorSet;
            this.mNode = node;
            this.mRule = rule;
        }

        /**
         * Ignore cancel events for now. We may want to handle this eventually,
         * to prevent follow-on animations from running when some dependency
         * animation is canceled.
         */
        public void onAnimationCancel(Animator animation) {
        }

        /**
         * An end event is received - see if this is an event we are listening for
         */
        public void onAnimationEnd(Animator animation) {
            if (mRule == Dependency.AFTER) { //依赖节点有了该监听事件 在动画结束后  运行
                startIfReady(animation);
            }
        }

        /**
         * Ignore repeat events for now
         */
        public void onAnimationRepeat(Animator animation) {
        }

        /**
         * A start event is received - see if this is an event we are listening for
         */
        public void onAnimationStart(Animator animation) {
            if (mRule == Dependency.WITH) {
                startIfReady(animation);
            }
        }

        /**
         * Check whether the event received is one that the node was waiting for.
         * If so, mark it as complete and see whether it's time to start
         * the animation.
         * @param dependencyAnimation the animation that sent the event.
         * 
         * 进这个函数  必定是某个依赖Listener开始或结束  引导mNode一起运动或者在之后运动
         * mNode可能不止一个依赖哦,遍历所有依赖 找到是哪个依赖发出的依赖Listener
         * 找到后将它的依赖Listener移除  并在mNode的备份依赖里移除依赖  移除后 如果没有依赖了 就可以运行了
         */
        private void startIfReady(Animator dependencyAnimation) {
            if (mAnimatorSet.mTerminated) {
                // if the parent AnimatorSet was canceled, then don't start any dependent anims
                return;
            }
            Dependency dependencyToRemove = null;
            int numDependencies = mNode.tmpDependencies.size();//mNode即将要运行的动画 
            for (int i = 0; i < numDependencies; ++i) {
                Dependency dependency = mNode.tmpDependencies.get(i);
                if (dependency.rule == mRule &&
                        dependency.node.animation == dependencyAnimation) {//依赖的动画是拥有DependencyListener的正在运行的动画
                    // rule fired - remove the dependency and listener and check to
                    // see whether it's time to start the animation
                    dependencyToRemove = dependency;
                    dependencyAnimation.removeListener(this);//依赖移除掉DependencyListener
                    break;
                }
            }
            mNode.tmpDependencies.remove(dependencyToRemove);//该节点 即将运行前  移除掉依赖(tmpDependencies就是这个作用  在备份里移除依赖)
            if (mNode.tmpDependencies.size() == 0) {//没有依赖啦 可以运行啦
                // all dependencies satisfied: start the animation
                mNode.animation.start();
                mAnimatorSet.mPlayingSet.add(mNode.animation);
            }
        }

    }

    private class AnimatorSetListener implements AnimatorListener {

        private AnimatorSet mAnimatorSet;

        AnimatorSetListener(AnimatorSet animatorSet) {
            mAnimatorSet = animatorSet;
        }

        public void onAnimationCancel(Animator animation) {
            if (!mTerminated) {
                // Listeners are already notified of the AnimatorSet canceling in cancel().
                // The logic below only kicks in when animations end normally
                if (mPlayingSet.size() == 0) {
                    if (mListeners != null) {
                        int numListeners = mListeners.size();
                        for (int i = 0; i < numListeners; ++i) {
                            mListeners.get(i).onAnimationCancel(mAnimatorSet);
                        }
                    }
                }
            }
        }

        @SuppressWarnings("unchecked")
        public void onAnimationEnd(Animator animation) {//动画结束后 将对应的节点删除
            animation.removeListener(this);
            mPlayingSet.remove(animation);
            Node animNode = mAnimatorSet.mNodeMap.get(animation);
            animNode.done = true;
            if (!mTerminated) {
                // Listeners are already notified of the AnimatorSet ending in cancel() or
                // end(); the logic below only kicks in when animations end normally
                ArrayList<Node> sortedNodes = mAnimatorSet.mSortedNodes;//判断是否所有动画都结束
                boolean allDone = true;
                int numSortedNodes = sortedNodes.size();
                for (int i = 0; i < numSortedNodes; ++i) {
                    if (!sortedNodes.get(i).done) {
                        allDone = false;
                        break;
                    }
                }
                if (allDone) {//Set中的所有动画都结束 执行结束监听事件
                    // If this was the last child animation to end, then notify listeners that this
                    // AnimatorSet has ended
                    if (mListeners != null) {
                        ArrayList<AnimatorListener> tmpListeners =
                                (ArrayList<AnimatorListener>) mListeners.clone();
                        int numListeners = tmpListeners.size();
                        for (int i = 0; i < numListeners; ++i) {
                            tmpListeners.get(i).onAnimationEnd(mAnimatorSet);
                        }
                    }
                    mAnimatorSet.mStarted = false;
                }
            }
        }

        // Nothing to do
        public void onAnimationRepeat(Animator animation) {
        }

        // Nothing to do
        public void onAnimationStart(Animator animation) {
        }

    }

    /**
     * This method sorts the current set of nodes, if needed. The sort is a simple
     * DependencyGraph sort, which goes like this:
     * - All nodes without dependencies become 'roots'
     * - while roots list is not null
     * -   for each root r
     * -     add r to sorted list
     * -     remove r as a dependency from any other node
     * -   any nodes with no dependencies are added to the roots list
     */
    private void sortNodes() {
        if (mNeedsSort) {
            mSortedNodes.clear();
            ArrayList<Node> roots = new ArrayList<Node>();
            int numNodes = mNodes.size();
            for (int i = 0; i < numNodes; ++i) {
                Node node = mNodes.get(i);
                if (node.dependencies == null || node.dependencies.size() == 0) {
                    roots.add(node);//将没有依赖的Node放到roots列表中
                }
            }
            ArrayList<Node> tmpRoots = new ArrayList<Node>();
            while (roots.size() > 0) {
                int numRoots = roots.size();
                for (int i = 0; i < numRoots; ++i) {
                    Node root = roots.get(i);
                    mSortedNodes.add(root);//遍历没有依赖的roots,将没有依赖的root放入mSortedNodes中
                    if (root.nodeDependents != null) {//root虽没有依赖  但是它可能被依赖,依赖于root的节点存在root的nodeDependents中
                        int numDependents = root.nodeDependents.size();
                        for (int j = 0; j < numDependents; ++j) {//依赖于root的节点node不一定只有一个
                            Node node = root.nodeDependents.get(j);//依赖于root的节点node,node的依赖节点nodeDependencies中必然包含root
                            node.nodeDependencies.remove(root);//只删除依赖Node列表中的Node  不对依赖列表操作
                            if (node.nodeDependencies.size() == 0) {
                                tmpRoots.add(node);//node的依赖节点全被remove后(node的依赖节点只有root), node增加到tmpRoots中
                            }
                        }
                    }
                }
                roots.clear();
                roots.addAll(tmpRoots);//root变为tmpRoots  再继续循环  tmpRoots为空为止 这时将mNodes中的所有节点都按顺序放入了mSortedNodes列表中 
                tmpRoots.clear();
            }
            mNeedsSort = false;//排序完后 标志位置false 重放时 不必再sort
            if (mSortedNodes.size() != mNodes.size()) {
                throw new IllegalStateException("Circular dependencies cannot exist"
                        + " in AnimatorSet");
            }
        } else {
            // Doesn't need sorting, but still need to add in the nodeDependencies list
            // because these get removed as the event listeners fire and the dependencies
            // are satisfied
            int numNodes = mNodes.size();
            for (int i = 0; i < numNodes; ++i) {
                Node node = mNodes.get(i);
                if (node.dependencies != null && node.dependencies.size() > 0) {
                    int numDependencies = node.dependencies.size();
                    for (int j = 0; j < numDependencies; ++j) {
                        Dependency dependency = node.dependencies.get(j);
                        if (node.nodeDependencies == null) {
                            node.nodeDependencies = new ArrayList<Node>();
                        }
                        if (!node.nodeDependencies.contains(dependency.node)) {
                            node.nodeDependencies.add(dependency.node);
                        }
                    }
                }
                // nodes are 'done' by default; they become un-done when started, and done
                // again when ended
                node.done = false;
            }
        }
    }

    /**
     * Dependency holds information about the node that some other node is
     * dependent upon and the nature of that dependency.
     *  依赖类只有两种关系 
     *  1：与依赖一起
     *  2：在依赖的后面
     *  
     *  当为某一节点创建一依赖node的时候  该节点要么与node一起运动 要么就在node的后面运动
     */
    private static class Dependency {
        static final int WITH = 0; // dependent node must start with this dependency node 依赖的节点一起执行
        static final int AFTER = 1; // dependent node must start when this dependency node finishes  依赖的节点完成后再执行

        // The node that the other node with this Dependency is dependent upon
        public Node node;//依赖的节点 dependency node

        // The nature of the dependency (WITH or AFTER)
        public int rule;

        public Dependency(Node node, int rule) {
            this.node = node;
            this.rule = rule;
        }
    }

    /**
     * A Node is an embodiment of both the Animator that it wraps as well as
     * any dependencies that are associated with that Animation. This includes
     * both dependencies upon other nodes (in the dependencies list) as
     * well as dependencies of other nodes upon this (in the nodeDependents list).
     */
    private static class Node implements Cloneable {
        public Animator animation;

        /**
         *  These are the dependencies that this node's animation has on other
         *  nodes. For example, if this node's animation should begin with some
         *  other animation ends, then there will be an item in this node's
         *  dependencies list for that other animation's node.
         */
        public ArrayList<Dependency> dependencies = null;//Node的所有依赖列表

        /**
         * tmpDependencies is a runtime detail. We use the dependencies list for sorting.
         * But we also use the list to keep track of when multiple dependencies are satisfied,
         * but removing each dependency as it is satisfied. We do not want to remove
         * the dependency itself from the list, because we need to retain that information
         * if the AnimatorSet is launched in the future. So we create a copy of the dependency
         * list when the AnimatorSet starts and use this tmpDependencies list to track the
         * list of satisfied dependencies.
         */
        public ArrayList<Dependency> tmpDependencies = null;

        /**
         * nodeDependencies is just a list of the nodes that this Node is dependent upon.
         * This information is used in sortNodes(), to determine when a node is a root.
         */
        public ArrayList<Node> nodeDependencies = null;//本Node依赖的其他Node列表

        /**
         * nodeDepdendents is the list of nodes that have this node as a dependency. This
         * is a utility field used in sortNodes to facilitate removing this node as a
         * dependency when it is a root node.
         */
        public ArrayList<Node> nodeDependents = null;//依赖本Node的Node列表

        /**
         * Flag indicating whether the animation in this node is finished. This flag
         * is used by AnimatorSet to check, as each animation ends, whether all child animations
         * are done and it's time to send out an end event for the entire AnimatorSet.
         */
        public boolean done = false;

        /**
         * Constructs the Node with the animation that it encapsulates. A Node has no
         * dependencies by default; dependencies are added via the addDependency()
         * method.
         *
         * @param animation The animation that the Node encapsulates.
         */
        public Node(Animator animation) {
            this.animation = animation;
        }

        /**
         * Add a dependency to this Node. The dependency includes information about the
         * node that this node is dependency upon and the nature of the dependency.
         * @param dependency
         */
        public void addDependency(Dependency dependency) {
            if (dependencies == null) {
                dependencies = new ArrayList<Dependency>();
                nodeDependencies = new ArrayList<Node>();
            }
            dependencies.add(dependency);//依赖加入列表
            if (!nodeDependencies.contains(dependency.node)) {
                nodeDependencies.add(dependency.node);//依赖的节点加入列表
            }
            Node dependencyNode = dependency.node;
            if (dependencyNode.nodeDependents == null) {
                dependencyNode.nodeDependents = new ArrayList<Node>();
            }
            dependencyNode.nodeDependents.add(this);//dependencyNode是本Node依赖的Node,将本Node增加到dependencyNode的被依赖列表中
        }

        @Override
        public Node clone() {
            try {
                Node node = (Node) super.clone();
                node.animation = (Animator) animation.clone();
                return node;
            } catch (CloneNotSupportedException e) {
               throw new AssertionError();
            }
        }
    }

    /**
     * The <code>Builder</code> object is a utility class to facilitate adding animations to a
     * <code>AnimatorSet</code> along with the relationships between the various animations. The
     * intention of the <code>Builder</code> methods, along with the {@link
     * AnimatorSet#play(Animator) play()} method of <code>AnimatorSet</code> is to make it possible
     * to express the dependency relationships of animations in a natural way. Developers can also
     * use the {@link AnimatorSet#playTogether(Animator[]) playTogether()} and {@link
     * AnimatorSet#playSequentially(Animator[]) playSequentially()} methods if these suit the need,
     * but it might be easier in some situations to express the AnimatorSet of animations in pairs.
     * <p/>
     * <p>The <code>Builder</code> object cannot be constructed directly, but is rather constructed
     * internally via a call to {@link AnimatorSet#play(Animator)}.</p>
     * <p/>
     * <p>For example, this sets up a AnimatorSet to play anim1 and anim2 at the same time, anim3 to
     * play when anim2 finishes, and anim4 to play when anim3 finishes:</p>
     * <pre>
     *     AnimatorSet s = new AnimatorSet();
     *     s.play(anim1).with(anim2);
     *     s.play(anim2).before(anim3);
     *     s.play(anim4).after(anim3);
     * </pre>
     * <p/>
     * <p>Note in the example that both {@link Builder#before(Animator)} and {@link
     * Builder#after(Animator)} are used. These are just different ways of expressing the same
     * relationship and are provided to make it easier to say things in a way that is more natural,
     * depending on the situation.</p>
     * <p/>
     * <p>It is possible to make several calls into the same <code>Builder</code> object to express
     * multiple relationships. However, note that it is only the animation passed into the initial
     * {@link AnimatorSet#play(Animator)} method that is the dependency in any of the successive
     * calls to the <code>Builder</code> object. For example, the following code starts both anim2
     * and anim3 when anim1 ends; there is no direct dependency relationship between anim2 and
     * anim3:
     * <pre>
     *   AnimatorSet s = new AnimatorSet();
     *   s.play(anim1).before(anim2).before(anim3);
     * </pre>
     * If the desired result is to play anim1 then anim2 then anim3, this code expresses the
     * relationship correctly:</p>
     * <pre>
     *   AnimatorSet s = new AnimatorSet();
     *   s.play(anim1).before(anim2);
     *   s.play(anim2).before(anim3);
     * </pre>
     * <p/>
     * <p>Note that it is possible to express relationships that cannot be resolved and will not
     * result in sensible results. For example, <code>play(anim1).after(anim1)</code> makes no
     * sense. In general, circular dependencies like this one (or more indirect ones where a depends
     * on b, which depends on c, which depends on a) should be avoided. Only create AnimatorSets
     * that can boil down to a simple, one-way relationship of animations starting with, before, and
     * after other, different, animations.</p>
     * 
     * 提供创建Node以及Node依赖关系的类 通过Builder构造mNodes列表 和mNodeMap地图
     * 
     * mCurrentNode对应进入Builder的当前动画节点  每次构建Builder都会有一个mCurrentNode,然后在它基础上构建其他Node的依赖关系
     * 而每次Builder的时候 将作为mCurrentNode的动画Node可能已经存在 也就是说存在于这个依赖网络中 就通过animator从Map中取出Node
     */
    public class Builder {

        /**
         * This tracks the current node being processed. It is supplied to the play() method
         * of AnimatorSet and passed into the constructor of Builder.
         */
        private Node mCurrentNode;

        /**
         * package-private constructor. Builders are only constructed by AnimatorSet, when the
         * play() method is called.
         *
         * @param anim The animation that is the dependency for the other animations passed into
         * the other methods of this Builder object.
         * 
         * 创建Builder 初始化当前Node、 Nodes列表 、NodeMap
         */
        Builder(Animator anim) {
            mCurrentNode = mNodeMap.get(anim);
            if (mCurrentNode == null) {
                mCurrentNode = new Node(anim);
                mNodeMap.put(anim, mCurrentNode);
                mNodes.add(mCurrentNode);
            }
        }

        /**
         * Sets up the given animation to play at the same time as the animation supplied in the
         * {@link AnimatorSet#play(Animator)} call that created this <code>Builder</code> object.
         *
         * @param anim The animation that will play when the animation supplied to the
         * {@link AnimatorSet#play(Animator)} method starts.
         * 
         * 将新Animator 加入Nodes和NodeMap中
         * Node类中包含Animator以及Dependency类型
         */
        public Builder with(Animator anim) {
            Node node = mNodeMap.get(anim);
            if (node == null) {
                node = new Node(anim);
                mNodeMap.put(anim, node);
                mNodes.add(node);
            }
            Dependency dependency = new Dependency(mCurrentNode, Dependency.WITH);
            node.addDependency(dependency);
            return this;
        }

        /**
         * Sets up the given animation to play when the animation supplied in the
         * {@link AnimatorSet#play(Animator)} call that created this <code>Builder</code> object
         * ends.
         *
         * @param anim The animation that will play when the animation supplied to the
         * {@link AnimatorSet#play(Animator)} method ends.
         */
        public Builder before(Animator anim) {//mCurrentNode在node之前执行
            Node node = mNodeMap.get(anim);
            if (node == null) {
                node = new Node(anim);
                mNodeMap.put(anim, node);
                mNodes.add(node);
            }
            Dependency dependency = new Dependency(mCurrentNode, Dependency.AFTER);
            node.addDependency(dependency);
            return this;
        }

        /**
         * Sets up the given animation to play when the animation supplied in the
         * {@link AnimatorSet#play(Animator)} call that created this <code>Builder</code> object
         * to start when the animation supplied in this method call ends.
         *
         * @param anim The animation whose end will cause the animation supplied to the
         * {@link AnimatorSet#play(Animator)} method to play.
         */
        public Builder after(Animator anim) {//mCurrentNode在node之后后执行
            Node node = mNodeMap.get(anim);
            if (node == null) {
                node = new Node(anim);
                mNodeMap.put(anim, node);
                mNodes.add(node);
            }
            Dependency dependency = new Dependency(node, Dependency.AFTER);//创建mCurrentNode的依赖 node为其依赖节点
            mCurrentNode.addDependency(dependency);
            return this;
        }

        /**
         * Sets up the animation supplied in the
         * {@link AnimatorSet#play(Animator)} call that created this <code>Builder</code> object
         * to play when the given amount of time elapses.
         *
         * @param delay The number of milliseconds that should elapse before the
         * animation starts.
         */
        public Builder after(long delay) {
            // setup dummy ValueAnimator just to run the clock
            ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
            anim.setDuration(delay);
            after(anim);
            return this;
        }

    }

}
