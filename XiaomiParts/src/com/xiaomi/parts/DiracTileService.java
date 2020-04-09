package com.xiaomi.parts;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Handler;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import com.xiaomi.parts.R;

public class DiracTileService extends TileService {


    @Override
    public void onStartListening() {

        Tile tile = getQsTile();
        if (DiracService.sDiracUtils.hasInitialized() && 
            DiracService.sDiracUtils.isDiracEnabled(getApplicationContext())) {
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            tile.setState(Tile.STATE_INACTIVE);
        }

        tile.updateTile();

        super.onStartListening();
    }

    @Override
    public void onClick() {
	if (!DiracService.sDiracUtils.hasInitialized())
            return;
        Tile tile = getQsTile();
        if (DiracService.sDiracUtils.isDiracEnabled(getApplicationContext())) {
            DiracService.sDiracUtils.setEnabled(getApplicationContext(),false);
            tile.setState(Tile.STATE_INACTIVE);
        } else {
            DiracService.sDiracUtils.setEnabled(getApplicationContext(),true);
            tile.setState(Tile.STATE_ACTIVE);
        }
        tile.updateTile();
        super.onClick();
    }
}
