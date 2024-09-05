package net.PeytonPlayz585.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright (c) 2022-2024 lax1dude. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
public class Logger {
	
	public final String loggerName;

	Logger(String name) {
		this.loggerName = name;
	}
	
	public void trace(String msg) {
		log(Level.TRACE, msg);
	}
	
	public void trace(String msg, Object... args) {
		log(Level.TRACE, msg, args);
	}
	
	public void trace(Throwable msg) {
		log(Level.WARN, msg);
	}
	
	public void debug(String msg) {
		log(Level.DEBUG, msg);
	}
	
	public void debug(String msg, Object... args) {
		log(Level.DEBUG, msg, args);
	}
	
	public void debug(Throwable msg) {
		log(Level.DEBUG, msg);
	}
	
	public void info(String msg) {
		log(Level.INFO, msg);
	}
	
	public void info(String msg, Object... args) {
		log(Level.INFO, msg, args);
	}
	
	public void info(Throwable msg) {
		log(Level.INFO, msg);
	}
	
	public void warn(String msg) {
		log(Level.WARN, msg);
	}
	
	public void warn(String msg, Object... args) {
		log(Level.WARN, msg, args);
	}
	
	public void warn(Throwable msg) {
		log(Level.WARN, msg);
	}
	
	public void error(String msg) {
		log(Level.ERROR, msg);
	}
	
	public void error(String msg, Object... args) {
		log(Level.ERROR, msg, args);
	}
	
	public void error(Throwable msg) {
		log(Level.ERROR, msg);
	}
	
	public void fatal(String msg) {
		log(Level.FATAL, msg);
	}
	
	public void fatal(String msg, Object... args) {
		log(Level.FATAL, msg, args);
	}
	
	public void fatal(Throwable msg) {
		log(Level.FATAL, msg);
	}
	
	private static final SimpleDateFormat fmt = fixDateFormat(new SimpleDateFormat("hh:mm:ss+SSS"));
	private static final Date dateInstance = new Date();
	
	public void log(Level level, String msg) {
		if(level.levelInt >= LogManager.logLevel.levelInt) {
			synchronized(LogManager.logLock) {
				dateInstance.setTime(System.currentTimeMillis());
				String line = "[" + fmt.format(dateInstance) + "]" +
						"[" + "main" + "/" + level.levelName + "]" +
						"[" + loggerName + "]: " + msg;
				level.getPrintStream().println(line);
				if(LogManager.logRedirector != null) {
					LogManager.logRedirector.log(line, level.isErr);
				}
			}
		}
	}
	
	public void log(Level level, String msg, Object... args) {
		if(level.levelInt >= LogManager.logLevel.levelInt) {
			synchronized(LogManager.logLock) {
				dateInstance.setTime(System.currentTimeMillis());
				String line = "[" + fmt.format(dateInstance) + "]" +
						"[" + "main" + "/" + level.levelName + "]" +
						"[" + loggerName + "]: " + formatParams(msg, args);
				level.getPrintStream().println(line);
				if(LogManager.logRedirector != null) {
					LogManager.logRedirector.log(line, level.isErr);
				}
			}
		}
	}

	public static String formatParams(String msg, Object... args) {
		if(args.length > 0) {
			StringBuilder builtString = new StringBuilder();
			for(int i = 0; i < args.length; ++i) {
				int idx = msg.indexOf("{}");
				if(idx != -1) {
					builtString.append(msg.substring(0, idx));
					builtString.append(args[i]);
					msg = msg.substring(idx + 2);
				}else {
					break;
				}
			}
			builtString.append(msg);
			return builtString.toString();
		}else {
			return msg;
		}
	}

	public void log(Level level, Throwable msg) {
		logExcp(level, "Exception Thrown", msg);
	}
	
	private void logExcp(final Level level, String h, Throwable msg) {
		log(level, "{}: {}", h, msg.toString());
		Throwable cause = msg.getCause();
		if(cause != null) {
			logExcp(level, "Caused By", cause);
		}
	}
	
	public void finer(String format, Object... params) {
        log(Level.FINER, format, params);
    }

	public boolean isDebugEnabled() {
		return LogManager.logLevel.levelInt <= Level.DEBUG.levelInt;
	}
	
	private static Calendar getLocaleCalendar() {
		return Calendar.getInstance();
	}
	
	private static <T extends DateFormat> T fixDateFormat(T input) {
		input.setCalendar(getLocaleCalendar());
		return input;
	}

	public void severe(String format, Object... params) {
        log(Level.SEVERE, format, params);
    }

	public void fine(String format, Object... params) {
		log(Level.FINE, format, params);
	}
	
}