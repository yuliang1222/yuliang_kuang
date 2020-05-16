/**
 * ClassName:DenyPolicy
 * Author:Administrator
 * Date:2020/4/16 001616:57
 * Description:TODO
 */
package com.handWriteThreadpooll;

/**
 * @Author: yuliang
 * @Date: 2020/4/16 0016 16:57
 */
@FunctionalInterface
public interface DenyPolicy {
	void reject(Runnable runnable, ThreadPool threadPool);

	class DiscardDenyPolicy implements DenyPolicy {
		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
		}
	}

	class AbortDenyPolicy implements DenyPolicy {
		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			throw new RunnableDenyPolicyException("线程满了");
		}
	}

	class RunnerDenyPolicy implements DenyPolicy {
		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			if (!threadPool.isShutdown()) {
				runnable.run();
			}
		}
	}


}
