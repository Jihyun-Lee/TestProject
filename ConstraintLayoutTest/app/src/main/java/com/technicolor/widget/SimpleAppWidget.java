package com.technicolor.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;


/**
 * https://medium.com/android-bits/android-widgets-ad3d166458d3
 * Widget Creation Steps :
 * 1, create layout for the widget.
 * 2, create XML for defining the widget properties.
 * 3, create class for the widget actions.
 * 4, add all these to AndroidManifest.xml file.
 */

public class SimpleAppWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for ( int appWidgetId : appWidgetIds ){
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Toast.makeText(context,"onUpdate",Toast.LENGTH_SHORT).show();
        }
    }


    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                 int appWidgetId) {
        //RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
        // In widget we are not allowing to use intents as usually. We have to use PendingIntent instead of 'startActivity'
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,0 );
        //views.setOnClickPendingIntent(R.id.tvWidget, pendingIntent);
        //appWidgetManager.updateAppWidget(appWidgetId, views);
    }


     /**
     *
     *  Understanding Override Methods* Before going to the next example, we need to understand widget classes that override the following methods.
     *
     *  onUpdate(): This is called to update the App Widget at intervals defined by the updatePeriodMillis attribute.
     *  onAppWidgetOptionsChanged() : This is called when the widget is first placed and also whenever the widget is resized.
     *  onDeleted(Context, int[]): This is called every time an App Widget is deleted from the App Widget host.
     *  onEnabled(Context): This is called when an instance the App Widget is created for the first time.
     *  onDisabled(Context): This is called when the last instance of your App Widget is deleted from the App Widget host.
     *  onReceive(Context, Intent): This is called for every broadcast and before each of the above callback methods.
     */

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Toast.makeText(context,"onAppWidgetOptionsChanged",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Toast.makeText(context,"onDeleted",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Toast.makeText(context,"onEnabled",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Toast.makeText(context,"onDisabled",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Toast.makeText(context,"onReceive",Toast.LENGTH_SHORT).show();
    }
}
