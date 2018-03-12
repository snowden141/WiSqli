package com.example.shobhraj.wisqli;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class Mywidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Cursor res= MainActivity.mydb.getData();
        StringBuffer buffer=new StringBuffer();

        while(res.moveToNext()) {
            buffer.append("ID : " + res.getString(0) + "\n");
            buffer.append("NAME : " + res.getString(1) + "\n \n");
        }
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.mywidget);
        views.setTextViewText(R.id.appwidget_text, buffer.toString());

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        RemoteViews views= new RemoteViews(context.getPackageName(),R.layout.mywidget);
        Intent configintent=new Intent(context,MainActivity.class);

        PendingIntent configpendingIntnet=PendingIntent.getActivity(context,0,configintent,0);

        views.setOnClickPendingIntent(R.id.appwidget_text,configpendingIntnet);
        appWidgetManager.updateAppWidget(appWidgetIds,views);

        for (int appWidgetId : appWidgetIds) {
            Cursor res= MainActivity.mydb.getData();
            StringBuffer buffer=new StringBuffer();

            while(res.moveToNext())
            {
                buffer.append("ID : " + res.getString(0) + "\n");
                buffer.append("NAME : "+ res.getString(1) + "\n \n");
            }
            CharSequence widgetText = context.getString(R.string.appwidget_text);
            // Construct the RemoteViews object
            RemoteViews rviews = new RemoteViews(context.getPackageName(), R.layout.mywidget);
            rviews.setTextViewText(R.id.appwidget_text, widgetText);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, rviews);
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

