package com.mstudio.android.doome.app;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import android.content.SharedPreferences;

public class Applications extends Application {
	private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
	@Override
	public void onCreate() {
		
		this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread thread, Throwable ex) {
				Intent intent = new Intent(getApplicationContext(), DebugActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				intent.putExtra("error", getStackTrace(ex));
				PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 11111, intent, PendingIntent.FLAG_ONE_SHOT);
				AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
				am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingIntent);
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(2);
				uncaughtExceptionHandler.uncaughtException(thread, ex);
				
				SharedPreferences sp =getSharedPreferences("isopenwindow", MODE_PRIVATE);
				SharedPreferences.Editor et = sp.edit();
				et.putBoolean("isopenwindow", false);
				et.commit();
				
				SharedPreferences sh5 =getSharedPreferences("is5s", MODE_PRIVATE);
				SharedPreferences.Editor ett = sh5.edit();
				ett.putBoolean("is5s", false);
				ett.commit();
				
			}
		});
		super.onCreate();
		
	}
	private String getStackTrace(Throwable th){
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		Throwable cause = th;
		while(cause != null){
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		final String stacktraceAsString = result.toString();
		printWriter.close();
		return stacktraceAsString;
	}
}
