package com.localbrief;

import com.localbrief.service.DemoAssetPageService;
import com.localbrief.ui.AssetPageFrame;

import javax.swing.SwingUtilities;

public final class LocalDataBriefApp {
    private LocalDataBriefApp() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AssetPageFrame frame = new AssetPageFrame(new DemoAssetPageService());
            frame.loadPage();
            frame.setVisible(true);
        });
    }
}
