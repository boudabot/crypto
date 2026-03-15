package com.localbrief.ui;

import com.localbrief.model.IndicatorSnapshot;
import com.localbrief.model.SeriesPoint;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class IndicatorCardPanel extends JPanel {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public IndicatorCardPanel(IndicatorSnapshot snapshot, Color panelColor, Color textColor, Color accentColor) {
        setBackground(panelColor);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 214, 201), 1),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(snapshot.title());
        title.setAlignmentX(LEFT_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(textColor);

        JLabel description = new JLabel(snapshot.description());
        description.setAlignmentX(LEFT_ALIGNMENT);
        description.setFont(new Font("SansSerif", Font.PLAIN, 13));
        description.setForeground(new Color(92, 87, 79));

        JLabel value = new JLabel(formatValue(snapshot), SwingConstants.LEFT);
        value.setAlignmentX(LEFT_ALIGNMENT);
        value.setFont(new Font("Serif", Font.BOLD, 34));
        value.setForeground(textColor);

        JLabel trend = new JLabel(formatTrend(snapshot));
        trend.setAlignmentX(LEFT_ALIGNMENT);
        trend.setFont(new Font("SansSerif", Font.PLAIN, 13));
        trend.setForeground(colorForTrend(snapshot.deltaValue(), accentColor));

        JLabel date = new JLabel("Derniere observation: " + DATE_FORMATTER.format(snapshot.latestDate()));
        date.setAlignmentX(LEFT_ALIGNMENT);
        date.setFont(new Font("SansSerif", Font.PLAIN, 12));
        date.setForeground(new Color(105, 98, 88));

        add(title);
        add(Box.createVerticalStrut(4));
        add(description);
        add(Box.createVerticalStrut(18));
        add(value);
        add(Box.createVerticalStrut(6));
        add(trend);
        add(Box.createVerticalStrut(8));
        add(date);
        add(Box.createVerticalStrut(14));
        add(new SparklinePanel(snapshot.points(), accentColor));
    }

    private String formatValue(IndicatorSnapshot snapshot) {
        String numeric = snapshot.latestValue().setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        if ("M".equals(snapshot.unit())) {
            return numeric + " M";
        }
        return numeric + " " + snapshot.unit();
    }

    private String formatTrend(IndicatorSnapshot snapshot) {
        String delta = snapshot.deltaValue().abs().setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        return "Variation recente: " + snapshot.trendLabel() + " de " + delta + " " + snapshot.unit();
    }

    private Color colorForTrend(java.math.BigDecimal delta, Color accentColor) {
        int sign = delta.compareTo(java.math.BigDecimal.ZERO);
        if (sign < 0) {
            return new Color(161, 65, 42);
        }
        if (sign > 0) {
            return accentColor;
        }
        return new Color(112, 104, 95);
    }

    private static final class SparklinePanel extends JPanel {
        private final List<SeriesPoint> points;
        private final Color accentColor;

        private SparklinePanel(List<SeriesPoint> points, Color accentColor) {
            this.points = points;
            this.accentColor = accentColor;
            setOpaque(false);
            setPreferredSize(new Dimension(300, 120));
            setMinimumSize(new Dimension(300, 120));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            setAlignmentX(LEFT_ALIGNMENT);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            if (points == null || points.size() < 2) {
                return;
            }

            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int left = 6;
            int right = width - 6;
            int top = 10;
            int bottom = height - 10;

            double min = points.stream().map(SeriesPoint::value).mapToDouble(java.math.BigDecimal::doubleValue).min().orElse(0);
            double max = points.stream().map(SeriesPoint::value).mapToDouble(java.math.BigDecimal::doubleValue).max().orElse(0);
            double range = max - min;
            if (range == 0) {
                range = 1;
            }

            g2.setColor(new Color(230, 224, 214));
            g2.drawLine(left, bottom, right, bottom);

            Path2D path = new Path2D.Double();
            for (int i = 0; i < points.size(); i++) {
                double x = left + ((right - left) * i / (double) (points.size() - 1));
                double normalized = (points.get(i).value().doubleValue() - min) / range;
                double y = bottom - ((bottom - top) * normalized);

                if (i == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }

            g2.setColor(accentColor);
            g2.setStroke(new BasicStroke(2.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(path);
            g2.dispose();
        }
    }
}
