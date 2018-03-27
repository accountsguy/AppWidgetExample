package accountsguy.net.appwidgetexample;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by advic on 27/03/2018.
 */

public class MyWidgetProvider extends AppWidgetProvider {


    public static PendingIntent buildPendingIndent(Context context){
        ++MyWidgetIntentReceiver.clickCount;

        Intent intent = new Intent();
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews){
        ComponentName componentName = new ComponentName(context, MyWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //super.onUpdate(context, appWidgetManager, appWidgetIds);
//        final int count = appWidgetIds.length;
//
//        for (int i = 0; i < count; i++) {
//            int widgetId = appWidgetIds[i];
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);

        remoteViews.setTextViewText(R.id.title, getTitle());
        remoteViews.setTextViewText(R.id.desc, getDescription());

        remoteViews.setOnClickPendingIntent(R.id.syncbutton, MyWidgetProvider.buildPendingIndent
                (context));
        pushWidgetUpdate(context, remoteViews);
//        }
    }

    private String getTitle(){
        return "Hellow from AccountsGuy.Net";
    }

    private String getDescription(){
        return "Initial Update";
    }
}
