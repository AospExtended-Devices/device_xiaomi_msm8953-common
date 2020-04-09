package com.xiaomi.parts;


import java.lang.IllegalArgumentException;
import android.content.Context;
import android.util.Log;
import android.util.Pair;
import java.util.List;
import java.util.*;

public final class DiracUtils {

public DiracSound mDiracSound;
private boolean mInitialized;

     public boolean hasInitialized()
  {
    return mInitialized;
  }

  public void initialize()
  {
    boolean enabled;
    int iEnabled;
    if (!mInitialized)
    {
      mInitialized = true;
      mDiracSound = new DiracSound(0, 0);
      iEnabled = mDiracSound.getMusic();
      if (iEnabled == 1) {
        enabled = true;
      }else {
        enabled = false;
      }
      mDiracSound.setEnabled(enabled);
    }
  }

    public boolean isDiracEnabled(Context paramContext)
  {
    int i =1;
    int j = 0;
    j = mDiracSound.getMusic();
    if (i == j)
    {
      return true;
    } else {
      return false;
    }
  }

   public void release()
  {
    if (mInitialized)
    {
      mDiracSound.release();
      mDiracSound = null;
      mInitialized = false;
    }
  }

   public void setEnabled(Context paramContext, boolean paramBoolean)
  {
    int i = 1;
    if (paramBoolean)
    {
      i = 1;
    } else {
      i = 0;
    }
    mAudEnhncr.setEnabled(paramBoolean);
    mAudEnhncr.setMusic(i);
    return;
  }

   public void onBootCompleted() {
        setEnabled(mDiracSound.getMusic() == 1);
        mDiracSound.setHeadsetType(mDiracSound.getHeadsetType());
        setLevel(getLevel());
    }



    private String getLevel() {
        StringBuilder selected = new StringBuilder();
        for (int band = 0; band <= 6; band++) {
            int temp = (int) mDiracSound.getLevel(band);
            selected.append(temp);
            if (band != 6) selected.append(",");
        }
        return selected.toString();
    }

    void setLevel(String preset) {
        String[] level = preset.split("\\s*,\\s*");
        for (int band = 0; band <= level.length - 1; band++) {
            mDiracSound.setLevel(band, Float.valueOf(level[band]));
        }
    }

    void setHeadsetType(int paramInt) {
        mDiracSound.setHeadsetType(paramInt);
    }
}
