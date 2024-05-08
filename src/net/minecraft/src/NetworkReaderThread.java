package net.minecraft.src;

class NetworkReaderThread extends Thread {
	final NetworkManager netManager;

	NetworkReaderThread(NetworkManager var1, String var2) {
		super(var2);
		this.netManager = var1;
	}

	public void run() {
		Object var1 = NetworkManager.threadSyncObject;
		synchronized(var1) {
			++NetworkManager.numReadThreads;
		}

		while(true) {
			boolean var12 = false;

			try {
				var12 = true;
				if(!NetworkManager.isRunning(this.netManager)) {
					var12 = false;
					break;
				}

				if(NetworkManager.isServerTerminating(this.netManager)) {
					var12 = false;
					break;
				}

				while(NetworkManager.readNetworkPacket(this.netManager)) {
				}

				try {
					sleep(100L);
				} catch (InterruptedException var15) {
				}
			} finally {
				if(var12) {
					Object var5 = NetworkManager.threadSyncObject;
					synchronized(var5) {
						--NetworkManager.numReadThreads;
					}
				}
			}
		}

		var1 = NetworkManager.threadSyncObject;
		synchronized(var1) {
			--NetworkManager.numReadThreads;
		}
	}
}
