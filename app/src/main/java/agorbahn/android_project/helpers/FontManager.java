package agorbahn.android_project.helpers;

/**
 * Created by Adam on 12/2/2016.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;

import agorbahn.android_project.R;

/**
 * Created by Guest on 11/30/16.
 */
public class FontManager {
    public static final String ROOT = "fonts/";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), ROOT + font);
    }

    public static String setIcon(Context context, int id) {
        String result = "";
        result = Html.fromHtml(context.getString(id)).toString();
        return result;
    }
}
