package accountsguy.net.appwidgetexample;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by advic on 27/03/2018.
 */

public class MyWidgetIntentReceiver extends BroadcastReceiver {
    public static int clickCount = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            try {
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);

                remoteViews.setTextViewText(R.id.title, getTitle());
                remoteViews.setTextViewText(R.id.desc, getDescription(context));

                remoteViews.setOnClickPendingIntent(R.id.syncbutton, MyWidgetProvider
                        .buildPendingIndent(context));
                MyWidgetProvider.pushWidgetUpdate(context, remoteViews);
            }
            catch (Exception e){
                Log.i("Test WidgetReceiver-", e.getMessage());
            }
        }
    }

    private String getDescription(Context context) {
        String msg[];
        msg = context.getResources().getStringArray(R.array.media_names);
        if(clickCount >= msg.length){
            clickCount = 0;
        }
        return msg[clickCount];
    }

    private String getTitle() {return "Hellow from AccountsGuy.Net";}
}
