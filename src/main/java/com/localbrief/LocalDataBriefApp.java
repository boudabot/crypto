package com.localbrief;

import com.localbrief.service.MarketOverviewService;
import com.localbrief.ui.DashboardFrame;

import javax.swing.SwingUtilities;

public final class LocalDataBriefApp {
    private LocalDataBriefApp() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardFrame frame = new DashboardFrame(new MarketOverviewService());
            frame.setVisible(true);
            frame.loadData();
        });
    }
}

