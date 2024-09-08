package net.PeytonPlayz585.fileutils;

import java.util.Collection;
import java.util.LinkedList;

import org.teavm.interop.Async;
import org.teavm.interop.AsyncCallback;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.indexeddb.EventHandler;
import org.teavm.jso.indexeddb.IDBCountRequest;
import org.teavm.jso.indexeddb.IDBCursor;
import org.teavm.jso.indexeddb.IDBCursorRequest;
import org.teavm.jso.indexeddb.IDBDatabase;
import org.teavm.jso.indexeddb.IDBFactory;
import org.teavm.jso.indexeddb.IDBGetRequest;
import org.teavm.jso.indexeddb.IDBObjectStoreParameters;
import org.teavm.jso.indexeddb.IDBOpenDBRequest;
import org.teavm.jso.indexeddb.IDBRequest;
import org.teavm.jso.indexeddb.IDBTransaction;
import org.teavm.jso.indexeddb.IDBVersionChangeEvent;
import org.teavm.jso.typedarrays.ArrayBuffer;
import org.teavm.jso.typedarrays.Uint8Array;

public class IndexedDBFilesystem {
	
	public static enum OpenState {
		OPENED, LOCKED, ERROR
	}

	private static String err = "";
	private static IDBDatabase db = null;

	public static final OpenState initialize() {
		DatabaseOpen dbo = AsyncHandlers.openDB("_net_PeytonPlayz585_eaglercraft_beta_IndexedDBFilesystem_1_7_3");
		if(dbo == null) {
			err = "Unknown Error";
			return OpenState.ERROR;
		}
		if(dbo.failedLocked) {
			return OpenState.LOCKED;
		}
		if(dbo.failedInit || dbo.database == null) {
			err = dbo.failedError == null ? "Initialization Failed" : dbo.failedError;
			return OpenState.ERROR;
		}
		db = dbo.database;
		return OpenState.OPENED;
	}
	
	public static final String errorDetail() {
		return err;
	}
	
	public static final boolean fileExists(String path) {
		return AsyncHandlers.fileGetType(db, path) == FileExists.FILE;
	}
	
	public static final boolean directoryExists(String path) {
		return AsyncHandlers.fileGetType(db, path) == FileExists.DIRECTORY;
	}
	
	public static final boolean pathExists(String path) {
		return AsyncHandlers.fileExists(db, path).bool;
	}
	
	private static final void mkdir(String dir) {
		if(directoryExists(dir)) {
			return;
		}
		int i = dir.lastIndexOf('/');
		if(i > 0) {
			mkdir(dir.substring(0, i));
		}
		AsyncHandlers.writeWholeFile(db, dir, true, ArrayBuffer.create(0));
	}
	
	public static final void writeFile(String path, byte[] data) {
		int i = path.lastIndexOf('/');
		if(i > 0) {
			mkdir(path.substring(0, i));
		}
		Uint8Array arr = Uint8Array.create(data.length);
		arr.set(data);
		AsyncHandlers.writeWholeFile(db, path, false, arr.getBuffer());
	}
	
	public static final byte[] readFile(String path) {
		ArrayBuffer arr = AsyncHandlers.readWholeFile(db, path);
		if(arr == null) {
			return null;
		}
		byte[] data = new byte[arr.getByteLength()];
		Uint8Array arrr = Uint8Array.create(arr);
		for(int i = 0; i < data.length; ++i) {
			data[i] = (byte) arrr.get(i);
		}
		return data;
	}
	
	public static final long getLastModified(String path) {
		int lm = AsyncHandlers.fileGetLastModified(db, path);
		return lm == -1 ? -1l : AsyncHandlers.eaglercraftEpoch + lm;
	}
	
	public static final int getFileSize(String path) {
		ArrayBuffer arr = AsyncHandlers.readWholeFile(db, path);
		if(arr == null) {
			return -1;
		}else {
			return arr.getByteLength();
		}
	}
	
	public static final void renameFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		AsyncHandlers.deleteFile(db, oldPath);
	}
	
	public static final void copyFile(String oldPath, String newPath) {
		ArrayBuffer arr = AsyncHandlers.readWholeFile(db, oldPath);
		int i = newPath.lastIndexOf('/');
		if(i > 0) {
			mkdir(newPath.substring(0, i));
		}
		AsyncHandlers.writeWholeFile(db, newPath, false, arr);
	}
	
	public static final void deleteFile(String path) {
		AsyncHandlers.deleteFile(db, path);
	}

	public static final Collection<FileEntry> listFiles(String path, boolean listDirs, boolean recursiveDirs) {
		LinkedList<FileEntry> lst = new LinkedList<FileEntry>();
		AsyncHandlers.iterateFiles(db, path, listDirs, recursiveDirs, lst);
		return lst;
	}
	
	protected static class BooleanResult {
		
		protected static final BooleanResult TRUE = new BooleanResult(true);
		protected static final BooleanResult FALSE = new BooleanResult(false);
		
		protected final boolean bool;
		
		private BooleanResult(boolean b) {
			bool = b;
		}
		
		protected static BooleanResult _new(boolean b) {
			return b ? TRUE : FALSE;
		}
		
	}
	
	protected static class DatabaseOpen {
		
		protected final boolean failedInit;
		protected final boolean failedLocked;
		protected final String failedError;
		
		protected final IDBDatabase database;
		
		protected DatabaseOpen(boolean init, boolean locked, String error, IDBDatabase db) {
			failedInit = init;
			failedLocked = locked;
			failedError = error;
			database = db;
		}
		
	}
	
	protected static enum FileExists {
		FILE, DIRECTORY, FALSE
	}
	
	@JSBody(script = "return ((typeof indexedDB) !== 'undefined') ? indexedDB : null;")
	protected static native IDBFactory createIDBFactory();
	
	protected static class AsyncHandlers {
		
		protected static final long eaglercraftEpoch = 1645568542000l;
		
		@Async
		protected static native DatabaseOpen openDB(String name);
		
		private static void openDB(String name, final AsyncCallback<DatabaseOpen> cb) {
			IDBFactory i = createIDBFactory();
			if(i == null) {
				cb.complete(new DatabaseOpen(false, false, "window.indexedDB was null or undefined", null));
				return;
			}
			final IDBOpenDBRequest f = i.open(name, 1);
			f.setOnBlocked(new EventHandler() {
				public void handleEvent() {
					cb.complete(new DatabaseOpen(false, true, null, null));
				}
			});
			f.setOnSuccess(new EventHandler() {
				public void handleEvent() {
					cb.complete(new DatabaseOpen(false, false, null, f.getResult()));
				}
			});
			f.setOnError(new EventHandler() {
				public void handleEvent() {
					cb.complete(new DatabaseOpen(true, false, "open error", null));
				}
			});
			f.setOnUpgradeNeeded(new EventListener<IDBVersionChangeEvent>() {
				public void handleEvent(IDBVersionChangeEvent evt) {
					IDBObjectStorePatched.createObjectStorePatch(f.getResult(), "filesystem", IDBObjectStoreParameters.create().keyPath("path"));
				}
			});
		}
		
		@Async
		protected static native BooleanResult deleteFile(IDBDatabase db, String name);
		
		private static void deleteFile(IDBDatabase db, String name, final AsyncCallback<BooleanResult> cb) {
			IDBTransaction tx = db.transaction("filesystem", "readwrite");
			final IDBRequest r = IDBObjectStorePatched.objectStorePatch(tx, "filesystem").delete(makeTheFuckingKeyWork(name));
			
			r.setOnSuccess(new EventHandler() {
				public void handleEvent() {
					cb.complete(BooleanResult._new(true));
				}
			});
			r.setOnError(new EventHandler() {
				public void handleEvent() {
					cb.complete(BooleanResult._new(false));
				}
			});
		}
		
		@JSBody(params = { "obj" }, script = "return (typeof obj === 'undefined') ? null : ((typeof obj.data === 'undefined') ? null : obj.data);")
		protected static native ArrayBuffer readRow(JSObject obj);
		
		@JSBody(params = { "obj" }, script = "return (typeof obj === 'undefined') ? false : ((typeof obj.directory === 'undefined') ? false : obj.directory);")
		protected static native boolean isRowDirectory(JSObject obj);
		
		@JSBody(params = { "obj" }, script = "return (typeof obj === 'undefined') ? -1 : ((typeof obj.lastModified === 'undefined') ? -1 : obj.lastModified);")
		protected static native int readLastModified(JSObject obj);
		
		@JSBody(params = { "obj" }, script = "return [obj];")
		private static native JSObject makeTheFuckingKeyWork(String k);
		
		@Async
		protected static native ArrayBuffer readWholeFile(IDBDatabase db, String name);
		
		private static void readWholeFile(IDBDatabase db, String name, final AsyncCallback<ArrayBuffer> cb) {
			IDBTransaction tx = db.transaction("filesystem", "readonly");
			final IDBGetRequest r = IDBObjectStorePatched.objectStorePatch(tx, "filesystem").get(makeTheFuckingKeyWork(name));
			r.setOnSuccess(new EventHandler() {
				public void handleEvent() {
					cb.complete(isRowDirectory(r.getResult()) ? null : readRow(r.getResult()));
				}
			});
			r.setOnError(new EventHandler() {
				public void handleEvent() {
					cb.complete(null);
				}
			});
			
		}
		
		@Async
		protected static native Integer readLastModified(IDBDatabase db, String name);
		
		private static void readLastModified(IDBDatabase db, String name, final AsyncCallback<Integer> cb) {
			IDBTransaction tx = db.transaction("filesystem", "readonly");
			final IDBGetRequest r = IDBObjectStorePatched.objectStorePatch(tx, "filesystem").get(makeTheFuckingKeyWork(name));
			r.setOnSuccess(new EventHandler() {
				public void handleEvent() {
					cb.complete(readLastModified(r.getResult()));
				}
			});
			r.setOnError(new EventHandler() {
				public void handleEvent() {
					cb.complete(-1);
				}
			});
			
		}
		
		@JSBody(params = { "k" }, script = "return ((typeof k) === \"string\") ? k : (((typeof k) === \"undefined\") ? null : (((typeof k[0]) === \"string\") ? k[0] : null));")
		private static native String readKey(JSObject k);
		
		@Async
		protected static native Integer iterateFiles(IDBDatabase db, final String prefix, final boolean listDirs, final boolean recursiveDirs, final Collection<FileEntry> lst);
		
		private static void iterateFiles(IDBDatabase db, final String prefix, final boolean listDirs, final boolean recursiveDirs, final Collection<FileEntry> lst, final AsyncCallback<Integer> cb) {
			IDBTransaction tx = db.transaction("filesystem", "readonly");
			final IDBCursorRequest r = IDBObjectStorePatched.objectStorePatch(tx, "filesystem").openCursor();
			final int[] res = new int[1];
			r.setOnSuccess(new EventHandler() {
				public void handleEvent() {
					IDBCursor c = r.getResult();
					if(c == null || c.getKey() == null || c.getValue() == null) {
						cb.complete(res[0]);
						return;
					}
					String k = readKey(c.getKey());
					if(k != null) {
						if(k.startsWith(prefix)) {
							if(recursiveDirs || k.indexOf('/', prefix.length() + 1) == -1) {
								boolean dir = isRowDirectory(c.getValue());
								if(dir) {
									if(listDirs) {
										lst.add(new FileEntry(k, true, -1));
									}
								}else {
									lst.add(new FileEntry(k, false, eaglercraftEpoch + readLastModified(c.getValue())));
								}
							}
						}
					}
					c.doContinue();
				}
			});
			r.setOnError(new EventHandler() {
				public void handleEvent() {
					cb.complete(res[0] > 0 ? res[0] : -1);
				}
			});
		}
		
		@Async
		protected static native BooleanResult fileExists(IDBDatabase db, String name);
		
		private static void fileExists(IDBDatabase db, String name, final AsyncCallback<BooleanResult> cb) {
			IDBTransaction tx = db.transaction("filesystem", "readonly");
			final IDBCountRequest r = IDBObjectStorePatched.objectStorePatch(tx, "filesystem").count(makeTheFuckingKeyWork(name));
			r.setOnSuccess(new EventHandler() {
				public void handleEvent() {
					cb.complete(BooleanResult._new(r.getResult() > 0));
				}
			});
			r.setOnError(new EventHandler() {
				public void handleEvent() {
					cb.complete(BooleanResult._new(false));
				}
			});
		}
		
		@Async
		protected static native Integer fileGetLastModified(IDBDatabase db, String name);
		
		private static void fileGetLastModified(IDBDatabase db, String name, final AsyncCallback<Integer> cb) {
			IDBTransaction tx = db.transaction("filesystem", "readonly");
			final IDBGetRequest r = IDBObjectStorePatched.objectStorePatch(tx, "filesystem").get(makeTheFuckingKeyWork(name));
			r.setOnSuccess(new EventHandler() {
				public void handleEvent() {
					cb.complete(readLastModified(r.getResult()));
				}
			});
			r.setOnError(new EventHandler() {
				public void handleEvent() {
					cb.complete(-1);
				}
			});
		}
		
		@Async
		protected static native FileExists fileGetType(IDBDatabase db, String name);
		
		private static void fileGetType(IDBDatabase db, String name, final AsyncCallback<FileExists> cb) {
			IDBTransaction tx = db.transaction("filesystem", "readonly");
			final IDBGetRequest r = IDBObjectStorePatched.objectStorePatch(tx, "filesystem").get(makeTheFuckingKeyWork(name));
			r.setOnSuccess(new EventHandler() {
				public void handleEvent() {
					cb.complete(r.getResult() == null ? FileExists.FALSE : (isRowDirectory(r.getResult()) ? FileExists.DIRECTORY : FileExists.FILE));
				}
			});
			r.setOnError(new EventHandler() {
				public void handleEvent() {
					cb.complete(FileExists.FALSE);
				}
			});
		}
		
		@JSBody(params = { "pat", "dir", "lm", "dat" }, script = "return { path: pat, directory: dir, lastModified: lm, data: dat };")
		protected static native JSObject writeRow(String name, boolean directory, int lm, ArrayBuffer data);
		
		@Async
		protected static native BooleanResult writeWholeFile(IDBDatabase db, String name, boolean directory, ArrayBuffer data);
		
		private static void writeWholeFile(IDBDatabase db, String name, boolean directory, ArrayBuffer data, final AsyncCallback<BooleanResult> cb) {
			IDBTransaction tx = db.transaction("filesystem", "readwrite");
			final IDBRequest r = IDBObjectStorePatched.objectStorePatch(tx, "filesystem").put(writeRow(name, directory, (int)(System.currentTimeMillis() - eaglercraftEpoch), data));
			r.setOnSuccess(new EventHandler() {
				public void handleEvent() {
					cb.complete(BooleanResult._new(true));
				}
			});
			r.setOnError(new EventHandler() {
				public void handleEvent() {
					cb.complete(BooleanResult._new(false));
				}
			});
		}
	}
	
}