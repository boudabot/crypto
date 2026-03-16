package com.localbrief.ui;

import com.localbrief.model.KeyMetric;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public final class MetricTilePanel extends JPanel {
    public MetricTilePanel(KeyMetric metric, Color background, Color border, Color text, Color muted) {
        setOpaque(true);
        setBackground(background);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(border, 1),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(metric.label());
        label.setAlignmentX(LEFT_ALIGNMENT);
        label.setForeground(muted);
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JLabel value = new JLabel(metric.value());
        value.setAlignmentX(LEFT_ALIGNMENT);
        value.setForeground(text);
        value.setFont(new Font("SansSerif", Font.BOLD, 22));

        JLabel detail = new JLabel(metric.detail());
        detail.setAlignmentX(LEFT_ALIGNMENT);
        detail.setForeground(muted);
        detail.setFont(new Font("SansSerif", Font.PLAIN, 12));

        add(label);
        add(Box.createVerticalStrut(10));
        add(value);
        add(Box.createVerticalStrut(8));
        add(detail);
    }
}
