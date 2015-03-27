package models;

import android.app.Activity;
import network.IGeneralRun;

@SuppressWarnings("unused")
public interface IModel {
    public void save();
    public void save(IGeneralRun run);
    public void save(IGeneralRun run, boolean enableProgress, Activity a);
}
