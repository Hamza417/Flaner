package app.simple.flaner.decoration.popup;

import android.content.pm.ApplicationInfo;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

public interface PopupMenuCallback {
    
    /**
     * Suitable for using with {@link androidx.recyclerview.widget.RecyclerView}
     *
     * @param source text of the menu item
     * @param applicationInfo {@link ApplicationInfo}
     * @param icon {@link ImageView} used for transition effects
     */
    default void onMenuItemClicked(@NotNull String source, ApplicationInfo applicationInfo, ImageView icon) {
    }
    
    /**
     * Suitable for using with dialog fragments
     *
     * @param source text of the menu item
     */
    default void onMenuItemClicked(@NotNull String source) {
    }
    
    /**
     * Called when popup is dismissed, use it to
     * trigger animations or any pending events
     */
    default void onDismiss() {
    }
}
