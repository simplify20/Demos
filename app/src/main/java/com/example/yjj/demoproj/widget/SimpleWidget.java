package com.example.yjj.demoproj.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.yjj.demoproj.MessengerActivity;
import com.example.yjj.demoproj.R;

/**
 * @author:YJJ
 * @date:2016/2/22
 * @email:yangjianjun@117go.com
 */
public class SimpleWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_sample);
        Intent intent = new Intent(context, MessengerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.open, pendingIntent);
        for (int i = 0, length = appWidgetIds.length; i < length; i++)
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
    }
}
